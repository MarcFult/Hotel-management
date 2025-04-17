package at.fhv.sys.hotel.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;

@Entity
public class CustomerQueryPanacheModel extends PanacheEntity {

    // Panache provides Auto-CRUD for everything
    public String userId;
    public String email;

    public String name;


    public String getUserId() {
        return userId;
    }


    @Transactional
    public void createCustomer(CustomerQueryPanacheModel customer) {
        customer.persist();
    }

}
