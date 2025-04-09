package at.fhv.sys.hotel.projection;

import at.fhv.sys.hotel.commands.shared.events.BookingCreated;
import at.fhv.sys.hotel.models.BookingQueryModel;
import at.fhv.sys.hotel.models.BookingQueryPanacheModel;
import at.fhv.sys.hotel.service.BookingService;
import at.fhv.sys.hotel.service.BookingServicePanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class BookingProjection {

    @Inject
    BookingService bookingService;

    @Inject
    BookingServicePanache bookingServicePanache;

    public void processBookingCreatedEvent(BookingCreated bookingCreatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + bookingCreatedEvent);
        bookingService.createBooking(new BookingQueryModel(
                bookingCreatedEvent.getBookingId(),
                bookingCreatedEvent.getRoomId(),
                bookingCreatedEvent.getCustomerId(),
                bookingCreatedEvent.getStartDate(),
                bookingCreatedEvent.getEndDate()));

        BookingQueryPanacheModel booking = new BookingQueryPanacheModel();
        booking.bookingId = bookingCreatedEvent.getBookingId();
        booking.roomId = bookingCreatedEvent.getRoomId();
        booking.customerId = bookingCreatedEvent.getCustomerId();
        booking.startDate = bookingCreatedEvent.getStartDate();
        booking.endDate = bookingCreatedEvent.getEndDate();
        bookingServicePanache.createBooking(booking);
    }
}
