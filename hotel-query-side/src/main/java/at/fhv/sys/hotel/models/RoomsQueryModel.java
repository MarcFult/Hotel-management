package at.fhv.sys.hotel.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RoomsQueryModel extends PanacheEntityBase {

    @Id
    private String roomId;

    private int roomNumber;
    private double price;

    private int capacity;
    private boolean hasTV;
    private boolean isSmokingAllowed;

    public RoomsQueryModel() {}

    public RoomsQueryModel(String roomId, int roomNumber, double price, int capacity, boolean hasTV, boolean isSmokingAllowed) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.price = price;
        this.capacity = capacity;
        this.hasTV = hasTV;
        this.isSmokingAllowed = isSmokingAllowed;
    }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public boolean isHasTV() { return hasTV; }
    public void setHasTV(boolean hasTV) { this.hasTV = hasTV; }

    public boolean isSmokingAllowed() { return isSmokingAllowed; }
    public void setSmokingAllowed(boolean smokingAllowed) { isSmokingAllowed = smokingAllowed; }
}
