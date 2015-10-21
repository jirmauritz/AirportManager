package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Airplane;

import java.util.Set;

/**
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
public interface AirplaneDao {

    void create();

    Airplane findById(Long id);

    Set<Airplane> findAll();

    void update(Airplane airplane);

    void delete(Airplane airplane);

}
