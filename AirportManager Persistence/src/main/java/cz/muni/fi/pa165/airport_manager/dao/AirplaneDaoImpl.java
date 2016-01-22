package cz.muni.fi.pa165.airport_manager.dao;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.airport_manager.entity.Airplane;

/**
 * Class is repository for entity Airplane.
 * 
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
@Repository
public class AirplaneDaoImpl implements  AirplaneDao {

	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void create(Airplane airplane) {
		Objects.requireNonNull(airplane);
		em.persist(airplane);
		
	}


	@Override
	public void update(Airplane airplane) {
		Objects.requireNonNull(airplane);
		em.merge(airplane);
	}

	@Override
	public void delete(Airplane airplane) {
		Objects.requireNonNull(airplane);
		em.remove(airplane);
	}
	
	@Override
	public Airplane findById(Long id) {
		Objects.requireNonNull(id);
		return em.find(Airplane.class, id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Set<Airplane> findAll() {
		 return new HashSet<>(
	                em.createQuery("SELECT a FROM Airplane a")
	                        .getResultList()
	        );
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Airplane> findByMinCapacity(int capacity){
		Objects.requireNonNull(capacity);
        return new HashSet<>(
                em.createQuery("SELECT a FROM Airplane a WHERE a.capacity >= :capacity")
                        .setParameter("capacity", capacity)
                        .getResultList()
        );
	}

	@Override
	public Airplane findByName(String name){
		Objects.requireNonNull(name);
        return (Airplane) em.createQuery("SELECT a FROM Airplane a WHERE a.name = :name")
                        .setParameter("name", name)
                        .getSingleResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Airplane> findByType(String type){
		Objects.requireNonNull(type);
        return new HashSet<>(
                em.createQuery("SELECT a FROM Airplane a WHERE a.type = :type")
                        .setParameter("type", type)
                        .getResultList()
        );
	}

}
