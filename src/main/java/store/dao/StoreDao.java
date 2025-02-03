// src/main/java/store/dao/StoreDao.java
package store.dao;

import store.entity.StoreEntity;
import java.util.List;

public interface StoreDao {
    void save(StoreEntity store);
    StoreEntity findById(int id);
    List<StoreEntity> findAll();
    void update(StoreEntity store);
    void delete(StoreEntity store);
}