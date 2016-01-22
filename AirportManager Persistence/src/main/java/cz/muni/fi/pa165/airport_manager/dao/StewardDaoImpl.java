package cz.muni.fi.pa165.airport_manager.dao;

import cz.muni.fi.pa165.airport_manager.entity.Steward;

import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * Repository for Steward entities for communication with the database.
 *
 * @author Jiri Mauritz
 * @author 4099972@mail.muni.cz
 */
@Repository
public class StewardDaoImpl implements StewardDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public final void create(Steward steward) {
		Objects.requireNonNull(steward);
		em.persist(steward);
	}
	
	@Override
	public final void update(Steward steward) {
		Objects.requireNonNull(steward);
		em.merge(steward);
	}

	@Override
	public final void delete(Steward steward) {
		Objects.requireNonNull(steward);
		final Steward s = em.merge(steward);
		em.remove(s);
	}

	@Override
	public final Steward findById(Long id) {
		Objects.requireNonNull(id);
		return em.find(Steward.class, id);
	}
	
	@Override
	public final Steward findByBusinessId(Long bId) {
		Objects.requireNonNull(bId);
		return (Steward) em.createQuery("SELECT s FROM Steward s WHERE s.businessId = :bId")
				.setParameter("bId", bId)
				.getSingleResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final Set<Steward> findByFirstName(String firstName) {
		Objects.requireNonNull(firstName);
		return new HashSet<>(em
				.createQuery("SELECT s FROM Steward s WHERE s.firstName = :name")
				.setParameter("name", firstName).getResultList());
	}

	@Override
	@SuppressWarnings("unchecked")
	public final Set<Steward> findByLastName(String lastName) {
		Objects.requireNonNull(lastName);
		return new HashSet<>(em
				.createQuery("SELECT s FROM Steward s WHERE s.lastName = :name")
				.setParameter("name", lastName).getResultList());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Steward> findAll() {
		return new HashSet<>(em.createQuery("SELECT s FROM Steward s")
				.getResultList());
	}
	

}
