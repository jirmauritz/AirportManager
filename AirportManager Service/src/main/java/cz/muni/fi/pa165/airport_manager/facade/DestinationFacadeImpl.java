package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.DestinationCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationSimpleDTO;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.service.DestinationService;
import cz.muni.fi.pa165.airport_manager.service.MappingService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

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
	public void create(DestinationCreateDTO destination) {
		destinationService.create(mappingService.mapTo(destination, Destination.class));
	}

	@Override
	public void delete(Long id) {
		destinationService.delete(destinationService.findById(id));
	}

	@Override
	public DestinationSimpleDTO findById(Long id) {
		return mappingService.mapTo(destinationService.findById(id), DestinationSimpleDTO.class);
	}

	@Override
	public DestinationSimpleDTO findByAirportCode(String code) {
		return mappingService.mapTo(destinationService.findByAirportCode(code), DestinationSimpleDTO.class);
	}

	@Override
	public Set<DestinationSimpleDTO> findByCountry(String country) {
		return mappingService.mapTo(destinationService.findByCountry(country), DestinationSimpleDTO.class);
	}

	@Override
	public Set<DestinationSimpleDTO> findAll() {
		return mappingService.mapTo(destinationService.findAll(), DestinationSimpleDTO.class);
	}
	
}
