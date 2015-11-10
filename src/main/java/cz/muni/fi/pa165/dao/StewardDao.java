package cz.muni.fi.pa165.dao;

import java.util.Set;

import cz.muni.fi.pa165.entity.Steward;

/**
 * Repository for entity Steward.
 * 
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
public interface StewardDao {

	/**
	 * Finds list of stewards with given first name
	 * 
	 * @param firstName first name of the steward
	 * @return list of stewards
	 */
	Set<Steward> findByFirstName(String firstName);

	/**
	 * Finds list of stewards with given last name
	 * 
	 * @param lastName last name of the steward
	 * @return list of stewards
	 */
	Set<Steward> findByLastName(String lastName);
	
	/**
	 * Finds stewards with given business id.
	 * 
	 * @param businessId id of the steward
	 * @return steward
	 */
	Steward findByBusinessId(Long businessId);

	/**
	 * Creates the specified Steward in the database.
	 *
	 * @param steward an entity to create
	 */
	void create(Steward steward);

	/**
	 * Finds and returns the Steward in the database with the specified id.
	 *
	 * @param id entity id to find
	 * @return an entity with specified id
	 */
	Steward findById(Long id);

	/**
	 * Finds and returns all Stewards
	 *
	 * @return set of Stewards
	 */
	Set<Steward> findAll();

	/**
	 * Updates the state of the Steward in the database
	 *
	 * @param steward steward to be updated
	 */
	void update(Steward steward);

	/**
	 * Deletes the Steward from database
	 *
	 * @param steward entity steward to be deleted
	 */
	void delete(Steward steward);
}
