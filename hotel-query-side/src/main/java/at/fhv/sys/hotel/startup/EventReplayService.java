package at.fhv.sys.hotel.startup;

import com.eventstore.dbclient.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class EventReplayService {

    private EventStoreDBClient client;

    @PostConstruct
    public void init() {
        // Set up the EventStoreDB client.
        // Adjust the connection string as necessary (for example, include TLS settings as appropriate).
        EventStoreDBClientSettings settings = EventStoreDBConnectionString.parseOrThrow("esdb://localhost:2113?tls=false");

       client = EventStoreDBClient.create(settings);
        ReadAllOptions options = ReadAllOptions.get().forwards().fromStart();
        client.readAll(options).thenAccept(result -> {
            for (ResolvedEvent resolvedEvent : result.getEvents()) {
                processEvent(resolvedEvent);
            }
        }).join();
        // EventStoreDBClientSettings settings = EventStoreDBClientSettings.builder()
        //         .connectionString("esdb://localhost:2113?tls=false")
        //         .build();
        // client = EventStoreDBClient.create(settings);

        // Start replaying events on application startup.
        // replayEvents();
    }

/*    private void replayEvents() {
        // Create read options to read all events from the beginning.
        ReadAllOptions options = ReadAllOptions.get()
                .forwards()
                .fromStart();

        // Replay events by reading them all from the event store.
        // Using .join() here for simplicity to block until replay completes,
        // but in a real application you might handle this asynchronously.
        client.readAll(options)
                .thenAccept(stream -> stream.forEach(this::processEvent))
                .join();
    }*/

    private void processEvent(ResolvedEvent event) {
        // Retrieve event type and data.
        String eventType = event.getOriginalEvent().getEventType();
        String eventData = new String(event.getOriginalEvent().getEventData(), StandardCharsets.UTF_8);

        // Log the event for demonstration purposes.
        System.out.println("Replaying event: " + eventType + ", data: " + eventData);

        // Depending on the event type, update your read model accordingly.
        // For example:
        switch (eventType) {
            case "CustomerCreated":

                ReplayUtilities rep = new ReplayUtilities();
                Object customer = rep.ParseCustomerCreated(eventData);
                rep.InsertCustomerCreate(customer);


                break;

            case "OrderPlaced":
                // Deserialize eventData and update the order projection.
                // orderProjectionService.handleOrderPlaced(eventData);
                break;

            // Add additional event types as needed.
            default:
                // Optionally log or handle unknown event types.
                System.out.println("Unknown event type: " + eventType);
        }
    }
    /**
     * Replay utilities are needed in the command and the query side. Parse Obeject function is the same in both but the insert into database changes depending of the service
     * example: Query has optimized tables for customer, customer table has to be inserted into more that one table or enriched ex. Customer by profit.
     * while the command line has a quite straight forward table object
     */


    private static final class ReplayUtilities{

        Object ParseCustomerCreated(Object ob){
            return null;
        }

        //call function to create object from event type.
        //call funciton to insert said object into each required query table.
        //Parse event type function and one insert into customer create table function.
        //foobar = ParseObject(EventType event)
        //instertIntoQueryTable(CustomerCreated customer)
        // Deserialize eventData and update/create the user projection.
        // userProjectionService.handleUserCreated(eventData);
        Exception InsertCustomerCreate(Object customerObject){
            return null;
        }

    }
}

