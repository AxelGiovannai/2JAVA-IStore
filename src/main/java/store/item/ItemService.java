// src/main/java/store/item/ItemService.java
package store.item;

import store.item.dao.ItemDao;
import store.entity.ItemEntity;
import java.util.List;

public class ItemService {
    private final ItemDao itemDao;

    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void saveItem(ItemEntity item) {
        itemDao.save(item);
    }

    public ItemEntity findItemById(int id) {
        return itemDao.findById(id);
    }

    public List<ItemEntity> findAllItems() {
        return itemDao.findAll();
    }

    public void updateItem(ItemEntity item) {
        itemDao.update(item);
    }

    public void deleteItem(ItemEntity item) {
        itemDao.delete(item);
    }
}