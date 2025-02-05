package store.inventory.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import store.entity.InventoryEntity;

import java.util.List;

/**
 * Implementation of the InventoryDao interface.
 */
public class InventoryDaoImpl implements InventoryDao {
    private final SessionFactory sessionFactory;

    /**
     * Constructs a new InventoryDaoImpl.
     *
     * @param sessionFactory the session factory
     */
    public InventoryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Saves an inventory entity.
     *
     * @param inventory the inventory entity to save
     */
    @Override
    public void save(InventoryEntity inventory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(inventory);
            session.getTransaction().commit();
        }
    }

    /**
     * Finds an inventory entity by its ID.
     *
     * @param id the ID of the inventory entity
     * @return the found inventory entity
     */
    @Override
    public InventoryEntity findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(InventoryEntity.class, id);
        }
    }

    /**
     * Finds all inventory entities.
     *
     * @return a list of all inventory entities
     */
    @Override
    public List<InventoryEntity> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM InventoryEntity", InventoryEntity.class).list();
        }
    }

    /**
     * Updates an inventory entity.
     *
     * @param inventory the inventory entity to update
     */
    @Override
    public void update(InventoryEntity inventory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(inventory);
            session.getTransaction().commit();
        }
    }

    /**
     * Deletes an inventory entity.
     *
     * @param inventory the inventory entity to delete
     */
    @Override
    public void delete(InventoryEntity inventory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(inventory);
            session.getTransaction().commit();
        }
    }

    /**
     * Finds an inventory entity with its items by its ID.
     *
     * @param id the ID of the inventory entity
     * @return the found inventory entity with its items
     */
    @Override
    public InventoryEntity findInventoryWithItems(int id) {
        try (Session session = sessionFactory.openSession()) {
            InventoryEntity inventory = session.get(InventoryEntity.class, id);
            if (inventory != null) {
                inventory.getItems().size();
            }
            return inventory;
        }
    }
}