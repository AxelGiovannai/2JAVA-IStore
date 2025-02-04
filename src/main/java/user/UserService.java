package user;

import user.dao.UserDao;
import user.entity.UserEntity;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void saveUser(UserEntity user) {
        userDao.save(user);
    }

    public UserEntity findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public void updateUser(UserEntity user) {
        userDao.update(user);
    }

    public void deleteUser(UserEntity user) {
        userDao.delete(user);
    }

    public List<UserEntity> findAllUsers() {
        return userDao.findAll();
    }
}