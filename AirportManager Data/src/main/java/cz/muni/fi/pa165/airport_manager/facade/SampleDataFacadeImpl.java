package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.config.DataConfiguration;
import cz.muni.fi.pa165.airport_manager.dto.*;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Basic implementation of sample data for AirportManager
 *
 * @author Tomas Valka, Dušan Lago
 * @author 422718@mail.muni.cz, 396336@mail.muni.cz
 */
@Facade
public class SampleDataFacadeImpl implements SampleDataFacade {

    private @Autowired
    FlightFacade flightFacade;

    private @Autowired
    DestinationFacade destinationFacade;

    private @Autowired
    AirplaneFacade airplaneFacade;

    private @Autowired
    StewardFacade stewardFacade;

    @Autowired
    EntityManager em;

    @Override
    public void loadData() {
        // destinations
        DestinationSimpleDTO SHA430 = createDestination("SHA", "Shanghai", "China");
        DestinationSimpleDTO KAR735 = createDestination("KAR", "Karachi", "Pakistan");
        DestinationSimpleDTO LAG255 = createDestination("LAG", "Lagos", "Nigeria");
        DestinationSimpleDTO DEL532 = createDestination("DEL", "Delhi", "India");
        DestinationSimpleDTO IST676 = createDestination("IST", "Istanbul", "Turkey");
        DestinationSimpleDTO TOK639 = createDestination("TOK", "Tokyo", "Japan");
        DestinationSimpleDTO TIA416 = createDestination("TIA", "Tianjin", "China");
        DestinationSimpleDTO GUA103 = createDestination("GUA", "Guangzhou", "China");
        DestinationSimpleDTO MUM180 = createDestination("MUM", "Mumbai", "India");
        DestinationSimpleDTO MOS673 = createDestination("MOS", "Moscow", "Russia");
        DestinationSimpleDTO SAP339 = createDestination("SAP", "São Paulo", "Brazil");
        DestinationSimpleDTO BEI973 = createDestination("BEI", "Beijing", "China");
        DestinationSimpleDTO SHE785 = createDestination("SHE", "Shenzhen", "China");
        DestinationSimpleDTO SEO471 = createDestination("SEO", "Seoul", "South Korea");
        DestinationSimpleDTO JAK487 = createDestination("JAK", "Jakarta", "Indonesia");
        DestinationSimpleDTO LAH638 = createDestination("LAH", "Lahore", "Pakistan");
        DestinationSimpleDTO KIN411 = createDestination("KIN", "Kinshasa", "Democratic Republic of the Congo");
        DestinationSimpleDTO CAI346 = createDestination("CAI", "Cairo", "Egypt");
        DestinationSimpleDTO MEX134 = createDestination("MEX", "Mexico City", "Mexico");
        DestinationSimpleDTO LIM467 = createDestination("LIM", "Lima", "Peru");
        DestinationSimpleDTO NEW828 = createDestination("NYC", "New York City", "United States");

        // airplanes
        AirplaneDTO jumbo = createAirplane("Boeing 747 Jumbo", AirplaneType.ECONOMY, 600);
        AirplaneDTO vulture = createAirplane("Boeing 747 Vulture", AirplaneType.ECONOMY, 427);
        AirplaneDTO cardinal = createAirplane("Boeing 747 Cardinal", AirplaneType.BUSINESS, 427);
        AirplaneDTO jay = createAirplane("Airbus A320 Jay", AirplaneType.FIRST, 200);
        AirplaneDTO wren = createAirplane("Airbus A320 Wren", AirplaneType.ECONOMY, 525);
        AirplaneDTO swallow = createAirplane("Airbus A320 Swallow", AirplaneType.ECONOMY, 100);

        // stewards
        StewardDTO carman = createSteward("Carman", "Parlier");
        StewardDTO rose = createSteward("Rose", "Fosdick");
        StewardDTO keisha = createSteward("Keisha", "Mak");
        StewardDTO mamie = createSteward("Mamie", "Mund");
        StewardDTO deane = createSteward("Deane", "Cassady");
        StewardDTO rhea = createSteward("Rhea", "Foley");
        StewardDTO sean = createSteward("Sean", "Neubert");
        StewardDTO gerardo = createSteward("Gerardo", "Williford");
        StewardDTO ema = createSteward("Ema", "Holder");
        StewardDTO lavette = createSteward("Lavette", "Gully");
        StewardDTO aracely = createSteward("Aracely", "Dimas");
        StewardDTO notassigned = createSteward("Not", "Assigned");

        // crews
        Set<StewardSimpleDTO> crew1 = new HashSet<>();
        crew1.add(carman);
        crew1.add(rose);
        crew1.add(keisha);
        crew1.add(mamie);

        Set<StewardSimpleDTO> crew2 = new HashSet<>();
        crew2.add(deane);
        crew2.add(rhea);
        crew2.add(sean);
        crew2.add(gerardo);

        Set<StewardSimpleDTO> crew3 = new HashSet<>();
        crew3.add(ema);
        crew3.add(lavette);
        crew3.add(aracely);

        // flights
        FlightDTO f1 = createFlight(true, new Date(1451664000L), new Date(1451757600000L), null, jumbo, SHA430, KAR735);
        FlightDTO f2 = createFlight(false, new Date(1451736000L), new Date(1451777400000L), crew2, cardinal, SHA430, GUA103);
        FlightDTO f3 = createFlight(true, new Date(1451734200L), new Date(1451755800000L), crew3, jay, MOS673, SEO471);
        FlightDTO f4 = createFlight(true, new Date(1451896200L), new Date(1451911500000L), crew1, vulture, LIM467, SAP339);
        FlightDTO f5 = createFlight(true, new Date(1451997900L), new Date(1452037500000L), crew2, jay, KIN411, MUM180);
        FlightDTO f6 = createFlight(true, new Date(1452145500L), new Date(1452169200000L), crew3, wren, SEO471, SHE785);
        FlightDTO f7 = createFlight(true, new Date(1452164400L), new Date(1452189600000L), crew1, jumbo, IST676, LAG255);
        FlightDTO f8 = createFlight(true, new Date(1452168000L), new Date(1452182400000L), crew2, cardinal, NEW828, MEX134);
        FlightDTO f9 = createFlight(true, new Date(1452312000L), new Date(1452394800000L), crew3, vulture, DEL532, TOK639);
        FlightDTO f10 = createFlight(true, new Date(1452668400L), new Date(1452715200000L), crew1, jay, BEI973, JAK487);
    }

    @Override
    public void loadUsers() {
        em.createNativeQuery("CREATE TABLE users (" +
                "username VARCHAR(7) NOT NULL, " +
                "password VARCHAR(60) NOT NULL, " +
                "enabled BOOLEAN NOT NULL DEFAULT TRUE, " +
                "PRIMARY KEY (username))")
                .executeUpdate();
        em.createNativeQuery("CREATE TABLE user_roles (" +
                "username VARCHAR(7) NOT NULL, " +
                "role VARCHAR(12) NOT NULL, " +
                "FOREIGN KEY (username) REFERENCES users (username))")
                .executeUpdate();

        em.createNativeQuery("INSERT INTO users (username, password, enabled)" +
                "VALUES ('" + DataConfiguration.USER_ADMIN   + "', '" + DataConfiguration.PASSWD_ADMIN + "', true)")
                .executeUpdate();
        em.createNativeQuery("INSERT INTO users (username, password, enabled)" +
                "VALUES ('" + DataConfiguration.USER_FLIGHT  + "', '" + DataConfiguration.PASSWD_FLIGHT + "', true)")
                .executeUpdate();
        em.createNativeQuery("INSERT INTO users (username, password, enabled)" +
                "VALUES ('" + DataConfiguration.USER_AIRPORT + "', '" + DataConfiguration.PASSWD_AIRPORT + "', true)")
                .executeUpdate();

        em.createNativeQuery("INSERT INTO user_roles (username, role)" +
                "VALUES ('" + DataConfiguration.USER_ADMIN   + "', '" + DataConfiguration.ROLE_FLIGHT + "')")
                .executeUpdate();
        em.createNativeQuery("INSERT INTO user_roles (username, role)" +
                "VALUES ('" + DataConfiguration.USER_ADMIN   + "', '" + DataConfiguration.ROLE_AIRPORT + "')")
                .executeUpdate();
        em.createNativeQuery("INSERT INTO user_roles (username, role)" +
                "VALUES ('" + DataConfiguration.USER_FLIGHT  + "', '" + DataConfiguration.ROLE_FLIGHT + "')")
                .executeUpdate();
        em.createNativeQuery("INSERT INTO user_roles (username, role)" +
                "VALUES ('" + DataConfiguration.USER_AIRPORT + "', '" + DataConfiguration.ROLE_AIRPORT + "')")
                .executeUpdate();
    }

    /*
         * Bypass parameters to facade for Destination
         *
         * @param airport code
         * @param airport name
         * @param country
         *
         * @return created entity DTO
         */
    private DestinationSimpleDTO createDestination(String name, String city, String country) {

        DestinationCreateDTO d = new DestinationCreateDTO();
        d.setName(name);
        d.setCity(city);
        d.setCountry(country);

        Long id = destinationFacade.create(d);
        return destinationFacade.findById(id);
    }

    /*
     * Bypass parameters to facade for Airplane
     *
     * @param airplane name
     * @param type of airplane
     * @param capacity
     *
     * @return created entity DTO
     */
    private AirplaneDTO createAirplane(String name, AirplaneType type, int capacity) {

        AirplaneCreateDTO a = new AirplaneCreateDTO();
        a.setName(name);
        a.setType(type);
        a.setCapacity(capacity);

        Long id = airplaneFacade.createAirplane(a);
        return airplaneFacade.getAirplane(id);
    }

    /*
     * Bypass parameters to facade for Steward
     *
     * @param business id
     * @param first name
     * @param last name
     *
     * @return created entity DTO
     */
    private StewardDTO createSteward(String firstName, String lastName) {

        StewardCreateDTO s = new StewardCreateDTO();
        s.setFirstName(firstName);
        s.setLastName(lastName);

        Long id = stewardFacade.createSteward(s);
        return stewardFacade.getSteward(id);
    }

    /*
     * Bypass parameters to facade for Steward
     *
     * @param international
     * @param departure time
     * @param arrival time
     * @param set of stewards
     * @param departure destination
     * @param arrival destination
     *
     * @return created entity DTO
     */
    private FlightDTO createFlight(Boolean international, Date departure, Date arrival,
            Set<StewardSimpleDTO> stewards, AirplaneDTO airplane, DestinationSimpleDTO from,
            DestinationSimpleDTO to) {

        FlightCreateDTO f = new FlightCreateDTO();
        f.setInternational(international);
        f.setDeparture(departure);
        f.setArrival(arrival);
        f.setAirplane(airplane);
        f.setFrom(from);
        f.setTo(to);

        Long id = flightFacade.create(f);

        if (stewards !=null) {
            for (StewardSimpleDTO s : stewards) {
                flightFacade.addSteward(s.getId(), id);
            }
        }
        return flightFacade.getFlight(id);
    }
}
