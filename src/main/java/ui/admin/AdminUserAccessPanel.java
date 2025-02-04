// src/main/java/ui/admin/AdminUserAccessPanel.java
package ui.admin;

import user.UserService;
import user.entity.UserEntity;
import user.entity.RoleEnum;
import store.StoreService;
import store.entity.StoreEntity;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class AdminUserAccessPanel extends JPanel {
    private final UserService userService;
    private final StoreService storeService;

    public AdminUserAccessPanel(UserService userService, StoreService storeService, Runnable showAdminDashboard) {
        this.userService = userService;
        this.storeService = storeService;

        setLayout(new BorderLayout());

        // Back button
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> showAdminDashboard.run());
        add(backButton, BorderLayout.SOUTH);

        // User and store selection form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Select User:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(userLabel, gbc);

        JComboBox<String> userComboBox = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(userComboBox, gbc);

        JLabel storeLabel = new JLabel("Select Store:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(storeLabel, gbc);

        JComboBox<String> storeComboBox = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(storeComboBox, gbc);

        JButton assignButton = new JButton("Assign to Store");
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(assignButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Load users and stores
        loadUsers(userComboBox);
        loadStores(storeComboBox);

        // Assign button action
        assignButton.addActionListener(e -> {
            String selectedUserEmail = (String) userComboBox.getSelectedItem();
            String selectedStoreName = (String) storeComboBox.getSelectedItem();

            if (selectedUserEmail != null && selectedStoreName != null) {
                UserEntity user = userService.findUserByEmail(selectedUserEmail);
                List<StoreEntity> stores = storeService.findAllStores();
                for (StoreEntity store : stores) {
                    if (store.getName().equals(selectedStoreName)) {
                        user.setRole(RoleEnum.ADMIN);
                        userService.updateUser(user);
                        JOptionPane.showMessageDialog(this, "User assigned to store and role updated to admin", "Success", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
            }
        });
    }

    private void loadUsers(JComboBox<String> userComboBox) {
        List<UserEntity> users = userService.findAllUsers();
        for (UserEntity user : users) {
            if (user.getRole() == RoleEnum.EMPLOYEE) {
                userComboBox.addItem(user.getEmail());
            }
        }
    }

    private void loadStores(JComboBox<String> storeComboBox) {
        List<StoreEntity> stores = storeService.findAllStores();
        for (StoreEntity store : stores) {
            storeComboBox.addItem(store.getName());
        }
    }
}