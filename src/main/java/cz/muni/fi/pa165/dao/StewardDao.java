package cz.muni.fi.pa165.dao;

import java.util.List;
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
	List<Steward> findByFirstName(String firstName);

	/**
	 * Finds list of stewards with given last name
	 * 
	 * @param lastName
	 * @return list of stewards
	 */
	List<Steward> findByLastName(String lastName);

	void create(Steward steward);

	Steward findById(Long id);

	List<Steward> findAll();

	void update(Steward steward);

	void delete(Steward steward);
}
