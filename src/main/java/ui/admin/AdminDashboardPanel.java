// src/main/java/ui/admin/AdminDashboardPanel.java
package ui.admin;

import user.UserService;

import javax.swing.*;
import java.awt.*;

public class AdminDashboardPanel extends JPanel {

    public AdminDashboardPanel(UserService userService, Runnable onLogout, Runnable showWhitelistManagement, Runnable showStoreManagement, Runnable showAdminCreateStore, Runnable showAdminUserAccess) {
        setLayout(new GridLayout(0, 1));

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