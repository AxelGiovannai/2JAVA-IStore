// src/main/java/store/StoreService.java
package store;

import store.dao.StoreDao;
import store.entity.StoreEntity;

import java.util.List;

public class StoreService {
    private final StoreDao storeDao;

    public StoreService(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public void saveStore(StoreEntity store) {
        storeDao.save(store);
    }

    public StoreEntity findStoreById(int id) {
        return storeDao.findById(id);
    }

    public List<StoreEntity> findAllStores() {
        return storeDao.findAll();
    }

    public void updateStore(StoreEntity store) {
        storeDao.update(store);
    }

    public void deleteStore(StoreEntity store) {
        if (storeDao.exists(store.getId())) {
            storeDao.refresh(store); // Refresh the entity before deletion
            storeDao.delete(store);
        }
    }

    public StoreEntity findStoreByName(String name) {
        List<StoreEntity> stores = storeDao.findAll();
        for (StoreEntity store : stores) {
            if (store.getName().equals(name)) {
                return store;
            }
        }
        return null;
    }
}