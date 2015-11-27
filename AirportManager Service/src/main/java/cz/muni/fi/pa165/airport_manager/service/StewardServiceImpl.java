package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.dao.StewardDao;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.entity.Steward;
import cz.muni.fi.pa165.airport_manager.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Basic implementation of steward service
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Service
public class StewardServiceImpl implements StewardService {

    private @Autowired StewardDao stewardDao;

    @Override
    public Long createSteward(Steward steward) {
        Objects.requireNonNull(steward);

        if (steward.getId() != null) {
            throw new IllegalArgumentException("Steward must not have id set.");
        }
        if (steward.getBusinessId() != null) {
            throw new IllegalArgumentException("Steward must not have business id set.");
        }
        if (steward.getFlights() != null && !steward.getFlights().isEmpty()) {
            throw new IllegalArgumentException("Steward must not have any flight assigned.");
        }
        if (steward.getFirstName() == null || steward.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("Steward must have first name set.");
        }
        if (steward.getLastName() == null || steward.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Steward must have last name set.");
        }

        try {
            stewardDao.create(steward);
        } catch (Exception e) {
            throw new DataAccessException("Entity " + steward + " already exists", e);
        }

        return steward.getId();
    }

    @Override
    public Steward findSteward(Long id) {
        Objects.requireNonNull(id);
        try {
            return stewardDao.findById(id);
        } catch (Exception e) {
            throw new DataAccessException("Some error occurred.", e);
        }
    }

    @Override
    public Set<Steward> findAllStewards() {
        try {
            return stewardDao.findAll();
        } catch (Exception e) {
            throw new DataAccessException("Some error occurred.", e);
        }
    }

    @Override
    public void updateSteward(Steward steward) throws IllegalArgumentException {
        Objects.requireNonNull(steward);
        Objects.requireNonNull(steward.getId());

        final Steward actualSteward = this.findSteward(steward.getId());
        if (!actualSteward.getBusinessId().equals(steward.getBusinessId())) {
            throw new IllegalArgumentException("Steward has changed businessID. Such change is not allowed.");
        }
        if (steward.getFirstName() == null || steward.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("Steward must have first name set.");
        }
        if (steward.getLastName() == null || steward.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Steward must have last name set.");
        }

        try {
            stewardDao.update(steward);
        } catch (Exception e) {
            throw new DataAccessException("Some error occurred.", e);
        }
    }

    @Override
    public void deleteSteward(Long id) {
        Objects.requireNonNull(id);
        try {
            stewardDao.delete(this.findSteward(id));
        } catch (Exception e) {
            throw new DataAccessException("Entity with id " + id + " does not exist.", e);
        }
    }

    @Override
    public boolean isAvailable(
            Long id,
            final Date from,
            final Date to
    ) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        if (!to.after(from)) {
            throw new IllegalArgumentException("Invalid time range.");
        }

        return isAvailable(this.findSteward(id), from, to);
    }

    @Override
    public Set<Steward> getAllAvailable(final Date from, final Date to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        if (!to.after(from)) {
            throw new IllegalArgumentException("Invalid time range.");
        }

        final Set<Steward> allStewards = new HashSet<>(this.findAllStewards());

        final Iterator<Steward> i = allStewards.iterator();
        while (i.hasNext()) {
            Steward s = i.next();
            if (!this.isAvailable(s, from, to)){
                i.remove();
            }
        }

        return allStewards;
    }

    private boolean isAvailable(
            final Steward steward,
            final Date from,
            final Date to
    ) {
        for (Flight flight : steward.getFlights()) {
            if (from.before(flight.getArrival()) && to.after(flight.getDeparture())) {
                return false;
            }
        }
        return true;
    }

}
