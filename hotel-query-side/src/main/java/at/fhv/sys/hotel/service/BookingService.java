package at.fhv.sys.hotel.service;

import at.fhv.sys.hotel.models.BookingQueryModel;
import at.fhv.sys.hotel.repository.BookingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ApplicationScoped
public class BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingService.class);

    @Inject
    BookingRepository bookingRepository;

    public List<BookingQueryModel> getAllBookings() {
        return bookingRepository.listAll();
    }

    @Transactional
    public void createBooking(String bookingId, String customerId, String roomId, LocalDate startDate, LocalDate endDate) {
        if (bookingRepository.findByBookingId(bookingId).isEmpty()) {
            BookingQueryModel booking = new BookingQueryModel();
            booking.setBookingId(bookingId);
            booking.setCustomerId(customerId);
            booking.setRoomId(roomId);
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);
            bookingRepository.persist(booking);
        } else {
            log.info("Booking with id {} already exists. Skipping insert.", bookingId);
        }
    }
    @Transactional
    public void createBooking(BookingQueryModel booking) {
        createBooking(
                booking.getBookingId(),
                booking.getCustomerId(),
                booking.getRoomId(),
                booking.getStartDate(),
                booking.getEndDate()
        );
    }


}
