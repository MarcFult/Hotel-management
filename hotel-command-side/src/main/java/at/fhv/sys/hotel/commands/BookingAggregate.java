package at.fhv.sys.hotel.commands;

import at.fhv.sys.hotel.client.EventBusClient;
import at.fhv.sys.hotel.commands.shared.events.RoomBookedEvent;
import at.fhv.sys.hotel.commands.shared.events.BookingCancelledEvent;
import at.fhv.sys.hotel.model.Booking;
import at.fhv.sys.hotel.repository.BookingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class BookingAggregate {

    @Inject
    BookingRepository bookingRepository;

    @Inject
    @RestClient
    EventBusClient eventClient;

    public String handle(BookRoomCommand command) {
        // check for overlapping bookings
        List<Booking> overlapping = bookingRepository.findByRoomAndDate(
                command.getRoomId(), command.getStartDate(), command.getEndDate());

        if (!overlapping.isEmpty()) {
            throw new IllegalArgumentException("Room is already booked for that period.");
        }

        Booking booking = new Booking();
        booking.setBookingId(command.getBookingId());
        booking.setCustomerId(command.getCustomerId());
        booking.setRoomId(command.getRoomId());
        booking.setStartDate(command.getStartDate());
        booking.setEndDate(command.getEndDate());
        booking.setPaid(false);

        bookingRepository.persist(booking);

        RoomBookedEvent event = new RoomBookedEvent(
                command.getBookingId(),
                command.getRoomId(),
                command.getCustomerId(),
                command.getStartDate(),
                command.getEndDate());

        eventClient.processRoomBookedEvent(event);
        return command.getBookingId();
    }

    public void handle(CancelBookingCommand command) {
        Booking booking = bookingRepository.findById(command.getBookingId());
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }
        // Optional: Remove it or mark as cancelled
        BookingCancelledEvent event = new BookingCancelledEvent(booking.getBookingId());
        eventClient.processBookingCancelledEvent(event);
    }
}

