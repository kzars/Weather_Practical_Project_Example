package org.example.location;

import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;

import java.util.List;

public interface LocationService {
    void addLocation(Location location) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException;
    List<Location> getAllLocations();

    Location findById(String id);
}
