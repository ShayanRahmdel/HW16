package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Student;
import repository.StudentRepository;
import service.StudentService;

public class StudentServiceImpl extends BaseEntityServiceImpl<Student,Integer,
        StudentRepository> implements StudentService {
    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }
}
