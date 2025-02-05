package store.item.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import store.entity.ItemEntity;
import java.util.List;

/**
 * Implementation of the ItemDao interface.
 */
public class ItemDaoImpl implements ItemDao {
    private final SessionFactory sessionFactory;

    /**
     * Constructs a new ItemDaoImpl.
     *
     * @param sessionFactory the session factory
     */
    public ItemDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Saves an item entity.
     *
     * @param item the item entity to save
     */
    @Override
    public void save(ItemEntity item) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(item);
            transaction.commit();
        }
    }

    /**
     * Finds an item entity by its ID.
     *
     * @param id the ID of the item entity
     * @return the found item entity
     */
    @Override
    public ItemEntity findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(ItemEntity.class, id);
        }
    }

    /**
     * Finds all item entities.
     *
     * @return a list of all item entities
     */
    @Override
    public List<ItemEntity> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ItemEntity", ItemEntity.class).list();
        }
    }

    /**
     * Updates an item entity.
     *
     * @param item the item entity to update
     */
    @Override
    public void update(ItemEntity item) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(item);
            transaction.commit();
        }
    }

    /**
     * Deletes an item entity.
     *
     * @param item the item entity to delete
     */
    @Override
    public void delete(ItemEntity item) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(item);
            transaction.commit();
        }
    }
}