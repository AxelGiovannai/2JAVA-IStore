// src/main/java/store/item/dao/ItemDao.java
package store.item.dao;

import store.entity.ItemEntity;
import java.util.List;

public interface ItemDao {
    void save(ItemEntity item);
    ItemEntity findById(int id);
    List<ItemEntity> findAll();
    void update(ItemEntity item);
    void delete(ItemEntity item);
}