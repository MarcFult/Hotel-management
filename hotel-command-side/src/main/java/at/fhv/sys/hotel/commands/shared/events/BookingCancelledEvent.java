package at.fhv.sys.hotel.commands.shared.events;

public class BookingCancelledEvent {
    private String bookingId;
    private String roomId;
    private String customerId;

    public BookingCancelledEvent() {
    }

    public BookingCancelledEvent(String bookingId, String roomId, String customerId) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.customerId = customerId;
    }
    public BookingCancelledEvent(String bookingId) {
        this.bookingId = bookingId;
    }

    // Getter & Setter
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
