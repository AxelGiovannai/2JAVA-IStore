package ui.user;

import store.entity.InventoryEntity;
import store.entity.ItemEntity;
import store.inventory.InventoryService;
import store.item.ItemService;
import user.UserService;
import user.entity.UserEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel for viewing and managing inventory in the user interface.
 */
public class InventoryViewPanel extends JPanel {
    private final UserService userService;
    private final ItemService itemService;
    private final InventoryService inventoryService;
    private final UserEntity currentUser;
    private final Runnable showUserManagementPanel;
    private final DefaultTableModel itemModel;

    /**
     * Constructs a new InventoryViewPanel.
     *
     * @param userService the user service
     * @param itemService the item service
     * @param inventoryService the inventory service
     * @param currentUser the current user
     * @param showUserManagementPanel the runnable to show the user management panel
     */
    public InventoryViewPanel(UserService userService, ItemService itemService, InventoryService inventoryService, UserEntity currentUser, Runnable showUserManagementPanel) {
        this.userService = userService;
        this.itemService = itemService;
        this.inventoryService = inventoryService;
        this.currentUser = currentUser;
        this.showUserManagementPanel = showUserManagementPanel;
        this.itemModel = new DefaultTableModel(new Object[]{"Name", "Price", "Stock"}, 0);

        setLayout(new BorderLayout());

        JTable itemTable = new JTable(itemModel);
        JScrollPane scrollPane = new JScrollPane(itemTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel managePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel itemNameLabel = new JLabel("Item Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        managePanel.add(itemNameLabel, gbc);

        JTextField itemNameField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        managePanel.add(itemNameField, gbc);

        JLabel itemPriceLabel = new JLabel("Item Price:");
        gbc.gridx = 2;
        gbc.gridy = 0;
        managePanel.add(itemPriceLabel, gbc);

        JTextField itemPriceField = new JTextField(10);
        gbc.gridx = 3;
        gbc.gridy = 0;
        managePanel.add(itemPriceField, gbc);

        JLabel itemQuantityLabel = new JLabel("Item Quantity:");
        gbc.gridx = 4;
        gbc.gridy = 0;
        managePanel.add(itemQuantityLabel, gbc);

        JTextField itemQuantityField = new JTextField(10);
        gbc.gridx = 5;
        gbc.gridy = 0;
        managePanel.add(itemQuantityField, gbc);

        JButton addItemButton = new JButton("Add Item");
        gbc.gridx = 6;
        gbc.gridy = 0;
        managePanel.add(addItemButton, gbc);

        JLabel priceLabel = new JLabel("New Price:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        managePanel.add(priceLabel, gbc);

        JTextField priceField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        managePanel.add(priceField, gbc);

        JButton updatePriceButton = new JButton("Update Price");
        gbc.gridx = 2;
        gbc.gridy = 1;
        managePanel.add(updatePriceButton, gbc);

        JLabel stockLabel = new JLabel("New Stock:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        managePanel.add(stockLabel, gbc);

        JTextField stockField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        managePanel.add(stockField, gbc);

        JButton updateStockButton = new JButton("Update Stock");
        gbc.gridx = 2;
        gbc.gridy = 2;
        managePanel.add(updateStockButton, gbc);

        JButton backButton = new JButton("Back");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 7;
        managePanel.add(backButton, gbc);

        add(managePanel, BorderLayout.SOUTH);

        addItemButton.addActionListener(e -> {
            String itemName = itemNameField.getText();
            String itemPriceText = itemPriceField.getText();
            String itemQuantityText = itemQuantityField.getText();
            if (!itemName.isEmpty() && !itemPriceText.isEmpty() && !itemQuantityText.isEmpty()) {
                try {
                    double itemPrice = Double.parseDouble(itemPriceText);
                    int itemQuantity = Integer.parseInt(itemQuantityText);
                    addItem(itemName, itemPrice, itemQuantity);
                    loadItems();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price or quantity format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        updatePriceButton.addActionListener(e -> {
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow != -1) {
                String itemName = (String) itemModel.getValueAt(selectedRow, 0);
                try {
                    double newPrice = Double.parseDouble(priceField.getText());
                    updateItemPrice(itemName, newPrice);
                    loadItems();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No item selected", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateStockButton.addActionListener(e -> {
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow != -1) {
                String itemName = (String) itemModel.getValueAt(selectedRow, 0);
                try {
                    int newStock = Integer.parseInt(stockField.getText());
                    updateItemStock(itemName, newStock);
                    loadItems();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid stock format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No item selected", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> showUserManagementPanel.run());

        loadItems();
    }

    /**
     * Loads items into the table model.
     */
    private void loadItems() {
        itemModel.setRowCount(0);
        InventoryEntity inventory = inventoryService.findInventoryWithItems(currentUser.getStore().getInventory().getId());
        List<ItemEntity> items = inventory.getItems();
        for (ItemEntity item : items) {
            itemModel.addRow(new Object[]{item.getName(), item.getPrice(), item.getStock()});
        }
    }

    /**
     * Adds a new item to the inventory.
     *
     * @param itemName the name of the item
     * @param itemPrice the price of the item
     * @param itemQuantity the quantity of the item
     */
    private void addItem(String itemName, double itemPrice, int itemQuantity) {
        InventoryEntity inventory = inventoryService.findInventoryWithItems(currentUser.getStore().getInventory().getId());
        ItemEntity item = new ItemEntity(itemName, itemPrice, itemQuantity, inventory);
        itemService.saveItem(item);
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
}