// src/main/java/store/inventory/InventoryService.java
package store.inventory;

import store.entity.InventoryEntity;
import store.inventory.dao.InventoryDao;

import java.util.List;

public class InventoryService {
    private final InventoryDao inventoryDao;

    public InventoryService(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public void saveInventory(InventoryEntity inventory) {
        inventoryDao.save(inventory);
    }

    public InventoryEntity findInventoryById(int id) {
        return inventoryDao.findById(id);
    }

    public List<InventoryEntity> findAllInventories() {
        return inventoryDao.findAll();
    }

    public void updateInventory(InventoryEntity inventory) {
        inventoryDao.update(inventory);
    }

    public void deleteInventory(InventoryEntity inventory) {
        inventoryDao.delete(inventory);
    }

    public InventoryEntity findInventoryWithItems(int id) {
        return inventoryDao.findInventoryWithItems(id);
    }
}