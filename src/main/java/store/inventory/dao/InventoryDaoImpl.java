// src/main/java/store/inventory/dao/InventoryDaoImpl.java
package store.inventory.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import store.entity.InventoryEntity;
import java.util.List;

public class InventoryDaoImpl implements InventoryDao {
    private final SessionFactory sessionFactory;

    public InventoryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(InventoryEntity inventory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(inventory);
            transaction.commit();
        }
    }

    @Override
    public InventoryEntity findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(InventoryEntity.class, id);
        }
    }

    @Override
    public List<InventoryEntity> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM InventoryEntity", InventoryEntity.class).list();
        }
    }

    @Override
    public void update(InventoryEntity inventory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(inventory);
            transaction.commit();
        }
    }

    @Override
    public void delete(InventoryEntity inventory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(inventory);
            transaction.commit();
        }
    }
}