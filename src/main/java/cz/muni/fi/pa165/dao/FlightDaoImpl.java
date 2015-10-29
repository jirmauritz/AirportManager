package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Destination;
import cz.muni.fi.pa165.entity.Flight;
import java.util.Date;
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
        return new HashSet<>(em.createQuery("SELECT d FROM Destination d")
                .getResultList());
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Flight> findAllInternational() {
        return new HashSet<Flight>(
                em.createQuery("SELECT f FROM Flight f WHERE f.international = 1")
                .getResultList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Flight> findByDeparture(Date departure) {
        Objects.requireNonNull(departure);
        return new HashSet<Flight>(
                em.createQuery("SELECT f FROM Flight f WHERE f.departure= :departure")
                .setParameter("departure", departure).getResultList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Flight> findByArrival(Date arrival) {
        Objects.requireNonNull(arrival);
        return new HashSet<Flight>(
                em.createQuery("SELECT f FROM Flight f WHERE f.arrival= :arrival")
                .setParameter("arrival", arrival).getResultList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Flight> findAllFromDestination(Destination dest) {
        Objects.requireNonNull(dest);
        return new HashSet<Flight>(
                em.createQuery("SELECT f FROM Flight f WHERE f.from= :destination")
                .setParameter("destination", dest).getResultList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Flight> findAllToDestination(Destination dest) {
        Objects.requireNonNull(dest);
        return new HashSet<Flight>(
                em.createQuery("SELECT f FROM Flight f WHERE f.to= :destination")
                .setParameter("destination", dest).getResultList());
    }

}
