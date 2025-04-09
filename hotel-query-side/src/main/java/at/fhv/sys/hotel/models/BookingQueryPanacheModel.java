package at.fhv.sys.hotel.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class BookingQueryPanacheModel extends PanacheEntity {

    public String bookingId;
    public String roomId;
    public String customerId;
    public LocalDate startDate;
    public LocalDate endDate;

    // Getters and setters
}
