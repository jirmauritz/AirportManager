package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.DestinationCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationSimpleDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightSimpleDTO;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.service.DestinationService;
import cz.muni.fi.pa165.airport_manager.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Set;

/**
 * Implementation of the DestinationFacade.
 * 
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */

@Facade
public class DestinationFacadeImpl implements DestinationFacade {
	
	@Autowired
	private DestinationService destinationService;

	@Autowired
    private MappingService mappingService;
	
	@Override
	public Long create(DestinationCreateDTO destination) {
		Objects.requireNonNull(destination);
		return destinationService.create(mappingService.mapTo(destination, Destination.class));
	}

	@Override
	public void delete(Long id) {
		Objects.requireNonNull(id);
		destinationService.delete(id);
	}

	@Override
	public DestinationSimpleDTO findById(Long id) {
		Objects.requireNonNull(id);
		return mappingService.mapTo(destinationService.findById(id), DestinationSimpleDTO.class);
	}

	@Override
	public DestinationSimpleDTO findByAirportCode(String code) {
		Objects.requireNonNull(code);
		return mappingService.mapTo(destinationService.findByAirportCode(code), DestinationSimpleDTO.class);
	}

	@Override
	public Set<DestinationSimpleDTO> findByCountry(String country) {
		Objects.requireNonNull(country);
		return mappingService.mapTo(destinationService.findByCountry(country), DestinationSimpleDTO.class);
	}

	@Override
	public Set<DestinationSimpleDTO> findAll() {
		return mappingService.mapTo(destinationService.findAll(), DestinationSimpleDTO.class);
	}

	@Override
	public void update(DestinationSimpleDTO destination) {
		Objects.requireNonNull(destination);

		// map to destination entity
		Destination destinationToUpdate = mappingService.mapTo(destination, Destination.class);

		destinationService.update(destinationToUpdate);
	}

	@Override
	public Set<FlightSimpleDTO> getFlightsByDestinations (Long destinationId){
		return  mappingService.mapTo(destinationService.getFlightsByDestinations(destinationId),FlightSimpleDTO.class);
	}
}
