package at.fhv.sys.hotel.projection;

import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import at.fhv.sys.hotel.models.CustomerQueryPanacheModel;
import at.fhv.sys.hotel.service.CustomerServicePanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.logging.Logger;

@ApplicationScoped
public class CustomerProjection {
    private static final Logger LOGGER = Logger.getLogger(CustomerProjection.class.getName());

    @Transactional
    public void processCustomerCreatedEvent(CustomerCreated event) {
        LOGGER.info("Processing customer creation: " + event.getUserId());

        // Skip if user already exists
        if (CustomerQueryPanacheModel.find("userId", event.getUserId()).firstResult() != null) {
            LOGGER.info("Customer already exists, skipping: " + event.getUserId());
            return;
        }

        CustomerQueryPanacheModel customer = new CustomerQueryPanacheModel();
        customer.userId = event.getUserId();
        customer.email = event.getEmail();
        customer.name = event.getName();

        LOGGER.info("Persisting customer: " + customer.getUserId());
        customer.persist();
    }


}