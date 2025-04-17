package at.fhv.sys.hotel.repository;

import at.fhv.sys.hotel.models.CustomerQueryPanacheModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerPanacheRepository implements PanacheRepository<CustomerQueryPanacheModel> {
}
