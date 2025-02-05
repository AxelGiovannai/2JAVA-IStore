package store.dao;

import store.entity.StoreEntity;
import java.util.List;

/**
 * Interface for Store Data Access Object.
 */
public interface StoreDao {
    /**
     * Saves a store entity.
     *
     * @param store the store entity to save
     */
    void save(StoreEntity store);

    /**
     * Finds a store entity by its ID.
     *
     * @param id the ID of the store entity
     * @return the found store entity
     */
    StoreEntity findById(int id);

    /**
     * Finds all store entities.
     *
     * @return a list of all store entities
     */
    List<StoreEntity> findAll();

    /**
     * Updates a store entity.
     *
     * @param store the store entity to update
     */
    void update(StoreEntity store);

    /**
     * Deletes a store entity.
     *
     * @param store the store entity to delete
     */
    void delete(StoreEntity store);

    /**
     * Refreshes a store entity.
     *
     * @param store the store entity to refresh
     */
    void refresh(StoreEntity store);

    /**
     * Checks if a store entity exists by its ID.
     *
     * @param id the ID of the store entity
     * @return true if the store entity exists, false otherwise
     */
    boolean exists(int id);
}