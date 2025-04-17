package at.fhv.sys.eventbus.services;

import at.fhv.sys.eventbus.client.QueryClient;
import at.fhv.sys.hotel.commands.shared.events.BookingCreated;
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

            // Serialize and append CustomerCreated to EventStoreDB
            String jsonData = serializeEvent(customerCreated);
            EventData event = EventData.builderAsJson(UUID.randomUUID(), "CustomerCreated", jsonData.getBytes(StandardCharsets.UTF_8)).build();
            appendToEventStore(stream, event);

            // Forward to the query side
            queryClient.forwardCustomerCreatedEvent(customerCreated);
        } else if (eventObject instanceof BookingCreated bookingCreated) {
            Logger.getAnonymousLogger().info("Storing and forwarding event: " + bookingCreated);

            // Serialize and append BookingCreated to EventStoreDB
            String jsonData = serializeBookingEvent(bookingCreated);
            EventData event = EventData.builderAsJson(UUID.randomUUID(), "BookingCreated", jsonData.getBytes(StandardCharsets.UTF_8)).build();
            appendToEventStore(stream, event);

            // Forward to the query side
            queryClient.forwardBookingCreatedEvent(bookingCreated);
        }
    }

    private void appendToEventStore(String stream, EventData event) {
        try {
            eventStore.appendToStream(stream, event).get(); // Blocking call
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to append event to stream", e);
        }
    }

    private String serializeEvent(CustomerCreated event) {
        return """
            {
              "userId": "%s",
              "email": "%s",
              "name": "%s"
            }
            """.formatted(event.getUserId(), event.getEmail(), event.getName());
    }

    private String serializeBookingEvent(BookingCreated event) {
        return """
            {
              "bookingId": "%s",
              "roomId": "%s",
              "customerId": "%s",
              "startDate": "%s",
              "endDate": "%s"
            }
            """.formatted(event.getBookingId(), event.getRoomId(), event.getCustomerId(), event.getStartDate(), event.getEndDate());
    }
}
