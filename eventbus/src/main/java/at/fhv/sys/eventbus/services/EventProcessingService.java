package at.fhv.sys.eventbus.services;

import at.fhv.sys.eventbus.client.QueryClient;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import com.eventstore.dbclient.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@ApplicationScoped
public class EventProcessingService {

    @Inject
    @RestClient
    QueryClient queryClient;

    private EventStoreDBClient eventStore;

    @PostConstruct
    public void init() {
        EventStoreDBClientSettings settings = EventStoreDBConnectionString.parseOrThrow("esdb://localhost:2113?tls=false");
        eventStore = EventStoreDBClient.create(settings);
    }

    public void processEvent(String stream, Object eventObject) {
        if (eventObject instanceof CustomerCreated customerCreated) {
            Logger.getAnonymousLogger().info("Storing and forwarding event: " + customerCreated);

            // 1. Serialize to JSON
            String jsonData = serializeEvent(customerCreated);

            // 2. Append to EventStore
            EventData event = EventData
                    .builderAsJson(UUID.randomUUID(), "CustomerCreated", jsonData.getBytes(StandardCharsets.UTF_8))
                    .build();

            try {
                eventStore.appendToStream(stream, event).get(); // Blocking call
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Failed to append event to stream", e);
            }

            // 3. Forward to query side
            queryClient.forwardCustomerCreatedEvent(customerCreated);
        }
    }

    private String serializeEvent(CustomerCreated event) {
        // You can use Jackson or whatever you prefer
        return """
                {
                  "userId": "%s",
                  "email": "%s"
                }
                """.formatted(event.getUserId(), event.getEmail());
    }
}
