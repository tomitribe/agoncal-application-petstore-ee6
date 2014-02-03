package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.domain.Customer;
import org.agoncal.application.petstore.persistence.Find;
import org.agoncal.application.petstore.persistence.Merge;
import org.agoncal.application.petstore.persistence.NamedQuery;
import org.agoncal.application.petstore.persistence.Persist;
import org.agoncal.application.petstore.persistence.PersistenceHandler;
import org.agoncal.application.petstore.persistence.QueryParam;
import org.agoncal.application.petstore.persistence.Remove;
import org.agoncal.application.petstore.util.Loggable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Stateless
@Loggable
public abstract class CustomerService implements Serializable, InvocationHandler {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private EntityManager em;

    // ======================================
    // =              Public Methods        =
    // ======================================

    public boolean doesLoginAlreadyExist(final String login) {
        try {
            findCustomer(login);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Persist
    public abstract Customer createCustomer(final Customer customer);

    @NamedQuery(Customer.FIND_ALL)
    public abstract List<Customer> findAllCustomers();


    @Find
    public abstract Customer findCustomer(Long id);

    @NamedQuery(Customer.FIND_BY_LOGIN)
    public abstract Customer findCustomer(@QueryParam("login") final String login);


    @NamedQuery(Customer.FIND_BY_LOGIN_PASSWORD)
    public abstract Customer findCustomer(@QueryParam("login") final String login, @QueryParam("login") final String password);


    @Merge
    public abstract Customer updateCustomer(final Customer customer);


    @Remove
    public abstract void removeCustomer(final Customer customer);


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return PersistenceHandler.invoke(em, method, args);
    }
}
