package ru.itis.rasimusv.services;

import ru.itis.rasimusv.models.*;

import java.util.*;

public interface StudentsService {

    List<Student> getAllStudents();

    List<Student> getAllStudentsByAge(int age);
}
