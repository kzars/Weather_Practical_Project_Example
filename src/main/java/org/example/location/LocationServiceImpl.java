package org.example.location;

import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;

import java.util.List;

public class LocationServiceImpl implements LocationService {

    private LocationDao locationDao;

    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public void addLocation(Location location) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException {
        locationDao.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.findAll();
    }

    @Override
    public Location findById(String id) {
        return locationDao.findById(id);
    }
}
