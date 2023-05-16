package org.example.location;



import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import jakarta.transaction.SystemException;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class LocationDaoImpl implements LocationDao {

    private Session session;

    public LocationDaoImpl() {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Location location) throws SystemException {
        Transaction transaction = null;
        try {
            transaction =  session.beginTransaction();
            session.persist(location);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Location findById(String id) {
        return session.get(Location.class, id);
    }

    @Override
    public List<Location> findAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Location> query = builder.createQuery(Location.class);
        query.from(Location.class);
        return session.createQuery(query).getResultList();
    }
}
