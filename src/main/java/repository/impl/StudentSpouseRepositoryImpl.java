package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.StudentSpouse;
import repository.StudentSpouseRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class StudentSpouseRepositoryImpl extends BaseEntityRepositoryImpl<StudentSpouse,Integer> implements StudentSpouseRepository {
    public StudentSpouseRepositoryImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public Class<StudentSpouse> getEntityClass() {
        return StudentSpouse.class;
    }

    @Override
    public Boolean isSpouseIsStudent(Integer studentID) {
        String sql = "SELECT s.isstudent FROM studentspouse  s WHERE s.student_id=?";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1,studentID);
        Object result = nativeQuery.getSingleResult();
        if (result != null) {
            return (Boolean) result;
        } else {
            return false;
        }
    }
}
