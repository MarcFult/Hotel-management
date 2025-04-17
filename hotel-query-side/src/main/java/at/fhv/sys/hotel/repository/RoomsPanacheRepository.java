package at.fhv.sys.hotel.repository;

import at.fhv.sys.hotel.models.RoomsQueryModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class RoomsPanacheRepository implements PanacheRepository<RoomsQueryModel> {

    public List<RoomsQueryModel> findByMinCapacity(int minCapacity) {
        return list("capacity >= ?1", minCapacity);
    }

    public List<RoomsQueryModel> findAllRooms() {
        return listAll();
    }
}
