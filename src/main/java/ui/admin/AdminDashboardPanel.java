// src/main/java/ui/admin/AdminDashboardPanel.java
package ui.admin;

import user.UserService;

import javax.swing.*;
import java.awt.*;

public class AdminDashboardPanel extends JPanel {

    public AdminDashboardPanel(UserService userService, Runnable onLogout, Runnable showWhitelistManagement, Runnable showInventoryManagement, Runnable showStoreManagement, Runnable showItemManagement, Runnable showAdminCreateStore, Runnable showAdminUserAccess) {
        setLayout(new GridLayout(0, 1));

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> onLogout.run());
        add(logoutButton);

        JButton whitelistButton = new JButton("Manage Whitelist");
        whitelistButton.addActionListener(e -> showWhitelistManagement.run());
        add(whitelistButton);

        JButton inventoryButton = new JButton("Manage Inventory");
        inventoryButton.addActionListener(e -> showInventoryManagement.run());
        add(inventoryButton);

        JButton storeButton = new JButton("Manage Store");
        storeButton.addActionListener(e -> showStoreManagement.run());
        add(storeButton);

        JButton itemButton = new JButton("Manage Items");
        itemButton.addActionListener(e -> showItemManagement.run());
        add(itemButton);

        JButton createStoreButton = new JButton("Create Store");
        createStoreButton.addActionListener(e -> showAdminCreateStore.run());
        add(createStoreButton);

        JButton userAccessButton = new JButton("User Access");
        userAccessButton.addActionListener(e -> showAdminUserAccess.run());
        add(userAccessButton);
    }
}