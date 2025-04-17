package at.fhv.sys.hotel.controller;

import at.fhv.sys.hotel.commands.PayBookingCommand;
import at.fhv.sys.hotel.commands.PaymentAggregate;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;

@Path("/payment")
public class PaymentCommandController {

    @Inject
    PaymentAggregate paymentAggregate;

    @POST
    @Path("/payBooking")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void payBooking(PayBookingCommand command) {
        paymentAggregate.handle(command);
    }
}
