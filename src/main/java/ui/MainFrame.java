// src/main/java/ui/MainFrame.java
package ui;

import store.StoreService;
import store.dao.StoreDaoImpl;
import store.inventory.InventoryService;
import store.inventory.dao.InventoryDaoImpl;
import store.item.ItemService;
import store.item.dao.ItemDaoImpl;
import ui.admin.*;
import ui.auth.LoginPanel;
import ui.auth.RegisterPanel;
import user.UserService;
import user.dao.UserDaoImpl;
import whitelist.WhitelistService;
import whitelist.dao.WhitelistDaoImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final SessionFactory sessionFactory;
    private final UserService userService;

    public MainFrame(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.userService = new UserService(new UserDaoImpl(sessionFactory));
        setTitle("IStore Application");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        showMainPanel();
    }

    private void showMainPanel() {
        getContentPane().removeAll();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton registerButton = new JButton("Créer un compte");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(registerButton, gbc);

        JButton loginButton = new JButton("Connexion");
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(loginButton, gbc);

        registerButton.addActionListener(e -> showRegisterPanel());
        loginButton.addActionListener(e -> showLoginPanel());

        add(mainPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showRegisterPanel() {
        WhitelistService whitelistService = new WhitelistService(new WhitelistDaoImpl(sessionFactory));
        getContentPane().removeAll();
        add(new RegisterPanel(whitelistService, this::showMainPanel, userService), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showLoginPanel() {
        getContentPane().removeAll();
        add(new LoginPanel(userService, this::showMainPanel, panel -> showAdminDashboard(), this::showMainPanel, this::showWhitelistManagement, this::showStoreManagement, this::showAdminCreateStore, this::showAdminUserAccess), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showAdminDashboard() {
        getContentPane().removeAll();
        add(new AdminDashboardPanel(userService, this::showMainPanel, this::showWhitelistManagement, this::showStoreManagement, this::showAdminCreateStore, this::showAdminUserAccess), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showWhitelistManagement() {
        WhitelistService whitelistService = new WhitelistService(new WhitelistDaoImpl(sessionFactory));
        getContentPane().removeAll();
        add(new WhitelistManagementPanel(whitelistService, this::showAdminDashboard), BorderLayout.CENTER);
        revalidate();
        repaint();
    }


    private void showStoreManagement() {
        StoreService storeService = new StoreService(new StoreDaoImpl(sessionFactory));
        ItemService itemService = new ItemService(new ItemDaoImpl(sessionFactory));
        InventoryService inventoryService = new InventoryService(new InventoryDaoImpl(sessionFactory));
        getContentPane().removeAll();
        add(new StoreManagementPanel(storeService, itemService, inventoryService, this::showAdminDashboard), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showAdminCreateStore() {
        StoreService storeService = new StoreService(new StoreDaoImpl(sessionFactory));
        InventoryService inventoryService = new InventoryService(new InventoryDaoImpl(sessionFactory));
        getContentPane().removeAll();
        add(new AdminCreateStorePanel(storeService, inventoryService, this::showAdminDashboard), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showAdminUserAccess() {
        StoreService storeService = new StoreService(new StoreDaoImpl(sessionFactory));
        getContentPane().removeAll();
        add(new AdminUserAccessPanel(userService, storeService, this::showAdminDashboard), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(sessionFactory);
            mainFrame.setVisible(true);
        });
    }
}