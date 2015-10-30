package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.EmbeddedPersistenceContext;
import cz.muni.fi.pa165.entity.Steward;
import cz.muni.fi.pa165.entity.Flight;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.assertj.core.api.Assertions.assertThat;
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
@ContextConfiguration(classes = EmbeddedPersistenceContext.class)
public class StewardDaoImplTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private StewardDao stewardDaoTest;

    private Steward peter = new Steward();
    private Steward wendy = new Steward(Long.MIN_VALUE, "Wendy", "Darling", Collections.<Flight>emptySet());

    ;

    @Before
    public void setUp() {

        peter.setFirstName("Peter");
        peter.setLastName("Pan");
        peter.setBusinessId(Long.MAX_VALUE);

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

}
