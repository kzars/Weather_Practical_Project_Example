package org.example.location;

import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;

import java.util.List;

public interface LocationDao {
    void save(Location location) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException;
    Location findById(String id);
    List<Location> findAll();
}
