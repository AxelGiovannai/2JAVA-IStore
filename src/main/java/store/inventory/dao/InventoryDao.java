package store.inventory.dao;

import store.entity.InventoryEntity;
import java.util.List;

/**
 * Interface for Inventory Data Access Object.
 */
public interface InventoryDao {
    /**
     * Saves an inventory entity.
     *
     * @param inventory the inventory entity to save
     */
    void save(InventoryEntity inventory);

    /**
     * Finds an inventory entity by its ID.
     *
     * @param id the ID of the inventory entity
     * @return the found inventory entity
     */
    InventoryEntity findById(int id);

    /**
     * Finds all inventory entities.
     *
     * @return a list of all inventory entities
     */
    List<InventoryEntity> findAll();

    /**
     * Updates an inventory entity.
     *
     * @param inventory the inventory entity to update
     */
    void update(InventoryEntity inventory);

    /**
     * Deletes an inventory entity.
     *
     * @param inventory the inventory entity to delete
     */
    void delete(InventoryEntity inventory);

    /**
     * Finds an inventory entity with its items by its ID.
     *
     * @param id the ID of the inventory entity
     * @return the found inventory entity with its items
     */
    InventoryEntity findInventoryWithItems(int id);
}