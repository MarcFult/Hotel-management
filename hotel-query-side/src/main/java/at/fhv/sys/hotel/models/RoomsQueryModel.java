package at.fhv.sys.hotel.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RoomsQueryModel {

    @Id
    private String roomId;
    private int roomNumber;
    private int capacity;

    public RoomsQueryModel() {
    };

    public RoomsQueryModel(String roomId, int roomNumber, int capacity) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.capacity = capacity;

    };

    public String getRoomId() {
        return roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setId(String roomId) {
        this.roomId = roomId;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;

    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
