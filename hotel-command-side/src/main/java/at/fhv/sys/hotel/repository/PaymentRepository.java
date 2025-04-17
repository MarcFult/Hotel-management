package at.fhv.sys.hotel.repository;

import at.fhv.sys.hotel.model.Payment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class PaymentRepository {

    @PersistenceContext
    EntityManager em;

    public void persist(Payment payment) {
        em.persist(payment);
    }

    public Payment findById(String paymentId) {
        return em.find(Payment.class, paymentId);
    }

    public List<Payment> findAll() {
        return em.createQuery("SELECT p FROM Payment p", Payment.class).getResultList();
    }

    public List<Payment> findByBookingId(String bookingId) {
        return em.createQuery("SELECT p FROM Payment p WHERE p.bookingId = :bookingId", Payment.class)
                .setParameter("bookingId", bookingId)
                .getResultList();
    }
}
