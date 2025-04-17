package at.fhv.sys.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Payment {
    @Id
    private String paymentId;
    private String bookingId;
    private LocalDate paymentDate;
    private String paymentMethod;
    private double amount;


}
