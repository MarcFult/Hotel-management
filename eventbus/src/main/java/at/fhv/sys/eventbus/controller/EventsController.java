package at.fhv.sys.eventbus.controller;

import at.fhv.sys.eventbus.services.EventProcessingService;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import at.fhv.sys.hotel.commands.shared.events.BookingCreated;  // Import the BookingCreated event
import at.fhv.sys.hotel.commands.shared.events.RoomCreated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logmanager.Logger;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventsController {
    @Inject
    EventProcessingService eventStoreService;

    public EventsController() {
    }

    @POST
    @Path("/customerCreated")
    public Response customerCreated(CustomerCreated event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processEvent("customer-" + event.getUserId(), event);
        return Response.ok(event).build();
    }

    @POST
    @Path("/bookingCreated")  // Add the new endpoint for bookingCreated
    public Response bookingCreated(BookingCreated event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processEvent("booking-" + event.getBookingId(), event);
        return Response.ok(event).build();
    }

    @POST
    @Path("/roomCreated")
    public Response roomCreated(RoomCreated event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processEvent("room-" + event.getRoomId(), event);
        return Response.ok(event).build();
    }

}
