// src/main/java/ui/admin/StoreManagementPanel.java
package ui.admin;

import store.StoreService;
import store.entity.InventoryEntity;
import store.entity.StoreEntity;
import store.entity.ItemEntity;
import store.item.ItemService;
import store.inventory.InventoryService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StoreManagementPanel extends JPanel {
    private final StoreService storeService;
    private final ItemService itemService;
    private final InventoryService inventoryService;
    private final DefaultTableModel itemModel;

    public StoreManagementPanel(StoreService storeService, ItemService itemService, InventoryService inventoryService, Runnable showAdminDashboard) {
        this.storeService = storeService;
        this.itemService = itemService;
        this.inventoryService = inventoryService;
        this.itemModel = new DefaultTableModel(new Object[]{"Name", "Price", "Stock"}, 0);

        setLayout(new BorderLayout());

        // Store selection
        JPanel storePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JLabel storeLabel = new JLabel("Select Store:");
        JComboBox<String> storeComboBox = new JComboBox<>();
        storePanel.add(storeLabel);
        storePanel.add(storeComboBox);
        add(storePanel, BorderLayout.NORTH);

        // Item table
        JTable itemTable = new JTable(itemModel);
        JScrollPane scrollPane = new JScrollPane(itemTable);
        add(scrollPane, BorderLayout.CENTER);

        // Management panel
        JPanel managePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // First row: New Price and New Stock
        JLabel priceLabel = new JLabel("New Price:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        managePanel.add(priceLabel, gbc);

        JTextField priceField = new JTextField(10);
        priceField.setMinimumSize(new Dimension(100, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        managePanel.add(priceField, gbc);

        JButton updatePriceButton = new JButton("Update Price");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        managePanel.add(updatePriceButton, gbc);

        JLabel stockLabel = new JLabel("New Stock:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        managePanel.add(stockLabel, gbc);

        JTextField stockField = new JTextField(10);
        stockField.setMinimumSize(new Dimension(100, 20));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        managePanel.add(stockField, gbc);

        JButton updateStockButton = new JButton("Update Stock");
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        managePanel.add(updateStockButton, gbc);

        // Second row: Add Item and Delete Item
        JLabel itemNameLabel = new JLabel("Item Name:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        managePanel.add(itemNameLabel, gbc);

        JTextField itemNameField = new JTextField(10);
        itemNameField.setMinimumSize(new Dimension(100, 20));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        managePanel.add(itemNameField, gbc);

        JLabel itemPriceLabel = new JLabel("Item Price:");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        managePanel.add(itemPriceLabel, gbc);

        JTextField itemPriceField = new JTextField(10);
        itemPriceField.setMinimumSize(new Dimension(100, 20));
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        managePanel.add(itemPriceField, gbc);

        JLabel itemStockLabel = new JLabel("Item Stock:");
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        managePanel.add(itemStockLabel, gbc);

        JTextField itemStockField = new JTextField(10);
        itemStockField.setMinimumSize(new Dimension(100, 20));
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        managePanel.add(itemStockField, gbc);

        JButton addItemButton = new JButton("Add Item");
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        managePanel.add(addItemButton, gbc);

        JButton deleteItemButton = new JButton("Delete Item");
        gbc.gridx = 7;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        managePanel.add(deleteItemButton, gbc);

        // Back button panel
        JPanel backPanel = new JPanel(new GridBagLayout());
        JButton backButton = new JButton("Back to Dashboard");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        backPanel.add(backButton, gbc);
        backButton.addActionListener(e -> showAdminDashboard.run());

        // Combine managePanel and backPanel
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(managePanel, BorderLayout.CENTER);
        southPanel.add(backPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);

        // Load stores
        loadStores(storeComboBox);

        // Store selection action
        storeComboBox.addActionListener(e -> {
            String selectedStore = (String) storeComboBox.getSelectedItem();
            if (selectedStore != null) {
                loadItems(selectedStore);
            }
        });

        // Update price action
        updatePriceButton.addActionListener(e -> {
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow != -1) {
                String itemName = (String) itemModel.getValueAt(selectedRow, 0);
                double newPrice = Double.parseDouble(priceField.getText());
                updateItemPrice(itemName, newPrice);
                loadItems((String) storeComboBox.getSelectedItem());
            }
        });

        // Update stock action
        updateStockButton.addActionListener(e -> {
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow != -1) {
                String itemName = (String) itemModel.getValueAt(selectedRow, 0);
                int newStock = Integer.parseInt(stockField.getText());
                updateItemStock(itemName, newStock);
                loadItems((String) storeComboBox.getSelectedItem());
            }
        });

        // Add item action
        addItemButton.addActionListener(e -> {
            String storeName = (String) storeComboBox.getSelectedItem();
            String itemName = itemNameField.getText();
            double itemPrice = Double.parseDouble(itemPriceField.getText());
            int itemStock = Integer.parseInt(itemStockField.getText());
            addItem(storeName, itemName, itemPrice, itemStock);
            loadItems(storeName);
        });

        // Delete item action
        deleteItemButton.addActionListener(e -> {
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow != -1) {
                String itemName = (String) itemModel.getValueAt(selectedRow, 0);
                deleteItem(itemName);
                loadItems((String) storeComboBox.getSelectedItem());
            }
        });
    }

    private void loadStores(JComboBox<String> storeComboBox) {
        storeComboBox.removeAllItems();
        List<StoreEntity> stores = storeService.findAllStores();
        for (StoreEntity store : stores) {
            storeComboBox.addItem(store.getName());
        }
    }

    private void loadItems(String storeName) {
        itemModel.setRowCount(0);
        List<StoreEntity> stores = storeService.findAllStores();
        for (StoreEntity store : stores) {
            if (store.getName().equals(storeName)) {
                InventoryEntity inventory = inventoryService.findInventoryWithItems(store.getInventory().getId());
                List<ItemEntity> items = inventory.getItems();
                for (ItemEntity item : items) {
                    itemModel.addRow(new Object[]{item.getName(), item.getPrice(), item.getStock()});
                }
            }
        }
    }

    private void updateItemPrice(String itemName, double newPrice) {
        List<ItemEntity> items = itemService.findAllItems();
        for (ItemEntity item : items) {
            if (item.getName().equals(itemName)) {
                item.setPrice(newPrice);
                itemService.updateItem(item);
                break;
            }
        }
    }

    private void updateItemStock(String itemName, int newStock) {
        List<ItemEntity> items = itemService.findAllItems();
        for (ItemEntity item : items) {
            if (item.getName().equals(itemName)) {
                item.setStock(newStock);
                itemService.updateItem(item);
                break;
            }
        }
    }

    private void addItem(String storeName, String itemName, double itemPrice, int itemStock) {
        List<StoreEntity> stores = storeService.findAllStores();
        for (StoreEntity store : stores) {
            if (store.getName().equals(storeName)) {
                ItemEntity newItem = new ItemEntity(itemName, itemPrice, itemStock, store.getInventory());
                itemService.saveItem(newItem);
                break;
            }
        }
    }

    private void deleteItem(String itemName) {
        List<ItemEntity> items = itemService.findAllItems();
        for (ItemEntity item : items) {
            if (item.getName().equals(itemName)) {
                itemService.deleteItem(item);
                break;
            }
        }
    }
}