package at.fhv.sys.hotel.repository;

import at.fhv.sys.hotel.models.BookingQueryModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookingRepository implements PanacheRepository<BookingQueryModel> {

    public Optional<BookingQueryModel> findByBookingId(String bookingId) {
        return find("bookingId", bookingId).firstResultOptional();
    }

    public List<String> findBookedRoomIds(LocalDate startDate, LocalDate endDate) {
        return find("startDate <= ?1 AND endDate >= ?2", endDate, startDate)
                .stream()
                .map(b -> b.getRoomId())
                .distinct()
                .toList();
    }


}
