package at.fhv.sys.hotel.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class BookingQueryModel {

    @Id
    private String bookingId;
    private String roomId;
    private String customerId;
    private LocalDate startDate;
    private LocalDate endDate;

    public BookingQueryModel() {
    }

    public BookingQueryModel(String bookingId, String roomId, String customerId, LocalDate startDate,
            LocalDate endDate) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and setters
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
