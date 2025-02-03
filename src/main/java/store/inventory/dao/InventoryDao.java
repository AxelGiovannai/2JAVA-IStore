// src/main/java/store/inventory/dao/InventoryDao.java
package store.inventory.dao;

import store.entity.InventoryEntity;
import java.util.List;

public interface InventoryDao {
    void save(InventoryEntity inventory);
    InventoryEntity findById(int id);
    List<InventoryEntity> findAll();
    void update(InventoryEntity inventory);
    void delete(InventoryEntity inventory);
}