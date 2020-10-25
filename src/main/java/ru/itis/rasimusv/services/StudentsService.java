package ru.itis.rasimusv.services;

import ru.itis.rasimusv.models.Student;

import java.util.List;

public interface StudentsService {

    List<Student> getAllStudents();

    List<Student> getAllStudentsByAge(int age);
}
