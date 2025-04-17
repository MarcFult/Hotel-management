package at.fhv.sys.hotel.service;

import at.fhv.sys.hotel.models.RoomsQueryModel;
import at.fhv.sys.hotel.repository.BookingRepository;
import at.fhv.sys.hotel.repository.RoomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class RoomService {

    @Inject
    RoomRepository roomRepository;

    @Inject
    BookingRepository bookingRepository;

    public List<RoomsQueryModel> getFreeRooms(LocalDate startDate, LocalDate endDate, int minCapacity) {
        List<String> bookedRoomIds = bookingRepository.findBookedRoomIds(startDate, endDate);

        return roomRepository.find("capacity >= ?1 and roomId not in ?2", minCapacity, bookedRoomIds).list();
    }

    public List<RoomsQueryModel> getAllRooms() {
        return RoomsQueryModel.listAll();
    }
}
