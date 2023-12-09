package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.StudentSpouse;
import repository.StudentSpouseRepository;

import javax.persistence.EntityManager;

public class StudentSpouseRepositoryImpl extends BaseEntityRepositoryImpl<StudentSpouse,Integer> implements StudentSpouseRepository {
    public StudentSpouseRepositoryImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public Class<StudentSpouse> getEntityClass() {
        return StudentSpouse.class;
    }
}
