package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Configuration Hibernate
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();


        // Lancer l'interface utilisateur
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(sessionFactory);
            mainFrame.setVisible(true);
        });
    }
}
