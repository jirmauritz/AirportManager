package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.dao.DestinationDao;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.exception.DataAccessException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.PersistenceException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

/**
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
@RunWith(MockitoJUnitRunner.class)
public class DestinationServiceTest {
    
    @Mock
    private DestinationDao destinationDao;
    
    @InjectMocks
    private DestinationService destinationService = new DestinationServiceImpl();
    
    private Destination destination = new Destination();
    private Destination destination2 = new Destination();

    @Before
    public void setUp() {
        destination.setName("CGN");
        destination.setCity("Köln");
        destination.setCountry("Deutschland");
        
        destination2.setId(123l);
        destination2.setName("DUS");
        destination2.setCity("Düsseldorf");
        destination2.setCountry("Deutschland");
        
        Set<Destination> destinations = new HashSet<>();
        destinations.add(destination);
        destinations.add(destination2);    

        when(destinationDao.findById(123l)).thenReturn(destination2);
        when(destinationDao.findByName("CGN")).thenReturn(destination);
        when(destinationDao.findByCountry("Deutschland")).thenReturn(destinations);
        when(destinationDao.findAll()).thenReturn(destinations);
    }
    
    /** Tests create() **/
    @Test
    public void create() {
        destinationService.create(destination);
        verify(destinationDao).create(Matchers.eq(destination));
    }

    @Test(expected = NullPointerException.class)
    public void createWithNull() {
        destinationService.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithId() {
        destinationService.create(destination2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createWithNullName() {
        destination.setName(null);
        destinationService.create(destination);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createWithEmptyName() {
        destination.setName("");
        destinationService.create(destination);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createWithNullCity() {
        destination.setCity(null);
        destinationService.create(destination);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createWithEmptyCity() {
        destination.setCity("");
        destinationService.create(destination);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createWithNullCountry() {
        destination.setCountry(null);
        destinationService.create(destination);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createWithEmptyCountry() {
        destination.setCountry("");
        destinationService.create(destination);
    }
    
    @Test(expected = DataAccessException.class)
    public void createPersistenceException(){
        doThrow(PersistenceException.class).when(destinationDao).create(any(Destination.class));
        destinationService.create(destination);
    }
    /** END Tests create() **/
    
     /** Tests update() **/
    @Test
    public void update() {
      
        destination2.setId(321l);
        destination2.setName("CGN2");
        destination2.setCity("Koeln");
        destination2.setCountry("Germany");
       
        destinationService.update(destination2);
        verify(destinationDao).update(destination2);
    }

    @Test(expected = NullPointerException.class)
    public void updateWithNull() {
        destinationService.update(null);
    }

    @Test(expected = NullPointerException.class)
    public void updateNullId() {
        destinationService.update(destination);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateWithNullName() {
        destination2.setName(null);
        destinationService.update(destination2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateWithEmptyName() {
        destination2.setName("");     
        destinationService.update(destination2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateWithNullCity() {
        destination2.setCity(null);     
        destinationService.update(destination2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateWithEmptyCity() {
        destination2.setCity("");     
        destinationService.update(destination2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateWithNullCountry() {
        destination2.setCountry(null);
        destinationService.update(destination2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateWithEmptyCountry() {
        destination2.setCountry("");     
        destinationService.update(destination2);
    }
    
    @Test(expected = DataAccessException.class)
    public void updatePersistenceException(){
        doThrow(PersistenceException.class).when(destinationDao).update(any(Destination.class));
        destinationService.update(destination2);
    }
    /** END Tests update() **/
    
    /** Tests delete() **/
    @Test(expected = NullPointerException.class)
    public void deleteWithNullId() {
        destinationService.delete(null);
    }

    @Test
    public void deleteDestinationWithId() {
        destinationService.delete(123l);
        verify(destinationDao).delete(destination2);
    }

    @Test(expected = DataAccessException.class)
    public void deletePersistenceException(){
        doThrow(PersistenceException.class).when(destinationDao).delete(any(Destination.class));
        destinationService.delete(123l);
    }
    /** END Tests delete() **/
    
    /** Tests findById() **/
    @Test(expected = NullPointerException.class)
    public void findByIdNull() {
        destinationService.findById(null);
    }
    
    @Test
    public void findById() {
        destinationService.findById(123l);
        verify(destinationDao).findById(123l);
    }
    
    @Test(expected = DataAccessException.class)
    public void findByIdPersistenceException(){
        doThrow(PersistenceException.class).when(destinationDao).findById(anyLong());
        destinationService.findById(123l);
    }
    /** END Tests findById() **/
    
    /** Tests findByAirportCode() **/
    @Test(expected = NullPointerException.class)
    public void findByAirportCodeNull() {
        destinationService.findByAirportCode(null);
    }
    
    @Test
    public void findByAirportCode() {        
        destinationService.findByAirportCode("CGN");
        verify(destinationDao).findByName("CGN");
    }
    
    @Test(expected = DataAccessException.class)
    public void findByAirplaneCodePersistenceException(){
        doThrow(PersistenceException.class).when(destinationDao).findByName(any(String.class));
        destinationService.findByAirportCode("CGN");
    }
    /** END Tests findByAirportCode() **/
    
    /** Tests findByCountry() **/
    @Test(expected = NullPointerException.class)
    public void findByCountryNull() {
        destinationService.findByCountry(null);
    }
    
    @Test
    public void findByCountry() {        
        final Set<Destination> destinations = destinationService.findByCountry("Deutschland");
        assertThat(destinations).isNotNull().isNotEmpty().contains(destination, destination2); 
    }
    
    @Test(expected = DataAccessException.class)
    public void findByCountryPersistenceException(){
        doThrow(PersistenceException.class).when(destinationDao).findByCountry(any(String.class));
        destinationService.findByCountry("Deutschland");
    }
    /** END Tests findAll() **/
    
    /** Tests findAll() **/
    @Test
    public void findAll() {
        final Set<Destination> destinations = destinationService.findAll();
        assertThat(destinations).isNotNull().isNotEmpty().contains(destination, destination2); 
    }
   
    @Test(expected = DataAccessException.class)
    public void findAllPersistenceException(){
        doThrow(PersistenceException.class).when(destinationDao).findAll();
        destinationService.findAll();
    }
    /** END Tests findAll() **/
}