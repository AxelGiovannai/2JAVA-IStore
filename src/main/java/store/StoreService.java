package store;

import store.dao.StoreDao;
import store.entity.StoreEntity;

import java.util.List;

/**
 * Service for managing stores.
 */
public class StoreService {
    private final StoreDao storeDao;

    /**
     * Constructs a new StoreService.
     *
     * @param storeDao the store DAO
     */
    public StoreService(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    /**
     * Saves a store entity.
     *
     * @param store the store entity to save
     */
    public void saveStore(StoreEntity store) {
        storeDao.save(store);
    }

    /**
     * Finds a store entity by its ID.
     *
     * @param id the ID of the store entity
     * @return the found store entity
     */
    public StoreEntity findStoreById(int id) {
        return storeDao.findById(id);
    }

    /**
     * Finds all store entities.
     *
     * @return a list of all store entities
     */
    public List<StoreEntity> findAllStores() {
        return storeDao.findAll();
    }

    /**
     * Updates a store entity.
     *
     * @param store the store entity to update
     */
    public void updateStore(StoreEntity store) {
        storeDao.update(store);
    }

    /**
     * Deletes a store entity.
     *
     * @param store the store entity to delete
     */
    public void deleteStore(StoreEntity store) {
        if (storeDao.exists(store.getId())) {
            storeDao.refresh(store);
            storeDao.delete(store);
        }
    }

    /**
     * Finds a store entity by its name.
     *
     * @param name the name of the store entity
     * @return the found store entity, or null if not found
     */
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