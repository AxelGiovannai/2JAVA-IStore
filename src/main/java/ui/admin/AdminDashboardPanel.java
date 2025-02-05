package ui.admin;

import user.UserService;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for the admin dashboard interface.
 */
public class AdminDashboardPanel extends JPanel {

    /**
     * Constructs a new AdminDashboardPanel.
     *
     * @param userService the user service
     * @param onLogout the runnable to execute on logout
     * @param showWhitelistManagement the runnable to show whitelist management
     * @param showStoreManagement the runnable to show store management
     * @param showAdminCreateStore the runnable to show admin create store
     * @param showAdminUserAccess the runnable to show admin user access
     */
    public AdminDashboardPanel(UserService userService, Runnable onLogout, Runnable showWhitelistManagement, Runnable showStoreManagement, Runnable showAdminCreateStore, Runnable showAdminUserAccess) {
        setLayout(new GridLayout(0, 1));
        System.out.println("ca rentre dans AdminDashboardPanel");
        JButton whitelistButton = new JButton("Manage Whitelist");
        whitelistButton.addActionListener(e -> showWhitelistManagement.run());
        add(whitelistButton);

        JButton storeButton = new JButton("Manage Store");
        storeButton.addActionListener(e -> showStoreManagement.run());
        add(storeButton);

        JButton createStoreButton = new JButton("Create Store");
        createStoreButton.addActionListener(e -> showAdminCreateStore.run());
        add(createStoreButton);

        JButton userAccessButton = new JButton("User Access");
        userAccessButton.addActionListener(e -> showAdminUserAccess.run());
        add(userAccessButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> onLogout.run());
        add(logoutButton);
    }
}