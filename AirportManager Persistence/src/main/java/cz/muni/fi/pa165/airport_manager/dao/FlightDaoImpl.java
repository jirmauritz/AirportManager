package cz.muni.fi.pa165.airport_manager.dao;

import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.entity.Flight;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * Implementation of DAO layer for Flight
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
@Repository
public class FlightDaoImpl implements FlightDao {

    @PersistenceContext
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
    @SuppressWarnings("unchecked")
    public Set<Flight> findAll() {
        return new HashSet<>(em.createQuery("SELECT d FROM Flight d")
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
        Flight toDelete = em.merge(flight);
        em.remove(toDelete);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Set<Flight> findAllInternational() {
        return new HashSet<>(
                em.createQuery("SELECT f FROM Flight f WHERE f.international = :true")
                .setParameter("true", true).getResultList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Set<Flight> findByDeparture(Date departure) {
        Objects.requireNonNull(departure);
        return new HashSet<>(
                em.createQuery("SELECT f FROM Flight f WHERE f.departure= :departure")
                .setParameter("departure", departure).getResultList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Set<Flight> findByArrival(Date arrival) {
        Objects.requireNonNull(arrival);
        return new HashSet<>(
                em.createQuery("SELECT f FROM Flight f WHERE f.arrival= :arrival")
                .setParameter("arrival", arrival).getResultList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Set<Flight> findAllFromDestination(Destination dest) {
        Objects.requireNonNull(dest);
        return new HashSet<>(
                em.createQuery("SELECT f FROM Flight f WHERE f.from= :destination")
                .setParameter("destination", dest).getResultList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Set<Flight> findAllToDestination(Destination dest) {
        Objects.requireNonNull(dest);
        return new HashSet<>(
                em.createQuery("SELECT f FROM Flight f WHERE f.to= :destination")
                .setParameter("destination", dest).getResultList());
    }

}
