package ru.itis.rasimusv.repositories;

import ru.itis.rasimusv.models.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User> {

    List<User> findAll();

    List<User> findByUUID(String uuid);

    List<User> findByCredentials(String username, String password);

    List<User> findByUsername(String username);

}
