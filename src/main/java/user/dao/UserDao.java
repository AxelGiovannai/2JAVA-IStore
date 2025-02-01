package user.dao;

import user.entity.UserEntity;

public interface UserDao {
    void save(UserEntity user);
    UserEntity findByEmail(String email);
    void update(UserEntity user);
    void delete(UserEntity user);
}