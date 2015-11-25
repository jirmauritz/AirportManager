package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.dao.DestinationDao;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the (@link DestinationService}.
 * This class uses destinationDao to access the persistence.
 * 
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */

@Service
public class DestinationServiceImpl implements DestinationService {
	
	@Autowired
	private DestinationDao destinationDao;

	@Override
	public Long create(Destination destination) {
		Objects.requireNonNull(destination);
		
		if (destination.getId() != null) {
			throw new IllegalStateException("Destination must not have id set.");
		}
		
		if (destination.getName() == null) {
			throw new IllegalStateException("Destination must have name set.");
		}
		
		if (destination.getCity() == null) {
			throw new IllegalStateException("Destination must have city set.");
		}
		
		if (destination.getCountry() == null) {
			throw new IllegalStateException("Destination must have country set.");
		}
		
		destinationDao.create(destination);
		return destination.getId();
	}

	@Override
	public void update(Destination destination) {
		Objects.requireNonNull(destination);
		Objects.requireNonNull(destination.getId());
		
		destinationDao.update(destination);
	}

	@Override
	public void delete(Destination destination) {
		Objects.requireNonNull(destination);
		Objects.requireNonNull(destination.getId());
		
		destinationDao.delete(destination);
	}

	@Override
	public Destination findById(Long id) {
		Objects.requireNonNull(id);
		
		return destinationDao.findById(id);
	}
	
	@Override
	public Destination findByAirportCode(String code) {
		Objects.requireNonNull(code);
		return destinationDao.findByName(code);
	}

	@Override
	public Set<Destination> findByCountry(String country) {
		Objects.requireNonNull(country);
		return destinationDao.findByCountry(country);
	}

	@Override
	public Set<Destination> findAll() {
		return destinationDao.findAll();
	}
	
}
