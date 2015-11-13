package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.dao.DestinationDao;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of the (@link DestinationService}.
 * This class uses destinationDao to access the persistence.
 * 
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */
public class DestinationServiceImpl implements DestinationService {
	
	@Autowired
	private DestinationDao destinationDao;

	@Override
	public void create(Destination destination) {
		destinationDao.create(destination);
	}

	@Override
	public void update(Destination destination) {
		destinationDao.update(destination);
	}

	@Override
	public void delete(Destination destination) {
		destinationDao.delete(destination);
	}

	@Override
	public Destination findById(Long id) {
		return destinationDao.findById(id);
	}
	
	@Override
	public Destination findByAirportCode(String code) {
		return destinationDao.findByName(code);
	}

	@Override
	public Set<Destination> findByCountry(String country) {
		return destinationDao.findByCountry(country);
	}

	@Override
	public Set<Destination> findAll() {
		return destinationDao.findAll();
	}
	
}
