package at.fhv.sys.hotel.repository;

import at.fhv.sys.hotel.models.RoomsQueryModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoomRepository implements PanacheRepository<RoomsQueryModel> {
}
