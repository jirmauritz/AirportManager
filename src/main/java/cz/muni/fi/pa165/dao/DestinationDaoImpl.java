package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Destination;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Implementation of the Dao layer for Destination entities. All methods run in transaction.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Transactional
@Named
public class DestinationDaoImpl implements DestinationDao {

    //TODO projit znovu vsechny metody az bude hotovy JavaDoc interfacu, aby metody splnovaly kontrakt

    @Inject
    private EntityManager entityManager;

    @Override
    public final void create(final Destination destination) {
        Objects.requireNonNull(destination);
        entityManager.persist(destination);
    }

    @Override
    public final Destination findById(final Long id) {
        Objects.requireNonNull(id);
        return entityManager.find(Destination.class, id);
    }

    @Override
    @SuppressWarnings("unchecked") //Returned List contains only Destination entities - table name in the query
    public final Set<Destination> findByCountry(final String country) {
        Objects.requireNonNull(country);
        return new HashSet<>(
                entityManager.createQuery("SELECT d FROM Destination d WHERE d.country = :country")
                        .setParameter("country", country)
                        .getResultList()
        );
    }

    @Override
    @SuppressWarnings("unchecked") //Returned List contains only Destination entities - table name in the query
    public final Set<Destination> findAll() {
        return new HashSet<>(
                entityManager.createQuery("SELECT d FROM Destination d")
                        .getResultList()
        );
    }

    @Override
    public final void update(final Destination destination) {
        Objects.requireNonNull(destination);
        entityManager.merge(destination);
    }

    @Override
    public final void delete(final Destination destination) {
        Objects.requireNonNull(destination);
        final Destination d = entityManager.merge(destination);
        entityManager.remove(d);
    }

}
