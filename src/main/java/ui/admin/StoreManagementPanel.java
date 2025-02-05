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

/**
 * Panel for managing stores and their items in the admin interface.
 */
public class StoreManagementPanel extends JPanel {
    private final StoreService storeService;
    private final ItemService itemService;
    private final InventoryService inventoryService;
    private final DefaultTableModel itemModel;

    /**
     * Constructs a new StoreManagementPanel.
     *
     * @param storeService the store service
     * @param itemService the item service
     * @param inventoryService the inventory service
     * @param showAdminDashboard the runnable to show the admin dashboard
     */
    public StoreManagementPanel(StoreService storeService, ItemService itemService, InventoryService inventoryService, Runnable showAdminDashboard) {
        this.storeService = storeService;
        this.itemService = itemService;
        this.inventoryService = inventoryService;
        this.itemModel = new DefaultTableModel(new Object[]{"Name", "Price", "Stock"}, 0);

        setLayout(new BorderLayout());

        JPanel storePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JLabel storeLabel = new JLabel("Select Store:");
        JComboBox<String> storeComboBox = new JComboBox<>();
        storePanel.add(storeLabel);
        storePanel.add(storeComboBox);
        add(storePanel, BorderLayout.NORTH);

        JTable itemTable = new JTable(itemModel);
        JScrollPane scrollPane = new JScrollPane(itemTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel managePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

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

        JPanel backPanel = new JPanel(new GridBagLayout());
        JButton backButton = new JButton("Back to Dashboard");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        backPanel.add(backButton, gbc);
        backButton.addActionListener(e -> showAdminDashboard.run());

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(managePanel, BorderLayout.CENTER);
        southPanel.add(backPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);

        loadStores(storeComboBox);

        storeComboBox.addActionListener(e -> {
            String selectedStore = (String) storeComboBox.getSelectedItem();
            if (selectedStore != null) {
                loadItems(selectedStore);
            }
        });

        updatePriceButton.addActionListener(e -> {
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow != -1) {
                String itemName = (String) itemModel.getValueAt(selectedRow, 0);
                double newPrice = Double.parseDouble(priceField.getText());
                updateItemPrice(itemName, newPrice);
                loadItems((String) storeComboBox.getSelectedItem());
            }
        });

        updateStockButton.addActionListener(e -> {
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow != -1) {
                String itemName = (String) itemModel.getValueAt(selectedRow, 0);
                int newStock = Integer.parseInt(stockField.getText());
                updateItemStock(itemName, newStock);
                loadItems((String) storeComboBox.getSelectedItem());
            }
        });

        addItemButton.addActionListener(e -> {
            String storeName = (String) storeComboBox.getSelectedItem();
            String itemName = itemNameField.getText();
            double itemPrice = Double.parseDouble(itemPriceField.getText());
            int itemStock = Integer.parseInt(itemStockField.getText());
            addItem(storeName, itemName, itemPrice, itemStock);
            loadItems(storeName);
        });

        deleteItemButton.addActionListener(e -> {
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow != -1) {
                String itemName = (String) itemModel.getValueAt(selectedRow, 0);
                deleteItem(itemName);
                loadItems((String) storeComboBox.getSelectedItem());
            }
        });
    }

    /**
     * Loads existing stores into the combo box.
     *
     * @param storeComboBox the combo box to load stores into
     */
    private void loadStores(JComboBox<String> storeComboBox) {
        storeComboBox.removeAllItems();
        List<StoreEntity> stores = storeService.findAllStores();
        for (StoreEntity store : stores) {
            storeComboBox.addItem(store.getName());
        }
    }

    /**
     * Loads items of the selected store into the table model.
     *
     * @param storeName the name of the selected store
     */
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

    /**
     * Updates the price of an item.
     *
     * @param itemName the name of the item
     * @param newPrice the new price of the item
     */
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

    /**
     * Updates the stock of an item.
     *
     * @param itemName the name of the item
     * @param newStock the new stock of the item
     */
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

    /**
     * Adds a new item to the store's inventory.
     *
     * @param storeName the name of the store
     * @param itemName the name of the item
     * @param itemPrice the price of the item
     * @param itemStock the stock of the item
     */
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

    /**
     * Deletes an item from the store's inventory.
     *
     * @param itemName the name of the item
     */
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