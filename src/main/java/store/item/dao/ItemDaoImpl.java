// src/main/java/store/item/dao/ItemDaoImpl.java
package store.item.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import store.entity.ItemEntity;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    private final SessionFactory sessionFactory;

    public ItemDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(ItemEntity item) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(item);
            transaction.commit();
        }
    }

    @Override
    public ItemEntity findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(ItemEntity.class, id);
        }
    }

    @Override
    public List<ItemEntity> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ItemEntity", ItemEntity.class).list();
        }
    }

    @Override
    public void update(ItemEntity item) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(item);
            transaction.commit();
        }
    }

    @Override
    public void delete(ItemEntity item) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(item);
            transaction.commit();
        }
    }
}