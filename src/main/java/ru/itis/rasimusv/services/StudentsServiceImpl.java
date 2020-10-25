package ru.itis.rasimusv.services;

import ru.itis.rasimusv.models.Student;
import ru.itis.rasimusv.repositories.StudentsRepository;

import java.util.List;

public class StudentsServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentsServiceImpl(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentsRepository.findAll();
    }

    @Override
    public List<Student> getAllStudentsByAge(int age) {
        return studentsRepository.findAllByAge(age);
    }


}
