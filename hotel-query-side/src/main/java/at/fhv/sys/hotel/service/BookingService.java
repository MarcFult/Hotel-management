package at.fhv.sys.hotel.service;

import at.fhv.sys.hotel.models.BookingQueryModel;
import at.fhv.sys.hotel.repository.BookingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class BookingService {

    @Inject
    BookingRepository bookingRepository;

    public List<BookingQueryModel> getAllBookings() {
        return bookingRepository.listAll();
    }

    @Transactional
    public void createBooking(BookingQueryModel booking) {
        bookingRepository.persist(booking);
    }
}
