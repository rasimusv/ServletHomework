package ru.itis.rasimusv.repositories;

import ru.itis.rasimusv.models.Student;

import java.util.List;

public interface StudentsRepository<ID> extends CrudRepository<Student, ID> {
    List<Student> findAllByAge(int age);
}
