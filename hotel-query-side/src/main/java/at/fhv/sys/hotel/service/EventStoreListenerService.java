package at.fhv.sys.hotel.service;

import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import at.fhv.sys.hotel.projection.CustomerProjection;
import com.eventstore.dbclient.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@ApplicationScoped
public class EventStoreListenerService {

    @Inject
    EventStoreDBClient eventStoreClient;

    @Inject
    CustomerProjection customerProjection;

    private static final Logger logger = Logger.getLogger(EventStoreListenerService.class.getName());

    @PostConstruct
    public void startListening() {
        try {
            // Create the EventStoreDB client settings
            EventStoreDBClientSettings settings = EventStoreDBConnectionString
                    .parseOrThrow("esdb://localhost:2113?tls=false");

            // Create the EventStoreDB client
            eventStoreClient = EventStoreDBClient.create(settings);

            // Subscribe to all events (starting from the earliest event)
            eventStoreClient.subscribeToAll(
                    SubscribeToAllOptions.get().fromStart(), // From the start
                    (ResolvedEvent resolvedEvent) -> onEventReceived(resolvedEvent) // Lambda expression handling events
            ).exceptionally(ex -> {
                logger.severe("Error starting event listener: " + ex.getMessage());
                return null;
            });
        } catch (Exception e) {
            logger.severe("Error during connection: " + e.getMessage());
        }
    }

    // Event handler method
    private void onEventReceived(ResolvedEvent resolvedEvent) {
        try {
            String eventType = resolvedEvent.getEvent().getEventType();
            if ("CustomerCreated".equals(eventType)) {
                // Deserialize the event data
                CustomerCreated event = deserializeEvent(resolvedEvent);
                // Pass to the projection
                customerProjection.processCustomerCreatedEvent(event);
            }
        } catch (Exception e) {
            logger.severe("Error processing event: " + e.getMessage());
        }
    }

    private CustomerCreated deserializeEvent(ResolvedEvent resolvedEvent) {
        // Deserialize event data to CustomerCreated class
        String eventData = new String(resolvedEvent.getEvent().getEventData(), StandardCharsets.UTF_8);
        try {
            return new ObjectMapper().readValue(eventData, CustomerCreated.class);
        } catch (Exception e) {
            logger.severe("Error deserializing event data: " + e.getMessage());
            return null;
        }
    }
}
