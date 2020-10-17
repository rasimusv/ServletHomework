package ru.itis.rasimusv.repositories;

import ru.itis.rasimusv.models.*;

import java.util.*;


public interface StudentsRepository extends CrudRepository<Student> {

    List<Student> findAllByAge(int age);

    List<Student> findAll();

}
