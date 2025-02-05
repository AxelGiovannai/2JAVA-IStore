package store.item;

import store.entity.ItemEntity;
import store.item.dao.ItemDao;

import java.util.List;

/**
 * Service for managing items.
 */
public class ItemService {
    private final ItemDao itemDao;

    /**
     * Constructs a new ItemService.
     *
     * @param itemDao the item DAO
     */
    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    /**
     * Saves an item entity.
     *
     * @param item the item entity to save
     */
    public void saveItem(ItemEntity item) {
        itemDao.save(item);
    }

    /**
     * Finds an item entity by its ID.
     *
     * @param id the ID of the item entity
     * @return the found item entity
     */
    public ItemEntity findItemById(int id) {
        return itemDao.findById(id);
    }

    /**
     * Finds all item entities.
     *
     * @return a list of all item entities
     */
    public List<ItemEntity> findAllItems() {
        return itemDao.findAll();
    }

    /**
     * Updates an item entity.
     *
     * @param item the item entity to update
     */
    public void updateItem(ItemEntity item) {
        itemDao.update(item);
    }

    /**
     * Deletes an item entity.
     *
     * @param item the item entity to delete
     */
    public void deleteItem(ItemEntity item) {
        itemDao.delete(item);
    }
}