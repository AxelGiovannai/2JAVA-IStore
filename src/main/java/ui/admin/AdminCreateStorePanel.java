package ui.admin;

import store.StoreService;
import store.entity.StoreEntity;
import store.inventory.InventoryService;
import store.entity.InventoryEntity;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel for creating and managing stores in the admin interface.
 */
public class AdminCreateStorePanel extends JPanel {
    private final StoreService storeService;
    private final InventoryService inventoryService;
    private final DefaultListModel<String> storeListModel;

    /**
     * Constructs a new AdminCreateStorePanel.
     *
     * @param storeService the store service
     * @param inventoryService the inventory service
     * @param showAdminDashboard the runnable to show the admin dashboard
     */
    public AdminCreateStorePanel(StoreService storeService, InventoryService inventoryService, Runnable showAdminDashboard) {
        this.storeService = storeService;
        this.inventoryService = inventoryService;
        this.storeListModel = new DefaultListModel<>();

        setLayout(new BorderLayout());

        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> showAdminDashboard.run());
        add(backButton, BorderLayout.SOUTH);

        JList<String> storeList = new JList<>(storeListModel);
        JScrollPane scrollPane = new JScrollPane(storeList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel addStorePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel storeNameLabel = new JLabel("Store Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        addStorePanel.add(storeNameLabel, gbc);

        JTextField storeNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        addStorePanel.add(storeNameField, gbc);

        JButton addButton = new JButton("Add Store");
        gbc.gridx = 1;
        gbc.gridy = 1;
        addStorePanel.add(addButton, gbc);

        JButton deleteButton = new JButton("Delete Store");
        gbc.gridx = 1;
        gbc.gridy = 2;
        addStorePanel.add(deleteButton, gbc);

        add(addStorePanel, BorderLayout.NORTH);

        addButton.addActionListener(e -> {
            String storeName = storeNameField.getText();
            if (!storeName.isEmpty()) {
                StoreEntity newStore = new StoreEntity(storeName);
                storeService.saveStore(newStore);

                InventoryEntity newInventory = new InventoryEntity(newStore);
                inventoryService.saveInventory(newInventory);

                storeListModel.addElement(storeName);
                storeNameField.setText("");
            }
        });

        deleteButton.addActionListener(e -> {
            String selectedStoreName = storeList.getSelectedValue();
            if (selectedStoreName != null) {
                List<StoreEntity> stores = storeService.findAllStores();
                for (StoreEntity store : stores) {
                    if (store.getName().equals(selectedStoreName)) {
                        InventoryEntity inventory = store.getInventory();
                        if (inventory != null) {
                            inventoryService.deleteInventory(inventory);
                        }
                        storeService.deleteStore(store);
                        storeListModel.removeElement(selectedStoreName);
                        break;
                    }
                }
            }
        });

        loadStores();
    }

    /**
     * Loads existing stores into the list model.
     */
    private void loadStores() {
        List<StoreEntity> stores = storeService.findAllStores();
        for (StoreEntity store : stores) {
            storeListModel.addElement(store.getName());
        }
    }
}