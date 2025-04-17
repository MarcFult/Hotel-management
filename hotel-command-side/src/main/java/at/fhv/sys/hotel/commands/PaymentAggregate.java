package at.fhv.sys.hotel.commands;

import at.fhv.sys.hotel.client.EventBusClient;
import at.fhv.sys.hotel.commands.shared.events.PaymentReceivedEvent;
import at.fhv.sys.hotel.model.Booking;
import at.fhv.sys.hotel.model.Payment;
import at.fhv.sys.hotel.repository.BookingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class PaymentAggregate {

    @Inject
    BookingRepository bookingRepository;

    @Inject
    @RestClient
    EventBusClient eventClient;

    public void handle(PayBookingCommand command) {
        Booking booking = bookingRepository.findById(command.getBookingId());
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }

        booking.setPaid(true);

        PaymentReceivedEvent event = new PaymentReceivedEvent(
                command.getPaymentId(),
                command.getBookingId(),
                command.getPaymentDate(),
                command.getPaymentMethod());

        eventClient.processPaymentReceivedEvent(event);
    }
}
