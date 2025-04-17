package at.fhv.sys.hotel.repository;

import at.fhv.sys.hotel.models.BookingQueryPanacheModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookingPanacheRepository implements PanacheRepository<BookingQueryPanacheModel> {
}
