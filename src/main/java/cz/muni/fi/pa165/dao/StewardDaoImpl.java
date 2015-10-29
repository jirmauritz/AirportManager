package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Steward;

import java.util.ArrayList;
import java.util.List;
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
	public final Steward findById(Long id) {
		Objects.requireNonNull(id);
		return em.find(Steward.class, id);
	}

	@Override
	public final List<Steward> findByFirstName(String firstName) {
		Objects.requireNonNull(firstName);
		return new ArrayList<>(em
				.createQuery("SELECT s FROM Steward WHERE s.firstName = :name")
				.setParameter("name", firstName).getResultList());
	}

	@Override
	public final List<Steward> findByLastName(String lastName) {
		Objects.requireNonNull(lastName);
		return new ArrayList<>(em
				.createQuery("SELECT s FROM Steward WHERE s.lastName = :name")
				.setParameter("name", lastName).getResultList());
	}

	@Override
	public List<Steward> findAll() {
		return new ArrayList<>(em.createQuery("SELECT s FROM Steward s")
				.getResultList());
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

}