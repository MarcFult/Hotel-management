package at.fhv.sys.hotel.projection;

import at.fhv.sys.hotel.commands.shared.events.RoomCreated;
import at.fhv.sys.hotel.models.RoomsQueryModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class RoomProjection {

    @Transactional
    public void processRoomsCreatedEvent(RoomCreated event) {
        // Check if the room already exists in the query model
        Optional<RoomsQueryModel> existing = RoomsQueryModel.find("roomId", event.getRoomId()).firstResultOptional();
        if (existing.isPresent()) {
            return; // Room already projected, no need to persist again
        }

        // Create and persist the new room projection if it doesn't exist
        RoomsQueryModel room = new RoomsQueryModel();
        room.setRoomId(event.getRoomId());
        room.setRoomNumber(event.getRoomNumber());
        room.setPrice(event.getPrice());
        room.setCapacity(event.getCapacity());
        room.setHasTV(event.isHasTV());
        room.setSmokingAllowed(event.isSmokingAllowed());
        room.persist();
    }
}
