package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Student;
import repository.StudentRepository;

import javax.persistence.EntityManager;

public class StudentRepositoryImpl extends BaseEntityRepositoryImpl<Student,Integer>
        implements StudentRepository {
    public StudentRepositoryImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
}
