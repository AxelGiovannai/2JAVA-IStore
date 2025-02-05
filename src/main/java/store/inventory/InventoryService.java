package store.inventory;

import store.entity.InventoryEntity;
import store.inventory.dao.InventoryDao;

import java.util.List;

/**
 * Service class for managing inventory operations.
 */
public class InventoryService {
    private final InventoryDao inventoryDao;

    /**
     * Constructs a new InventoryService.
     *
     * @param inventoryDao the inventory DAO
     */
    public InventoryService(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    /**
     * Saves an inventory entity.
     *
     * @param inventory the inventory entity to save
     */
    public void saveInventory(InventoryEntity inventory) {
        inventoryDao.save(inventory);
    }

    /**
     * Finds an inventory entity by its ID.
     *
     * @param id the ID of the inventory entity
     * @return the found inventory entity
     */
    public InventoryEntity findInventoryById(int id) {
        return inventoryDao.findById(id);
    }

    /**
     * Finds all inventory entities.
     *
     * @return a list of all inventory entities
     */
    public List<InventoryEntity> findAllInventories() {
        return inventoryDao.findAll();
    }

    /**
     * Updates an inventory entity.
     *
     * @param inventory the inventory entity to update
     */
    public void updateInventory(InventoryEntity inventory) {
        inventoryDao.update(inventory);
    }

    /**
     * Deletes an inventory entity.
     *
     * @param inventory the inventory entity to delete
     */
    public void deleteInventory(InventoryEntity inventory) {
        inventoryDao.delete(inventory);
    }

    /**
     * Finds an inventory entity with its items by its ID.
     *
     * @param id the ID of the inventory entity
     * @return the found inventory entity with its items
     */
    public InventoryEntity findInventoryWithItems(int id) {
        return inventoryDao.findInventoryWithItems(id);
    }
}