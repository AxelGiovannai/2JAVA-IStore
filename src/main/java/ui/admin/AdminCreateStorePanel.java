// src/main/java/ui/admin/AdminCreateStorePanel.java
package ui.admin;

import store.StoreService;
import store.entity.StoreEntity;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminCreateStorePanel extends JPanel {
    private final StoreService storeService;
    private final DefaultListModel<String> storeListModel;

    public AdminCreateStorePanel(StoreService storeService, Runnable showAdminDashboard) {
        this.storeService = storeService;
        this.storeListModel = new DefaultListModel<>();

        setLayout(new BorderLayout());

        // Back button
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> showAdminDashboard.run());
        add(backButton, BorderLayout.SOUTH);

        // Store list
        JList<String> storeList = new JList<>(storeListModel);
        JScrollPane scrollPane = new JScrollPane(storeList);
        add(scrollPane, BorderLayout.CENTER);

        // Add store form
        JPanel addStorePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel storeLabel = new JLabel("Store Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        addStorePanel.add(storeLabel, gbc);

        JTextField storeField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        addStorePanel.add(storeField, gbc);

        JButton addButton = new JButton("Add Store");
        gbc.gridx = 1;
        gbc.gridy = 1;
        addStorePanel.add(addButton, gbc);

        JButton deleteButton = new JButton("Delete Store");
        gbc.gridx = 1;
        gbc.gridy = 2;
        addStorePanel.add(deleteButton, gbc);

        add(addStorePanel, BorderLayout.NORTH);

        // Add store button action
        addButton.addActionListener(e -> {
            String storeName = storeField.getText();
            if (!storeName.isEmpty()) {
                try {
                    StoreEntity store = new StoreEntity(storeName);
                    storeService.saveStore(store);
                    storeListModel.addElement(storeName);
                    storeField.setText("");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Delete store button action
        deleteButton.addActionListener(e -> {
            String storeName = storeField.getText();
            if (!storeName.isEmpty()) {
                try {
                    List<StoreEntity> stores = storeService.findAllStores();
                    for (StoreEntity store : stores) {
                        if (store.getName().equals(storeName)) {
                            storeService.deleteStore(store);
                            storeListModel.removeElement(storeName);
                            storeField.setText("");
                            break;
                        }
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Load existing stores
        loadStores();
    }

    private void loadStores() {
        List<StoreEntity> stores = storeService.findAllStores();
        for (StoreEntity store : stores) {
            storeListModel.addElement(store.getName());
        }
    }
}