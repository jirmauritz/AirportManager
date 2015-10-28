package cz.muni.fi.pa165.dao;

import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.muni.fi.pa165.EmbeddedPersistenceContext;
import cz.muni.fi.pa165.entity.Destination;


/**
 * Test class for DestinationDao.
 * 
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedPersistenceContext.class)
public class DestinationDaoImplTest {
	
	private static final String TEST_CITY = "London";

	private static final String TEST_COUNTRY = "United Kingdom";

	private static final Long TEST_ID = 1l;

	private static final String TEST_NAME = "Heathrow Airport";

	@Autowired
	private DestinationDao destinationDaoImpl;
	
	@Autowired
	private EntityManager em;
	
	private Destination newDestination = new Destination();
	
	@Before
	public void SetUp(){
		newDestination.setCity(TEST_CITY);
		newDestination.setCountry(TEST_COUNTRY);
		newDestination.setName(TEST_NAME);
	}
	
	
	/**
	 * Tests methods create and findAll.
	 */
	@Test
	@Ignore
	public final void createAndFindAllTest() {
		destinationDaoImpl.create(newDestination);
		Set<Destination> destinations = destinationDaoImpl.findAll();
		
		Assert.assertFalse(destinations.isEmpty());
		
		Destination destination = destinations.iterator().next();
		
        Assert.assertEquals(destination.getCity(), newDestination.getCity());
        Assert.assertEquals(destination.getCountry(), newDestination.getCountry());
        Assert.assertEquals(destination.getName(), newDestination.getName());
        Assert.assertNotNull(destination.getId());
    }
    
	@Test
    public final void findByIdTest () {
        
    }

	@Test
    public final void findByCountryTest() {
       
    }

	@Test
    public final void updateTest() {
       
    }

	@Test
    public final void deleteTest() {
        
    }


	
}
