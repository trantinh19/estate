package repository;


import entity.UserEntity;

import java.util.List;

public interface IUserRepository extends JpaRepository<UserEntity> {
    List<UserEntity> findAll();
}
