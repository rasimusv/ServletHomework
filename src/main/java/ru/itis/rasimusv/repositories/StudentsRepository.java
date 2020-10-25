package ru.itis.rasimusv.repositories;

import ru.itis.rasimusv.models.Student;

import java.util.List;

public interface StudentsRepository extends CrudRepository<Student> {

    List<Student> findAllByAge(int age);

    List<Student> findAll();

}
