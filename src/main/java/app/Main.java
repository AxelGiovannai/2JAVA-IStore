package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Configuration Hibernate

        // Ouvrir une session
        try (SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); Session session = sessionFactory.openSession()) {
            System.out.println("Connexion à la base de données réussie !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        }

        // Lancer l'interface utilisateur
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
