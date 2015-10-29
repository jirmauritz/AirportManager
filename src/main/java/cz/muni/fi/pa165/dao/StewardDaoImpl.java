package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Steward;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jiri Mauritz
 * @author 4099972@mail.muni.cz
 */
@Repository
@Transactional
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
	public final void delete(final Steward steward) {
		Objects.requireNonNull(steward);
		em.remove(findById(steward.getId()));
	}

	@Override
	public final Steward findById(Long id) {
		Objects.requireNonNull(id);
		return em.find(Steward.class, id);
	}
	
	public final Steward findByBusinessId(Long bId) {
		Objects.requireNonNull(bId);
		return (Steward) em.createQuery("SELECT s FROM Steward s WHERE s.businessId = :bId")
				.setParameter("bId", bId)
				.getSingleResult();
	}

	
	public final Set<Steward> findByFirstNameWithSet(String firstName) {
		Objects.requireNonNull(firstName);
		return new HashSet<>(em
				.createQuery("SELECT s FROM Steward s WHERE s.firstName = :name")
				.setParameter("name", firstName).getResultList());
	}

	
	public final Set<Steward> findByLastNameWithSet(String lastName) {
		Objects.requireNonNull(lastName);
		return new HashSet<>(em
				.createQuery("SELECT s FROM Steward s WHERE s.lastName = :name")
				.setParameter("name", lastName).getResultList());
	}

	
	public Set<Steward> findAllWithSet() {
		return new HashSet<>(em.createQuery("SELECT s FROM Steward s")
				.getResultList());
	}
	
	@Override
	public List<Steward> findByFirstName(String firstName) {
		// delete this method after edit of the interface and rename the right method
		return new ArrayList<>(findByFirstNameWithSet(firstName));
	}

	@Override
	public List<Steward> findByLastName(String lastName) {
		// delete this method after edit of the interface and rename the right method
		return new ArrayList<>(findByLastNameWithSet(lastName));
	}

	@Override
	public List<Steward> findAll() {
		// delete this method after edit of the interface and rename the right method
		return new ArrayList<>(findAllWithSet());
	}



}
