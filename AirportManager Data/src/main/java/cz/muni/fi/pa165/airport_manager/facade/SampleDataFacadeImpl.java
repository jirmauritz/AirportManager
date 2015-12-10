package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.AirplaneCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationSimpleDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardSimpleDTO;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Override
    public void loadData() {

        // destinations
        DestinationSimpleDTO SHA430 = createDestination("SHA430", "Shanghai", "China");
        DestinationSimpleDTO KAR735 = createDestination("KAR735", "Karachi", "Pakistan");
        DestinationSimpleDTO LAG255 = createDestination("LAG255", "Lagos", "Nigeria");
        DestinationSimpleDTO DEL532 = createDestination("DEL532", "Delhi", "India");
        DestinationSimpleDTO IST676 = createDestination("IST676", "Istanbul", "Turkey");
        DestinationSimpleDTO TOK639 = createDestination("TOK639", "Tokyo", "Japan");
        DestinationSimpleDTO TIA416 = createDestination("TIA416", "Tianjin", "China");
        DestinationSimpleDTO GUA103 = createDestination("GUA103", "Guangzhou", "China");
        DestinationSimpleDTO MUM180 = createDestination("MUM180", "Mumbai", "India");
        DestinationSimpleDTO MOS673 = createDestination("MOS673", "Moscow", "Russia");
        DestinationSimpleDTO SAP339 = createDestination("SA339", "São Paulo", "Brazil");
        DestinationSimpleDTO BEI973 = createDestination("BEI973", "Beijing", "China");
        DestinationSimpleDTO SHE785 = createDestination("SHE785", "Shenzhen", "China");
        DestinationSimpleDTO SEO471 = createDestination("SEO471", "Seoul", "South Korea");
        DestinationSimpleDTO JAK487 = createDestination("JAK487", "Jakarta", "Indonesia");
        DestinationSimpleDTO LAH638 = createDestination("LAH638", "Lahore", "Pakistan");
        DestinationSimpleDTO KIN411 = createDestination("KIN411", "Kinshasa", "Democratic Republic of the Congo");
        DestinationSimpleDTO CAI346 = createDestination("CAI346", "Cairo", "Egypt");
        DestinationSimpleDTO MEX134 = createDestination("MEX134", "Mexico City", "Mexico");
        DestinationSimpleDTO LIM467 = createDestination("LIM467", "Lima", "Peru");
        DestinationSimpleDTO NEW828 = createDestination("NEW828", "New York City", "United States");

        // airplanes
        AirplaneDTO jumbo = createAirplane("Boeing 747 Jumbo", "Economy", 600);
        AirplaneDTO vulture = createAirplane("Boeing 747 Vulture", "Economy", 427);
        AirplaneDTO cardinal = createAirplane("Boeing 747 Cardinal", "Business", 427);
        AirplaneDTO jay = createAirplane("Airbus A320 Jay", "First", 200);
        AirplaneDTO wren = createAirplane("Airbus A320 Wren", "Economy", 525);

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
        FlightDTO f1 = createFlight(true, new Date(1451664000L), new Date(1451757600L), crew1, jumbo, SHA430, KAR735);
        FlightDTO f2 = createFlight(false, new Date(1451736000L), new Date(1451777400L), crew2, cardinal, SHA430, GUA103);
        FlightDTO f3 = createFlight(true, new Date(1451734200L), new Date(1451755800L), crew3, jay, MOS673, SEO471);
        FlightDTO f4 = createFlight(true, new Date(1451896200L), new Date(1451911500L), crew1, vulture, LIM467, SAP339);
        FlightDTO f5 = createFlight(true, new Date(1451997900L), new Date(1452037500L), crew2, jay, KIN411, MUM180);
        FlightDTO f6 = createFlight(true, new Date(1452145500L), new Date(1452169200L), crew3, wren, SEO471, SHE785);
        FlightDTO f7 = createFlight(true, new Date(1452164400L), new Date(1452189600L), crew1, jumbo, IST676, LAG255);
        FlightDTO f8 = createFlight(true, new Date(1452168000L), new Date(1452182400L), crew2, cardinal, NEW828, MEX134);
        FlightDTO f9 = createFlight(true, new Date(1452312000L), new Date(1452394800L), crew3, vulture, DEL532, TOK639);
        FlightDTO f10 = createFlight(true, new Date(1452668400L), new Date(1452715200L), crew1, jay, BEI973, JAK487);
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
    private AirplaneDTO createAirplane(String name, String type, int capacity) {

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
        FlightDTO f2 = flightFacade.getFlight(id);
        f2.setStewards(stewards);

        return f2;
    }
}
