package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.service.AirplaneService;
import cz.muni.fi.pa165.airport_manager.service.DestinationService;
import cz.muni.fi.pa165.airport_manager.service.FlightService;
import cz.muni.fi.pa165.airport_manager.service.StewardService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Basin implementation of sample data for AirportManager
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Facade
public class SampleDataFacadeImpl implements SampleDataFacade{

    private @Autowired StewardService     stewardService;
    private @Autowired FlightService      flightService;
    private @Autowired DestinationService destinationService;
    private @Autowired AirplaneService    airplaneService;

    @Override
    public void loadData() {
        //TODO
    }
}
