package cz.muni.fi.pa165.airport_manager.dao;

import cz.muni.fi.pa165.airport_manager.entity.Destination;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Implementation of the Dao layer for Destination entities. All methods run in transaction.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Repository
public class DestinationDaoImpl implements DestinationDao {

    private @PersistenceContext EntityManager entityManager;

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
    public final Destination findByName(final String name) {
        Objects.requireNonNull(name);
        return (Destination) entityManager.createQuery("SELECT d FROM Destination d WHERE d.name = :name")
                        .setParameter("name", name)
                        .getSingleResult();
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
