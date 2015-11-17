package cz.muni.fi.pa165.airport_manager.dao;

import cz.muni.fi.pa165.airport_manager.config.EmbeddedPersistenceConfiguration;
import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;
import org.springframework.transaction.annotation.Transactional;

/**
 * Unit test that tests DAO of the Airplane entity.
 * 
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedPersistenceConfiguration.class)
@Transactional
public class AirplaneDaoImplTest {
	
	@Autowired
	private AirplaneDao airplaneDaoImpl;
	
	private Airplane airplane;
	private Airplane airplane1;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Before
	public void SetUp(){
		airplane = new Airplane("Airbus", "A330", 255);
		airplane1 = new Airplane("Boing", "747", 300);
	}
	
	// Create airplane
	@Test
	public void createAirplaneTest() {
		airplaneDaoImpl.create(airplane);
		Assert.assertNotNull(airplane.getId());
		
		Airplane savedAirplane = entityManager.find(Airplane.class, airplane.getId());
		
		Assert.assertEquals(airplane, savedAirplane);
	}
	
	@Test(expected = NullPointerException.class)
	public void createNullAirplaneTest() {
		airplaneDaoImpl.create(null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void createAirplaneWithNullNameTest() {
		airplane.setName(null);
		airplaneDaoImpl.create(airplane);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void createAirplaneWithNullCapacityTest() {
		airplane.setName(null);
		airplaneDaoImpl.create(airplane);
	}

	@Test(expected = ConstraintViolationException.class)
	public void createAirplaneWithNullTypeTest() {
		airplane.setType(null);
		airplaneDaoImpl.create(airplane);
	}
	
	// Update airplane
	@Test
	public void updateAirplaneTest() {
		entityManager.persist(airplane);
		Airplane savedAirplane = entityManager.find(Airplane.class, airplane.getId());
		
		// set new parameters
		savedAirplane.setName("Boing");
		savedAirplane.setType("737");
		savedAirplane.setCapacity(200);
		airplaneDaoImpl.update(savedAirplane);
		
		// get updated airplane from db
		savedAirplane = entityManager.find(Airplane.class, airplane.getId());
		
		//assert that the parameters changed
		Assert.assertEquals("Boing", savedAirplane.getName());
		Assert.assertEquals("737", savedAirplane.getType());
		Assert.assertEquals(200, savedAirplane.getCapacity());
	}
	
	@Test(expected = NullPointerException.class)
	public void updateNullAirplaneTest() {		
		airplaneDaoImpl.update(null);
	}
	
	// delete airplane
	@Test
	@SuppressWarnings("unchecked")
	public void deleteAirplaneTest() {
		entityManager.persist(airplane);
		Airplane savedAirplane = entityManager.find(Airplane.class, airplane.getId());
		
		// check there is
		List<Airplane> allAirplanes = entityManager.createQuery("SELECT a FROM Airplane a").getResultList();
		Assert.assertEquals(1, allAirplanes.size());
		
		// delete airplane
		airplaneDaoImpl.delete(savedAirplane);
		
		// check there is not
		allAirplanes = entityManager.createQuery("SELECT a FROM Airplane a").getResultList();
		Assert.assertEquals(0, allAirplanes.size());
	}
	
	@Test
	public void deleteNoAirplaneTest() {
		airplaneDaoImpl.delete(airplane);
		// no exception expected
	}
	
	@Test(expected = NullPointerException.class)
	public void deleteNullAirplaneTest() {
		airplaneDaoImpl.delete(null);
	}
	
	// findById
	@Test
	public void findByIdTest() {
		entityManager.persist(airplane);
		entityManager.persist(airplane1);
		
		Airplane savedAirplane = airplaneDaoImpl.findById(airplane.getId());
		
		Assert.assertEquals(airplane, savedAirplane);	
	}
	
	@Test
	public void findNothingByIdTest() {
		entityManager.persist(airplane);
		
		Airplane savedAirplane = airplaneDaoImpl.findById(airplane.getId() + 1);
		
		Assert.assertEquals(null, savedAirplane);	
	}
	
	@Test(expected = NullPointerException.class)
	public void findAirplaneWithNullIdTest() {
		airplaneDaoImpl.findById(null);
	}
	
	// findAll
	@Test
	public void findAllTest() {
		entityManager.persist(airplane);
		entityManager.persist(airplane1);
		
		Set<Airplane> allAirplanes = airplaneDaoImpl.findAll();
		
		Assert.assertEquals(2, allAirplanes.size());
		Assert.assertTrue(allAirplanes.contains(airplane));
		Assert.assertTrue(allAirplanes.contains(airplane1));
	}
	
	@Test
	public void findNoneTest() {
		
		Set<Airplane> allAirplanes = airplaneDaoImpl.findAll();
		
		Assert.assertTrue(allAirplanes.isEmpty());
	}
	
}
