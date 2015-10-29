package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.EmbeddedPersistenceContext;
import cz.muni.fi.pa165.entity.Steward;
import cz.muni.fi.pa165.entity.Flight;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedPersistenceContext.class)
public class StewardDaoImplTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private StewardDaoImpl stewardDaoTest;

    private Steward peter;
    private Steward wendy;

    @Before
    public void setUp() {
        peter = new Steward();
        peter.setFirstName("Peter");
        peter.setLastName("Pan");
        peter.setBusinessId(Long.MAX_VALUE);

        wendy = new Steward(Long.MIN_VALUE, "Wendy", "Darling", Collections.<Flight>emptySet());

        stewardDaoTest = new StewardDaoImpl();
    }

    @Test
    public void testCreateFindAll() {
        stewardDaoTest.create(peter);
        stewardDaoTest.create(wendy);
        List<Steward> crew = stewardDaoTest.findAll();
        Assert.assertTrue(crew.size() == 2);
    }

    @Test
    public void testFindByFirstName() {
        em.persist(peter);
        List<Steward> foundPeters = stewardDaoTest.findByFirstName("Peter");

        Assert.assertTrue(foundPeters.size() == 1);
        Assert.assertNotNull(foundPeters.get(0).getId());
        Assert.assertEquals(foundPeters.get(0), peter);
    }

    @Test
    public void testFindByLastName() {
        em.persist(wendy);
        List<Steward> foundWendys = stewardDaoTest.findByLastName("Darling");

        Assert.assertTrue(foundWendys.size() == 1);
        Assert.assertNotNull(foundWendys.get(0).getId());
        Assert.assertEquals(foundWendys.get(0), wendy);
    }

    @Test
    public void testFindById() {
        em.persist(peter);
        List<Steward> foundPeters = stewardDaoTest.findByFirstName("Peter");

        Assert.assertTrue(foundPeters.size() == 1);
        Assert.assertNotNull(foundPeters.get(0).getId());

        Steward foundPeterId = stewardDaoTest.findById(foundPeters.get(0).getId());
        Assert.assertEquals(foundPeterId, peter);
    }

    @Test
    public void testUpdate() {
        em.persist(peter);
        peter.setLastName("Parker");
        stewardDaoTest.update(peter);

        List<Steward> foundPeters = stewardDaoTest.findByLastName("Parker");
        Assert.assertTrue(foundPeters.size() == 1);
    }

    @Test
    public void testDelete() {
        em.persist(wendy);
        stewardDaoTest.delete(wendy);
        List<Steward> foundWendys = stewardDaoTest.findByFirstName("Wendy");
        Assert.assertTrue(foundWendys.isEmpty());
    }
}
