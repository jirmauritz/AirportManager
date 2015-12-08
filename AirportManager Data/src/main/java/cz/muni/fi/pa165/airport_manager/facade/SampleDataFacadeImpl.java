package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.entity.Steward;
import cz.muni.fi.pa165.airport_manager.service.AirplaneService;
import cz.muni.fi.pa165.airport_manager.service.DestinationService;
import cz.muni.fi.pa165.airport_manager.service.FlightService;
import cz.muni.fi.pa165.airport_manager.service.StewardService;
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
    StewardService stewardService;
    private @Autowired
    FlightService flightService;
    private @Autowired
    DestinationService destinationService;
    private @Autowired
    AirplaneService airplaneService;

    @Override
    public void loadData() {

        // destinations
        Destination SHA430 = createDestination("SHA430", "Shanghai", "China");
        Destination KAR735 = createDestination("KAR735", "Karachi", "Pakistan");
        Destination LAG255 = createDestination("LAG255", "Lagos", "Nigeria");
        Destination DEL532 = createDestination("DEL532", "Delhi", "India");
        Destination IST676 = createDestination("IST676", "Istanbul", "Turkey");
        Destination TOK639 = createDestination("TOK639", "Tokyo", "Japan");
        Destination TIA416 = createDestination("TIA416", "Tianjin", "China");
        Destination GUA103 = createDestination("GUA103", "Guangzhou", "China");
        Destination MUM180 = createDestination("MUM180", "Mumbai", "India");
        Destination MOS673 = createDestination("MOS673", "Moscow", "Russia");
        Destination SAP339 = createDestination("SA339", "São Paulo", "Brazil");
        Destination BEI973 = createDestination("BEI973", "Beijing", "China");
        Destination SHE785 = createDestination("SHE785", "Shenzhen", "China");
        Destination SEO471 = createDestination("SEO471", "Seoul", "South Korea");
        Destination JAK487 = createDestination("JAK487", "Jakarta", "Indonesia");
        Destination LAH638 = createDestination("LAH638", "Lahore", "Pakistan");
        Destination KIN411 = createDestination("KIN411", "Kinshasa", "Democratic Republic of the Congo");
        Destination CAI346 = createDestination("CAI346", "Cairo", "Egypt");
        Destination MEX134 = createDestination("MEX134", "Mexico City", "Mexico");
        Destination LIM467 = createDestination("LIM467", "Lima", "Peru");
        Destination NEW828 = createDestination("NEW828", "New York City", "United States");

        // airplanes
        Airplane jumbo = createAirplane("Boeing 747 Jumbo", "Economy", 600);
        Airplane vulture = createAirplane("Boeing 747 Vulture", "Economy", 427);
        Airplane cardinal = createAirplane("Boeing 747 Cardinal", "Business", 427);
        Airplane jay = createAirplane("Airbus A320 Jay", "First", 200);
        Airplane wren = createAirplane("Airbus A320 Wren", "Economy", 525);

        // stewards
        Steward carman = createSteward(5847L, "Carman", "Parlier");
        Steward rose = createSteward(8055L, "Rose", "Fosdick");
        Steward keisha = createSteward(1595L, "Keisha", "Mak");
        Steward mamie = createSteward(7326L, "Mamie", "Mund");
        Steward deane = createSteward(6416L, "Deane", "Cassady");
        Steward rhea = createSteward(8519L, "Rhea", "Foley");
        Steward sean = createSteward(1601L, "Sean", "Neubert");
        Steward gerardo = createSteward(7582L, "Gerardo", "Williford");
        Steward ema = createSteward(1904L, "Ema", "Holder");
        Steward lavette = createSteward(5931L, "Lavette", "Gully");
        Steward aracely = createSteward(6330L, "Aracely", "Dimas");
        
        // crews
        Set<Steward> crew1 = new HashSet<>();
        crew1.add(carman);
        crew1.add(rose);
        crew1.add(keisha);
        crew1.add(mamie);
        
        Set<Steward> crew2 = new HashSet<>();
        crew2.add(deane);
        crew2.add(rhea);
        crew2.add(sean);
        crew2.add(gerardo);
        
        Set<Steward> crew3 = new HashSet<>();
        crew3.add(ema);
        crew3.add(lavette);
        crew3.add(aracely);
        
        // flights
        Flight f1 = createFlight(true, new Date(1451664000L), new Date(1451757600L), crew1, jumbo, SHA430, KAR735);
        Flight f2 = createFlight(false, new Date(1451736000L), new Date(1451777400L), crew2, cardinal, SHA430, GUA103);
        Flight f3 = createFlight(true, new Date(1451734200L), new Date(1451755800L), crew3, jay, MOS673, SEO471);
        Flight f4 = createFlight(true, new Date(1451896200L), new Date(1451911500L), crew1, vulture, LIM467, SAP339);
        Flight f5 = createFlight(true, new Date(1451997900L), new Date(1452037500L), crew2, jay, KIN411, MUM180);
        Flight f6 = createFlight(true, new Date(1452145500L), new Date(1452169200L), crew3, wren, SEO471, SHE785);
        Flight f7 = createFlight(true, new Date(1452164400L), new Date(1452189600L), crew1, jumbo, IST676, LAG255);
        Flight f8 = createFlight(true, new Date(1452168000L), new Date(1452182400L), crew2, cardinal, NEW828, MEX134);
        Flight f9 = createFlight(true, new Date(1452312000L), new Date(1452394800L), crew3, vulture, DEL532, TOK639);
        Flight f10 = createFlight(true, new Date(1452668400L), new Date(1452715200L), crew1, jay, BEI973, JAK487);
    }
    
    /*
     * Bypass parameters to service for entity Destination
     *
     * @param airport code
     * @param airport name
     * @param country
     *
     * @return created entity    
     */
    private Destination createDestination(String code, String name, String country) {
        Destination d = new Destination(code, name, country);
        destinationService.create(d);
        return d;
    }

    /*
     * Bypass parameters to service for entity Airplane
     *
     * @param airplane name
     * @param type of airplane
     * @param capacity
     *
     * @return created entity 
     */
    private Airplane createAirplane(String name, String type, int capacity) {
        Airplane a = new Airplane(name, type, capacity);
        airplaneService.create(a);
        return a;
    }

    /*
     * Bypass parameters to service for entity Steward
     *
     * @param business id
     * @param first name
     * @param last name
     *
     * @return created entity 
     */
    private Steward createSteward(Long businessId, String firstName, String lastName) {
        Steward s = new Steward(businessId, firstName, lastName, null);
        stewardService.createSteward(s);
        return s;
    }

    /*
     * Bypass parameters to service for entity Steward
     *
     * @param international
     * @param departure time
     * @param arrival time
     * @param set of stewards
     * @param departure destination
     * @param arrival destination
     *
     * @return created entity 
     */
    private Flight createFlight(Boolean international, Date departure, Date arrival, Set<Steward> stewards,
        Airplane airplane, Destination from, Destination to) {
        Flight f = new Flight(international, departure, arrival, stewards, airplane, from, to);
        flightService.create(f);
        return f;
    }
}
