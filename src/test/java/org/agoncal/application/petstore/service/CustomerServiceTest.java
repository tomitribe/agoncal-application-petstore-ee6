package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.domain.Address;
import org.agoncal.application.petstore.domain.Customer;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@Ignore
@RunWith(Arquillian.class)
public class CustomerServiceTest extends AbstractServiceTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private CustomerService customerService;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    // ======================================
    // =              Unit tests            =
    // ======================================

    @Test
    public void shouldCRUDaCustomer() {

        // Finds all the objects
        int initialNumber = customerService.findAllCustomers().size();

        // Creates an object
        Customer customer = new Customer("Penelope", "Gallimore", "happy", "birthday", "penelope@tomitribe.com", new Address("65 Ritherdon Road", "North Hampton", "56421", "UK"));

        // Persists the object
        customer = customerService.createCustomer(customer);

        Long id = customer.getId();

        // Finds all the objects and checks there's an extra one
        assertEquals("Should have an extra object", initialNumber + 1, customerService.findAllCustomers().size());

        // Finds the object by primary key
        customer = customerService.findCustomer(id);
        assertEquals("Penelope", customer.getFirstname());

        // Updates the object
        customer.setFirstname("Laura");
        customerService.updateCustomer(customer);

        // Finds the object by primary key
        customer = customerService.findCustomer(id);
        assertEquals("Laura", customer.getFirstname());

        // Deletes the object
        customerService.removeCustomer(customer);

        // Checks the object has been deleted
        assertNull("Should has been deleted", customerService.findCustomer(id));

        // Finds all the objects and checks there's one less
        assertEquals("Should have an extra object", initialNumber, customerService.findAllCustomers().size());
    }

}
