/*
package at.fhv.sys.hotel.startup;

import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import at.fhv.sys.hotel.projection.CustomerProjection;
import com.eventstore.dbclient.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@ApplicationScoped
public class QueryStartupReplay {
    private static final Logger LOGGER = Logger.getLogger(QueryStartupReplay.class.getName());

    @Inject
    EventStoreConnector eventStoreConnector;

    @Inject
    CustomerProjection customerProjection;

    @PostConstruct
    public void replayEvents() {
        LOGGER.info("Starting event replay...");

        try {
            replayFromCategoryStream(eventStoreConnector.getClient(), "$ce-customer");
        } catch (Exception e) {
            LOGGER.warning("Failed to replay customer category stream: " + e.getMessage());
        }
    }

    private void replayFromCategoryStream(EventStoreDBClient client, String categoryStream)
            throws ExecutionException, InterruptedException {

        LOGGER.info("Replaying events from stream: " + categoryStream);

        ReadResult result = client.readStream(categoryStream,
                ReadStreamOptions.get()
                        .fromStart()
                        .resolveLinkTos() // Important for $ce-customer!
        ).get();

        LOGGER.info("Total events fetched: " + result.getEvents().size());

        for (ResolvedEvent resolvedEvent : result.getEvents()) {
            RecordedEvent event = resolvedEvent.getEvent();
            LOGGER.info("Processing event: " + event.getEventType());

            if ("CustomerCreated".equals(event.getEventType())) {
                String json = new String(event.getEventData(), StandardCharsets.UTF_8);
                LOGGER.info("Event JSON: " + json);

                // Simple JSON parsing (replace with a real parser like Jackson/Gson for production)
                String userId = extractFromJson(json, "userId");
                String email = extractFromJson(json, "email");
                String name = extractFromJson(json, "name");

                CustomerCreated customerCreated = new CustomerCreated(userId, email, name);
                customerProjection.processCustomerCreatedEvent(customerCreated);
            }
        }
    }


    private String extractFromJson(String json, String field) {
        String searchString = "\"" + field + "\":\"";
        int start = json.indexOf(searchString) + searchString.length();
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }
}
*/
