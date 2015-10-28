package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.EmbeddedPersistenceContext;
import cz.muni.fi.pa165.entity.Steward;
import cz.muni.fi.pa165.entity.Flight;
import java.util.HashSet;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Test;

/**
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedPersistenceContext.class)
public class StewardDaoImplTest {

    @Inject
    private EntityManager em;
    
    private Steward peter;
    private Steward wendy;
    private StewardDaoImpl stewardDaoTest;
    
    @Before
    void setUp() {
        peter = new Steward();
        peter.setFirstName("Peter");
        peter.setLastName("Pan");
        peter.setBusinessId(Long.MAX_VALUE);
        
        wendy = new Steward(Long.MIN_VALUE, "Wendy", "Darling", new HashSet<Flight>());
        
        stewardDaoTest = new StewardDaoImpl();
    }

    @Test
    public void testCreate(){
        
        stewardDaoTest.create(peter);
        stewardDaoTest.create(wendy);

        List<Steward> crew = stewardDaoTest.findAll();
        
        Assert.assertTrue(crew.size() == 2);
    }
    
    @Test
    public void testFindByName(){
        List<Steward> foundPeters = stewardDaoTest.findByFirstName("Peter");
        
        Assert.assertTrue(foundPeters.size() == 1);
        Assert.assertEquals(foundPeters.get(0), peter);
    }
    
    @Test
    public void testFindBy(){
        List<Steward> foundPeters = stewardDaoTest.findByLastName("Pan");
        
        Assert.assertTrue(foundPeters.size() == 1);
        Assert.assertEquals(foundPeters.get(0), peter);
    }
}
