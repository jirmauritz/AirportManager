package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.dao.StewardDao;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.entity.Steward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

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
    public Steward createSteward(Steward steward) {
        Objects.requireNonNull(steward);

        if (steward.getId() != null) {
            throw new IllegalStateException("Steward must not have id set.");
        }
        if (steward.getBusinessId() != null) {
            throw new IllegalStateException("Steward must not have business id set.");
        }
        if (steward.getFirstName() != null && !steward.getFlights().isEmpty()) {
            throw new IllegalStateException("Steward must not have any flight assigned.");
        }
        if (steward.getFirstName() == null || steward.getFirstName().isEmpty()) {
            throw new IllegalStateException("Steward must have first name set.");
        }
        if (steward.getLastName() == null || steward.getLastName().isEmpty()) {
            throw new IllegalStateException("Steward must have last name set.");
        }

        stewardDao.create(steward);
        steward.setBusinessId(steward.getId());
        stewardDao.update(steward);

        return this.findSteward(steward.getId());
    }

    @Override
    public Steward findSteward(Long id) {
        Objects.requireNonNull(id);
        return stewardDao.findById(id);
    }

    @Override
    public Set<Steward> findAllStewards() {
        return stewardDao.findAll();
    }

    @Override
    public Steward updateSteward(Steward steward) throws IllegalStateException {
        Objects.requireNonNull(steward);
        Objects.requireNonNull(steward.getId());

        final Steward actualSteward = this.findSteward(steward.getId());
        if (!actualSteward.getBusinessId().equals(steward.getBusinessId())) {
            throw new IllegalStateException("Steward has changed businessID. Such change is not allowed.");
        }
        if (steward.getFirstName() == null || steward.getFirstName().isEmpty()) {
            throw new IllegalStateException("Steward must have first name set.");
        }
        if (steward.getLastName() == null || steward.getLastName().isEmpty()) {
            throw new IllegalStateException("Steward must have last name set.");
        }

        stewardDao.update(steward);

        return this.findSteward(steward.getId());
    }

    @Override
    public boolean deleteSteward(Steward steward) {
        Objects.requireNonNull(steward);
        stewardDao.delete(steward);
        return true;
    }

    @Override
    public boolean isAvailable(
            Long id,
            final Date start,
            final Date end
    ) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        final Steward actualSteward = this.findSteward(id);

//        boolean notAvailable = actualSteward.getFlights().stream()
//                .anyMatch( (Flight flight) ->
//                        (end  .after (flight.getDeparture()) && end  .before(flight.getArrival())) ||
//                        (start.after (flight.getDeparture()) && start.before(flight.getArrival())) ||
//                        (start.before(flight.getDeparture()) && end  .after (flight.getArrival()))
//                );

        for (Flight flight : actualSteward.getFlights()) {
            if (
                    (end  .after (flight.getDeparture()) && end  .before(flight.getArrival())) ||
                    (start.after (flight.getDeparture()) && start.before(flight.getArrival())) ||
                    (start.before(flight.getDeparture()) && end  .after (flight.getArrival()))
            ) {
                return false;
            }
        }
        return true;
    }
}
