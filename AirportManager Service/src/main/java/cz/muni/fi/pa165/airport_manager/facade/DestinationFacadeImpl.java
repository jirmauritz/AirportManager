/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.DestinationCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationDTO;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.service.DestinationService;
import cz.muni.fi.pa165.airport_manager.service.MappingService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author xmauritz
 */
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
	public DestinationDTO findById(Long id) {
		return mappingService.mapTo(destinationService.findById(id), DestinationDTO.class);
	}

	@Override
	public DestinationDTO findByAirportCode(String code) {
		return mappingService.mapTo(destinationService.findByAirportCode(code), DestinationDTO.class);
	}

	@Override
	public Set<DestinationDTO> findByCountry(String country) {
		return mappingService.mapTo(destinationService.findByCountry(country), DestinationDTO.class);
	}

	@Override
	public Set<DestinationDTO> findAll() {
		return mappingService.mapTo(destinationService.findAll(), DestinationDTO.class);
	}
	
}
