package cz.muni.fi.pa165.dao;

import java.util.Set;

import cz.muni.fi.pa165.entity.Steward;

/**
 * Repository for entity Steward.
 * 
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 *
 */
public interface StewardDao {

	/**
	 * Finds list of stewards with given first name
	 * 
	 * @param firstName
	 * @return list of stewards
	 */
	Set<Steward> findByFirstName(String firstName);

	/**
	 * Finds list of stewards with given last name
	 * 
	 * @param lastName
	 * @return list of stewards
	 */
	Set<Steward> findByLastName(String lastName);
	
	/**
	 * Finds stewards with given business id.
	 * 
	 * @param businessId
	 * @return steward
	 */
	Steward findByBusinessId(Long businessId);

	void create(Steward steward);

	Steward findById(Long id);

	Set<Steward> findAll();

	void update(Steward steward);

	void delete(Steward steward);
}
