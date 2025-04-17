package at.fhv.sys.hotel.commands;

import java.time.LocalDate;

public class PayBookingCommand {
    private String paymentId;
    private String bookingId;
    private LocalDate paymentDate;
    private String paymentMethod;

    // Konstruktor
    public PayBookingCommand(String paymentId, String bookingId, LocalDate paymentDate, String paymentMethod) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    // Getter-Methoden
    public String getPaymentId() {
        return paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
