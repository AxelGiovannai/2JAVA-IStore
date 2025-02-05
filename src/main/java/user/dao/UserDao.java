package user.dao;

import user.entity.UserEntity;

import java.util.List;

/**
 * Interface for user data access operations.
 */
public interface UserDao {

    /**
     * Saves a user entity.
     *
     * @param user the user entity to save
     */
    void save(UserEntity user);

    /**
     * Finds a user entity by email.
     *
     * @param email the email of the user
     * @return the user entity
     */
    UserEntity findByEmail(String email);

    /**
     * Updates a user entity.
     *
     * @param user the user entity to update
     */
    void update(UserEntity user);

    /**
     * Deletes a user entity.
     *
     * @param user the user entity to delete
     */
    void delete(UserEntity user);

    /**
     * Finds all user entities.
     *
     * @return the list of user entities
     */
    List<UserEntity> findAll();
}