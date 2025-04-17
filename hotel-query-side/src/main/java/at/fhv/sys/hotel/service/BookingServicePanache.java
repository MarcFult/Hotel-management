package at.fhv.sys.hotel.service;

import at.fhv.sys.hotel.models.BookingQueryPanacheModel;
import at.fhv.sys.hotel.repository.BookingPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class BookingServicePanache {

    @Inject
    BookingPanacheRepository bookingPanacheRepository;

    public List<BookingQueryPanacheModel> getAllBookings() {
        return bookingPanacheRepository.listAll();
    }

    @Transactional
    public void createBooking(BookingQueryPanacheModel booking) {
        bookingPanacheRepository.persist(booking);
    }

    public List<BookingQueryPanacheModel> findBookingsInRange(LocalDate start, LocalDate end) {
        return bookingPanacheRepository.find(
                "startDate <= ?1 and endDate >= ?2", end, start
        ).list();
    }

}
