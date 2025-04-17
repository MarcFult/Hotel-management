package at.fhv.sys.hotel.service;

import at.fhv.sys.hotel.models.CustomerQueryPanacheModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class CustomerServicePanache {

    public List<CustomerQueryPanacheModel> getAllCustomers() {
        List<CustomerQueryPanacheModel> customers = CustomerQueryPanacheModel.listAll();
        Logger.getAnonymousLogger().info("Found " + customers.size() + " customers.");
        return customers;
    }


    @Transactional
    public void createCustomer(CustomerQueryPanacheModel customer) {
        customer.persist();
    }



}