package at.fhv.sys.hotel.controller;


import at.fhv.sys.hotel.commands.BookRoomCommand;
import at.fhv.sys.hotel.commands.BookingAggregate;
import at.fhv.sys.hotel.commands.CancelBookingCommand;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;

@Path("/booking")
public class BookingCommandController {

    @Inject
    BookingAggregate bookingAggregate;

    @POST
    @Path("/bookRoom")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String bookRoom(BookRoomCommand command) {
        return bookingAggregate.handle(command);
    }

    @POST
    @Path("/cancelBooking")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void cancelBooking(CancelBookingCommand command) {
        bookingAggregate.handle(command);
    }
}
