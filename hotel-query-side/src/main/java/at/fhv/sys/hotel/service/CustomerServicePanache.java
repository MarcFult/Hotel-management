package at.fhv.sys.hotel.service;

import at.fhv.sys.hotel.models.CustomerQueryPanacheModel;
import at.fhv.sys.hotel.repository.CustomerPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;




@ApplicationScoped
public class CustomerServicePanache {
    @Inject
    CustomerPanacheRepository customerPanacheRepository;


    public List<CustomerQueryPanacheModel> getAllCustomers() {
        List<CustomerQueryPanacheModel> customers = CustomerQueryPanacheModel.listAll();
        Logger.getAnonymousLogger().info("Found " + customers.size() + " customers.");
        return customers;
    }

    public List<CustomerQueryPanacheModel> findCustomersByName(Optional<String> name) {
        if (name.isPresent() && !name.get().isBlank()) {
            return customerPanacheRepository
                    .find("name like ?1", "%" + name.get() + "%")
                    .list();
        } else {
            return customerPanacheRepository.listAll();
        }
    }



    @Transactional
    public void createCustomer(CustomerQueryPanacheModel customer) {
        customer.persist();
    }



}