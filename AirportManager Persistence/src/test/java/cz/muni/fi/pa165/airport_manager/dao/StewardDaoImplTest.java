package cz.muni.fi.pa165.airport_manager.dao;

import cz.muni.fi.pa165.airport_manager.config.EmbeddedPersistenceConfiguration;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.entity.Steward;

import java.util.Collections;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedPersistenceConfiguration.class)
@Transactional
public class StewardDaoImplTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private StewardDao stewardDaoTest;

    private Steward peter = new Steward();
    private Steward wendy = new Steward("Wendy", "Darling", Collections.<Flight>emptySet());

    @Before
    public void setUp() {

        peter.setFirstName("Peter");
        peter.setLastName("Pan");

    }

    @Test
    public void testCreateFindAll() {
        stewardDaoTest.create(peter);
        stewardDaoTest.create(wendy);
        Set<Steward> crew = stewardDaoTest.findAll();
        Assert.assertTrue(crew.size() == 2);
    }

    @Test
    public void testFindByFirstName() {
        stewardDaoTest.create(peter);
        Set<Steward> foundPeters = stewardDaoTest.findByFirstName("Peter");

        Assert.assertTrue(foundPeters.size() == 1);

        Steward firstFound = foundPeters.iterator().next();
        Assert.assertEquals(firstFound, peter);
    }

    @Test
    public void testFindByLastName() {
        stewardDaoTest.create(wendy);
        Set<Steward> foundWendys = stewardDaoTest.findByLastName("Darling");

        Assert.assertTrue(foundWendys.size() == 1);

        Steward firstFound = foundWendys.iterator().next();
        Assert.assertEquals(firstFound, wendy);

    }

    @Test
    public void testFindById() {
        stewardDaoTest.create(peter);
        Set<Steward> foundPeters = stewardDaoTest.findByFirstName("Peter");

        Assert.assertTrue(foundPeters.size() == 1);
        Steward firstFound = foundPeters.iterator().next();

        Assert.assertNotNull(firstFound.getId());
        Steward foundPeterId = stewardDaoTest.findById(firstFound.getId());
        Assert.assertEquals(foundPeterId, peter);
    }

    @Test
    public void testUpdate() {
        stewardDaoTest.create(peter);
        peter.setLastName("Parker");
        stewardDaoTest.update(peter);

        Set<Steward> foundPeters = stewardDaoTest.findByLastName("Parker");
        Assert.assertTrue(foundPeters.size() == 1);

        Steward firstFound = foundPeters.iterator().next();
        Assert.assertEquals(firstFound, peter);
    }

    @Test
    public void testDelete() {
        em.persist(wendy);

        stewardDaoTest.delete(wendy);

        Steward foundWendy = em.find(Steward.class, wendy.getId());
        Assert.assertNull(foundWendy);
    }

    @Test(expected = NullPointerException.class)
    public void createNullSteward() {
        stewardDaoTest.create(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void updateNullSteward() {
        stewardDaoTest.update(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void deleteNullSteward() {
        stewardDaoTest.delete(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void findByIdNullSteward() {
        stewardDaoTest.findByBusinessId(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void findByFirstNameNullSteward() {
        stewardDaoTest.findByFirstName(null);
    }

    @Test(expected = NullPointerException.class)
    public void findByLastNameNullSteward() {
        stewardDaoTest.findByLastName(null);
    }
    
}
