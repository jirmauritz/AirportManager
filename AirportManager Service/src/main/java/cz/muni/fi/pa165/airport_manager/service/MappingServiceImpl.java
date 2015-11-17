package cz.muni.fi.pa165.airport_manager.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implementation for MappingService.
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
@Service
public class MappingServiceImpl implements MappingService {
    @Autowired
    private Mapper dozer;

    public  <T> Set<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        Set<T> mappedCollection = new HashSet<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public  <T> T mapTo(Object u, Class<T> mapToClass) {
        return dozer.map(u,mapToClass);
    }
}
