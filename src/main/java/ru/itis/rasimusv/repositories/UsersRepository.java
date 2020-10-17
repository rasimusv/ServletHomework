package ru.itis.rasimusv.repositories;

import ru.itis.rasimusv.models.*;

import java.util.*;


public interface UsersRepository extends CrudRepository<User> {

    List<User> findAllByAge(int age);

    List<User> findAll();

}
