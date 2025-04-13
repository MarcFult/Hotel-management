package at.fhv.sys.hotel.client;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.EventStoreDBClientSettings;
import com.eventstore.dbclient.EventStoreDBConnectionString;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class EventStoreClient {

    private EventStoreDBClient eventStoreClient;

    void onStart(@Observes StartupEvent ev) {
        EventStoreDBClientSettings settings = EventStoreDBConnectionString.parseOrThrow(
                "esdb://admin:changeit@localhost:2113?tls=false"
        );
        eventStoreClient = EventStoreDBClient.create(settings);
    }

    public EventStoreDBClient getEventStoreClient() {
        return eventStoreClient;
    }
}
