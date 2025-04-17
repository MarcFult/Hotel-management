package at.fhv.sys.hotel.repository;

import at.fhv.sys.hotel.model.Booking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class BookingRepository {
    @PersistenceContext
    EntityManager em;

    public void persist(Booking booking) {
        em.persist(booking);
    }

    public Booking findById(String id) {
        return em.find(Booking.class, id);
    }

    public List<Booking> findByRoomAndDate(String roomId, LocalDate start, LocalDate end) {
        return em.createQuery("SELECT b FROM Booking b WHERE b.roomId = :roomId AND " +
                        "b.endDate > :start AND b.startDate < :end", Booking.class)
                .setParameter("roomId", roomId)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }
}
