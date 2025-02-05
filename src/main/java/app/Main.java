package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ui.MainFrame;

import javax.swing.SwingUtilities;

/**
 * Main class for the application.
 */
public class Main {

    /**
     * Main method to start the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SessionFactory sessionFactory = null;

        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            try (Session session = sessionFactory.openSession()) {
                System.out.println("Connexion à la base de données réussie !");
                session.beginTransaction();
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        }

        SessionFactory finalSessionFactory = sessionFactory;
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(finalSessionFactory);
            mainFrame.setVisible(true);
        });
    }
}