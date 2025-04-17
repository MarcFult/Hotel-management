package at.fhv.sys.hotel.query.controller;

import at.fhv.sys.hotel.commands.shared.events.BookingCreated;
import at.fhv.sys.hotel.models.BookingQueryPanacheModel;
import at.fhv.sys.hotel.projection.BookingProjection;
import at.fhv.sys.hotel.service.BookingServicePanache;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logmanager.Logger;

import java.time.LocalDate;
import java.util.List;
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingQueryController {

    @Inject
    BookingProjection bookingProjection;

    @Inject
    BookingServicePanache bookingServicePanache;

    @POST
    @Path("/bookingCreated")
    public Response bookingCreated(BookingCreated event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        bookingProjection.processBookingCreatedEvent(event);
        return Response.ok(event).build();
    }

    @GET
    @Path("/bookings")
    public Response getBookingsInRange(@QueryParam("start") String startDateStr, @QueryParam("end") String endDateStr) {
        LocalDate start = LocalDate.parse(startDateStr);
        LocalDate end = LocalDate.parse(endDateStr);
        List<BookingQueryPanacheModel> bookings = bookingServicePanache.findBookingsInRange(start, end);
        return Response.ok(bookings).build();
    }
    @GET
    @Path("/bookings/all")
    public Response getAllBookings() {
        List<BookingQueryPanacheModel> bookings = bookingServicePanache.getAllBookings();
        return Response.ok(bookings).build();
    }
}

