package store.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import store.entity.StoreEntity;
import java.util.List;

public class StoreDaoImpl implements StoreDao {
    private final SessionFactory sessionFactory;

    public StoreDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Saves a store entity.
     *
     * @param store the store entity to save
     */
    @Override
    public void save(StoreEntity store) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(store);
            transaction.commit();
        }
    }

    /**
     * Finds a store entity by its ID.
     *
     * @param id the ID of the store entity
     * @return the found store entity
     */
    @Override
    public StoreEntity findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(StoreEntity.class, id);
        }
    }

    /**
     * Finds all store entities.
     *
     * @return a list of all store entities
     */
    @Override
    public List<StoreEntity> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM StoreEntity", StoreEntity.class).list();
        }
    }

    /**
     * Updates a store entity.
     *
     * @param store the store entity to update
     */
    @Override
    public void update(StoreEntity store) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(store);
            transaction.commit();
        }
    }

    /**
     * Deletes a store entity.
     *
     * @param store the store entity to delete
     */
    @Override
    public void delete(StoreEntity store) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(store);
            transaction.commit();
        }
    }

    /**
     * Refreshes a store entity.
     *
     * @param store the store entity to refresh
     */
    @Override
    public void refresh(StoreEntity store) {
        try (Session session = sessionFactory.openSession()) {
            session.refresh(store);
        }
    }

    /**
     * Checks if a store entity exists by its ID.
     *
     * @param id the ID of the store entity
     * @return true if the store entity exists, false otherwise
     */
    @Override
    public boolean exists(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(StoreEntity.class, id) != null;
        }
    }
}