package service.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import base.service.impl.BaseEntityServiceImpl;
import entity.StudentSpouse;
import repository.StudentSpouseRepository;
import service.StudentSpouseService;

public class StudentSpouseServiceImpl extends BaseEntityServiceImpl<StudentSpouse,Integer,StudentSpouseRepository> implements StudentSpouseService {
    public StudentSpouseServiceImpl(StudentSpouseRepository repository) {
        super(repository);
    }


    @Override
    public Boolean isSpouseStudent(Integer studentId) {
        return repository.isSpouseIsStudent(studentId);
    }
}
