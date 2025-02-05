package store.item.dao;

import store.entity.ItemEntity;
import java.util.List;

/**
 * Interface for Item Data Access Object.
 */
public interface ItemDao {
    /**
     * Saves an item entity.
     *
     * @param item the item entity to save
     */
    void save(ItemEntity item);

    /**
     * Finds an item entity by its ID.
     *
     * @param id the ID of the item entity
     * @return the found item entity
     */
    ItemEntity findById(int id);

    /**
     * Finds all item entities.
     *
     * @return a list of all item entities
     */
    List<ItemEntity> findAll();

    /**
     * Updates an item entity.
     *
     * @param item the item entity to update
     */
    void update(ItemEntity item);

    /**
     * Deletes an item entity.
     *
     * @param item the item entity to delete
     */
    void delete(ItemEntity item);
}