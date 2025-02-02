package whitelist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import whitelist.entity.WhitelistedEmailEntity;
import java.util.List;

public class WhitelistDaoImpl implements WhitelistDao {
    private final SessionFactory sessionFactory;

    public WhitelistDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addEmail(WhitelistedEmailEntity emailEntity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(emailEntity);
            transaction.commit();
        }
    }

    @Override
    public WhitelistedEmailEntity findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<WhitelistedEmailEntity> query = session.createQuery("FROM WhitelistedEmailEntity WHERE email = :email", WhitelistedEmailEntity.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        }
    }

    @Override
    public List<WhitelistedEmailEntity> getAllEmails() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM WhitelistedEmailEntity", WhitelistedEmailEntity.class).list();
        }
    }
}