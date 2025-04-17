/*
package at.fhv.sys.hotel.startup;

import com.eventstore.dbclient.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class EventStoreConnector {

    @ConfigProperty(name = "eventstore.connection-string")
    String connectionString;

    private EventStoreDBClient client;
    private static final Logger LOGGER = Logger.getLogger(EventStoreConnector.class.getName());


    @PostConstruct
    void init() {
        try {
            EventStoreDBClientSettings settings = EventStoreDBConnectionString.parseOrThrow(connectionString);
            this.client = EventStoreDBClient.create(settings);
            LOGGER.info("Successfully connected to EventStoreDB");
            InitProjection();

        } catch (Exception e) {
            LOGGER.severe("Failed to connect to EventStoreDB: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    private void InitProjection(){
        System.out.println("This should run before the routes are public but the db connection is already usable as this would use the db connection to create your data entries");
        System.out.println("And it should block premature request tries");
        System.out.println("InitProjection - here you should get all the existing db content of the event store");

        System.out.println("below should be repeated for each table with in the eventbus-servie");
        System.out.println("Get Event Store Data Entries straight from its db for performance");
        System.out.println("Take said Object and populate your projection rinse and repeat till u have all the elements");
    }
    public EventStoreDBClient getClient() {
        return client;
    }
}
*/
