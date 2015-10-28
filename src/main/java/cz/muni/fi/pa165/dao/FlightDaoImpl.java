package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Flight;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.EntityManager;


/**
 * Implementation of DAO layer for Flight
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
public class FlightDaoImpl implements FlightDao {

    @Inject
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Flight flight) throws NullPointerException {
        Objects.requireNonNull(flight);
        em.persist(flight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flight findById(Long id) throws NullPointerException {
        Objects.requireNonNull(id);
        return em.find(Flight.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Flight> findAll() {
        return new HashSet<>(em.createQuery("SELECT d FROM Destination d").getResultList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Flight flight) throws NullPointerException {
        Objects.requireNonNull(flight);
        em.merge(flight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Flight flight) throws NullPointerException {
        Objects.requireNonNull(flight);
        em.remove(flight);
    }

}
