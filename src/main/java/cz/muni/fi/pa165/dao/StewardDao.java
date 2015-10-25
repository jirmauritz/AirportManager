package cz.muni.fi.pa165.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cz.muni.fi.pa165.entity.Steward;


/**
 * Repository for entity Steward, using Spring Data.
 * 
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 *
 */
public interface StewardDao extends CrudRepository<Steward, Long> {

	/**
	 * Finds list of stewards with given first name
	 * 
	 * @param firstName
	 * @return list of stewards 
	 */
	List<Steward> findByFirstName(String firstName);
	
	/**
	 * Finds list of stewards with given last name
	 * 
	 * @param lastName
	 * @return list of stewards 
	 */
	List<Steward> findByLastName(String lastName);
	
}
