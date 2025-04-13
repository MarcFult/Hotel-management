package at.fhv.sys.hotel.commands;

import at.fhv.sys.hotel.client.EventBusClient;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import at.fhv.sys.hotel.model.Customer;
import at.fhv.sys.hotel.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.logging.Logger;

@ApplicationScoped
public class CustomerAggregate {

    @Inject
    @RestClient
    EventBusClient eventClient;

    @Inject
    CustomerRepository customerRepository;

    public String handle(CreateCustomerCommand command) {
        // Optional: Check if customer already exists
        if (customerRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("Customer with this email already exists");
        }

        // 1. Save to the database
        Customer customer = new Customer();
        customer.setUserId(command.userId());
        customer.setName(command.name());
        customer.setEmail(command.email());

        customerRepository.persist(customer);

        // 2. Create and send event
        CustomerCreated event = new CustomerCreated(command.userId(), command.email());
        Logger.getAnonymousLogger().info(eventClient.processCustomerCreatedEvent(event).toString());

        return command.userId();
    }
}
