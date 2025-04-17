package at.fhv.sys.hotel.startup;

import at.fhv.sys.hotel.commands.shared.events.BookingCreated;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import at.fhv.sys.hotel.projection.BookingProjection;
import at.fhv.sys.hotel.projection.CustomerProjection;
import com.eventstore.dbclient.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class EventReplayService {

    @Inject
    CustomerProjection customerProjection;

    @Inject
    BookingProjection bookingProjection;

    private final ObjectMapper objectMapper;

    public EventReplayService() {
        // Register the JavaTimeModule for LocalDate, LocalDateTime, etc.
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @PostConstruct
    public void init() {
        EventStoreDBClientSettings settings = EventStoreDBConnectionString.parseOrThrow("esdb://localhost:2113?tls=false");
        EventStoreDBClient client = EventStoreDBClient.create(settings);

        ReadAllOptions options = ReadAllOptions.get().forwards().fromStart();
        client.readAll(options).thenAccept(result -> {
            for (ResolvedEvent resolvedEvent : result.getEvents()) {
                processEvent(resolvedEvent);
            }
        }).join();
    }

    private void processEvent(ResolvedEvent event) {
        String eventType = event.getOriginalEvent().getEventType();
        String eventData = new String(event.getOriginalEvent().getEventData(), StandardCharsets.UTF_8);
        System.out.println("Replaying event: " + eventType + ", data: " + eventData);

        try {
            switch (eventType) {
                case "CustomerCreated":
                    CustomerCreated customer = objectMapper.readValue(eventData, CustomerCreated.class);
                    customerProjection.processCustomerCreatedEvent(customer);
                    break;
                case "BookingCreated":
                    BookingCreated booking = objectMapper.readValue(eventData, BookingCreated.class);
                    bookingProjection.processBookingCreatedEvent(booking);
                    break;
                default:
                    System.out.println("Unknown event type: " + eventType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
