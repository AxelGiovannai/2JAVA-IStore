// src/main/java/ui/admin/AdminUserAccessPanel.java
package ui.admin;

import user.UserService;
import user.entity.UserEntity;
import user.entity.RoleEnum;
import store.StoreService;
import store.entity.StoreEntity;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminUserAccessPanel extends JPanel {
    private final UserService userService;
    private final StoreService storeService;
    private final DefaultTableModel userModel;

    public AdminUserAccessPanel(UserService userService, StoreService storeService, Runnable showAdminDashboard) {
        this.userService = userService;
        this.storeService = storeService;
        this.userModel = new DefaultTableModel(new Object[]{"Email", "Pseudo", "Store"}, 0);

        setLayout(new BorderLayout());

        // Back button
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> showAdminDashboard.run());
        add(backButton, BorderLayout.SOUTH);

        // User table
        JTable userTable = new JTable(userModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        // User management form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel storeLabel = new JLabel("Select Store:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(storeLabel, gbc);

        JComboBox<String> storeComboBox = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(storeComboBox, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JButton assignButton = new JButton("Assign to Store");
        JButton unassignButton = new JButton("Unassign from Store");
        JButton deleteButton = new JButton("Delete User");
        buttonPanel.add(assignButton);
        buttonPanel.add(unassignButton);
        buttonPanel.add(deleteButton);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.NORTH);

        // Load users and stores
        loadUsers();
        loadStores(storeComboBox);

        // Assign button action
        assignButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String selectedUserEmail = (String) userModel.getValueAt(selectedRow, 0);
                String selectedStoreName = (String) storeComboBox.getSelectedItem();

                if (selectedUserEmail != null && selectedStoreName != null) {
                    UserEntity user = userService.findUserByEmail(selectedUserEmail);
                    StoreEntity store = storeService.findStoreByName(selectedStoreName);
                    if (store != null) {
                        user.setStore(store);
                        userService.updateUser(user);
                        loadUsers(); // Refresh the user list
                    }
                }
            }
        });

        // Unassign button action
        unassignButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String selectedUserEmail = (String) userModel.getValueAt(selectedRow, 0);

                if (selectedUserEmail != null) {
                    UserEntity user = userService.findUserByEmail(selectedUserEmail);
                    user.setStore(null);
                    userService.updateUser(user);
                    loadUsers(); // Refresh the user list
                }
            }
        });

        // Delete button action
        deleteButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String selectedUserEmail = (String) userModel.getValueAt(selectedRow, 0);

                if (selectedUserEmail != null) {
                    UserEntity user = userService.findUserByEmail(selectedUserEmail);
                    userService.deleteUser(user);
                    loadUsers(); // Refresh the user list
                }
            }
        });
    }

    private void loadUsers() {
        userModel.setRowCount(0);
        List<UserEntity> users = userService.findAllUsers();
        for (UserEntity user : users) {
            String storeName = "none";
            if (user.getStore() != null) {
                storeName = user.getStore().getName();
            }
            userModel.addRow(new Object[]{user.getEmail(), user.getPseudo(), storeName});
        }
    }

    private void loadStores(JComboBox<String> storeComboBox) {
        storeComboBox.removeAllItems();
        List<StoreEntity> stores = storeService.findAllStores();
        for (StoreEntity store : stores) {
            storeComboBox.addItem(store.getName());
        }
    }
}