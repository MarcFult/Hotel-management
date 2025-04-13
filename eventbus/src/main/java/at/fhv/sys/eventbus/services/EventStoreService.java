package at.fhv.sys.eventbus.services;

import com.eventstore.dbclient.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class EventStoreService {

    private EventStoreDBClient client;

    @PostConstruct
    void init() {
        EventStoreDBClientSettings settings = EventStoreDBConnectionString.parseOrThrow(
                "esdb://localhost:2113?tls=false"
        );
        client = EventStoreDBClient.create(settings);
    }

    public void appendEvent(String streamName, Object eventObject) {
        try {
            EventData eventData = EventData.builderAsJson(
                    UUID.randomUUID(),
                    eventObject.getClass().getSimpleName(),
                    eventObject
            ).build();

            client.appendToStream(streamName, AppendToStreamOptions.get(), eventData).get();
        } catch (Exception e) {
            throw new RuntimeException("Failed to append event", e);
        }
    }
}
