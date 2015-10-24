package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Destination;
import java.util.List;
import java.util.Set;

/**
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
public interface DestinationDao {

    void create(Destination destination);

    Destination findById(Long id);

    /**
     * Find all destinations in specified country
     *
     * @return list of destination identifiers
     */
    Set<Destination> findByCountry(String country);

    Set<Destination> findAll();

    void update(Destination destination);

    void delete(Destination destination);

}