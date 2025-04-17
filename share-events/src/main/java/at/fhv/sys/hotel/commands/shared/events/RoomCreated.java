package at.fhv.sys.hotel.commands.shared.events;

public class RoomCreated {
    private String roomId;
    private int roomNumber;
    private double price;
    private int capacity;
    private boolean hasTV;
    private boolean smokingAllowed;

    public RoomCreated() {}

    public RoomCreated(String roomId, int roomNumber, double price, int capacity, boolean hasTV, boolean smokingAllowed) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.price = price;
        this.capacity = capacity;
        this.hasTV = hasTV;
        this.smokingAllowed = smokingAllowed;
    }

    // Getters & setters...

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isHasTV() {
        return hasTV;
    }

    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    public boolean isSmokingAllowed() {
        return smokingAllowed;
    }

    public void setSmokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }

    @Override
    public String toString() {
        return "RoomCreated{" +
                "roomId='" + roomId + '\'' +
                ", roomNumber=" + roomNumber +
                ", price=" + price +
                ", capacity=" + capacity +
                ", hasTV=" + hasTV +
                ", smokingAllowed=" + smokingAllowed +
                '}';
    }
}
