package at.fhv.sys.hotel.repository;

import at.fhv.sys.hotel.model.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    public boolean existsByUserId(String userId) {
        return find("userId", userId).firstResultOptional().isPresent();
    }

    public boolean existsByEmail(String email) {
        return find("email", email).firstResultOptional().isPresent();
    }
}
