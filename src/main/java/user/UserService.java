package user;

import user.dao.UserDao;
import user.entity.UserEntity;
import java.util.List;

/**
 * Service class for user operations.
 */
public class UserService {
    private final UserDao userDao;

    /**
     * Constructs a UserService with the specified user DAO.
     *
     * @param userDao the user DAO
     */
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Saves a user entity.
     *
     * @param user the user entity to save
     */
    public void saveUser(UserEntity user) {
        userDao.save(user);
    }

    /**
     * Finds a user entity by email.
     *
     * @param email the email of the user
     * @return the user entity
     */
    public UserEntity findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * Updates a user entity.
     *
     * @param user the user entity to update
     */
    public void updateUser(UserEntity user) {
        userDao.update(user);
    }

    /**
     * Deletes a user entity.
     *
     * @param user the user entity to delete
     */
    public void deleteUser(UserEntity user) {
        userDao.delete(user);
    }

    /**
     * Finds all user entities.
     *
     * @return the list of user entities
     */
    public List<UserEntity> findAllUsers() {
        return userDao.findAll();
    }
}