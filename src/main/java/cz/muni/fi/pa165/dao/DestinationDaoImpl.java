package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Destination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Slf4j
@Transactional
public class DestinationDaoImpl implements DestinationDao {

    //TODO projit znovu vsechny metody az bude hotovy JavaDoc interfacu, aby metody splnovaly kontrakt

    @PersistenceContext
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
