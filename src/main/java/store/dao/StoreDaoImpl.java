// src/main/java/store/dao/StoreDaoImpl.java
package store.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import store.entity.StoreEntity;

import java.util.List;

public class StoreDaoImpl implements StoreDao {
    private final SessionFactory sessionFactory;

    public StoreDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(StoreEntity store) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(store);
            session.getTransaction().commit();
        }
    }

    @Override
    public StoreEntity findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(StoreEntity.class, id);
        }
    }

    @Override
    public List<StoreEntity> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM StoreEntity", StoreEntity.class).list();
        }
    }

    @Override
    public void update(StoreEntity store) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(store);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(StoreEntity store) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(store);
            session.getTransaction().commit();
        }
    }

    @Override
    public void refresh(StoreEntity store) {
        try (Session session = sessionFactory.openSession()) {
            session.refresh(store);
        }
    }

    @Override
    public boolean exists(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(StoreEntity.class, id) != null;
        }
    }
}