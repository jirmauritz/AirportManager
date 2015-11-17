package cz.muni.fi.pa165.airport_manager.dao;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pa165.airport_manager.config.EmbeddedPersistenceConfiguration;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test class for DestinationDao.
 * 
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedPersistenceConfiguration.class)
public class DestinationDaoImplTest {

	private static final String TEST_CITY = "London";
	
	private static final String TEST_CITY1 = "Paris";

	private static final String TEST_COUNTRY = "United Kingdom";

	private static final String TEST_COUNTRY1 = "France"; 
	
	private static final Long TEST_ID = 1l;

	private static final String TEST_NAME = "Heathrow Airport";
	
	private static final String TEST_NAME1 = "Charlesa de Gaulla";

	@Autowired
	private DestinationDao destinationDaoImpl;
	
	@PersistenceContext
	private EntityManager em;

	private Destination newDestination = new Destination();
	private Destination newDestination1 = new Destination();

	@Before
	public void setUp() {
		newDestination.setCity(TEST_CITY);
		newDestination.setCountry(TEST_COUNTRY);
		newDestination.setName(TEST_NAME);
		newDestination1.setCity(TEST_CITY1);
		newDestination1.setCountry(TEST_COUNTRY1);
		newDestination1.setName(TEST_NAME1);
	}

	/**
	 * Tests methods create and findAll.
	 */
	@Test
	@Transactional
	public final void createAndFindAllTest() {
		destinationDaoImpl.create(newDestination);
		destinationDaoImpl.create(newDestination1);
		
		Set<Destination> destinations = destinationDaoImpl.findAll();

		Assert.assertTrue(destinations.size() == 2);

		Destination destination = destinations.iterator().next();

		if (destination.getId().equals(TEST_ID)){
		Assert.assertEquals(destination.getCity(), newDestination.getCity());
		Assert.assertEquals(destination.getCountry(),newDestination.getCountry());
		Assert.assertEquals(destination.getName(), newDestination.getName());
		Assert.assertNotNull(destination.getId());
		} else {
		Assert.assertEquals(destination.getCity(), newDestination1.getCity());
		Assert.assertEquals(destination.getCountry(),newDestination1.getCountry());
		Assert.assertEquals(destination.getName(), newDestination1.getName());
		Assert.assertNotNull(destination.getId());
		
		}
	}

	/**
	 * Tests methods findById, findByName and FindByCountry
	 */
	@Test
	@Transactional
	public final void findByIdFindByNameAndFindByCountryTest() {
		em.persist(newDestination);
		em.persist(newDestination1);
		
		Set<Destination> destinations = destinationDaoImpl.findByCountry(TEST_COUNTRY);
		Assert.assertFalse(destinations.isEmpty());
		
		Destination destination = destinationDaoImpl.findById(destinations.iterator().next().getId());

		Assert.assertEquals(destination.getCity(), newDestination.getCity());
		Assert.assertEquals(destination.getCountry(),newDestination.getCountry());
		Assert.assertEquals(destination.getName(), newDestination.getName());
		
		destination = destinationDaoImpl.findByName(TEST_NAME);

		Assert.assertEquals(destination.getCity(), newDestination.getCity());
		Assert.assertEquals(destination.getCountry(),newDestination.getCountry());
		Assert.assertEquals(destination.getName(), newDestination.getName());
	}


	/**
	 * Tests method delete.
	 */
	@Test
	@Transactional
	public final void deleteTest() {
		em.persist(newDestination);
		
		Set<Destination> destinations = destinationDaoImpl.findAll();
		Assert.assertFalse(destinations.isEmpty());
		
		em.merge(destinations.iterator().next());
		
		destinationDaoImpl.delete(newDestination);
		
		destinations = destinationDaoImpl.findAll();
		Assert.assertTrue(destinations.isEmpty());
	}
	
	/**
	 * Tests method update.
	 */
	@Test
	@Transactional
	public final void updateTest() {
		em.persist(newDestination);
		
		Set<Destination> destinations = destinationDaoImpl.findAll();
		Destination destination = destinations.iterator().next();
		
		destination.setCountry(TEST_COUNTRY1);
		destinationDaoImpl.update(destination);
		
		Destination updatedDestination = em.find(Destination.class, destination.getId());
		Assert.assertEquals(updatedDestination.getCountry(), TEST_COUNTRY1);
		
	}
	
	@Test(expected = NullPointerException.class)
    public void deleteNull() {
		destinationDaoImpl.delete(null);
    }
	
	@Test(expected = NullPointerException.class)
    public void createNull() {
		destinationDaoImpl.create(null);
    }
	
	@Test(expected = NullPointerException.class)
    public void updateNull() {
		destinationDaoImpl.update(null);
    }
	
	@Test(expected = NullPointerException.class)
    public void findByIdNull() {
		destinationDaoImpl.findById(null);
    }
	
	@Test(expected = NullPointerException.class)
    public void findByCountryNull() {
		destinationDaoImpl.findByCountry(null);
    }
	
}
