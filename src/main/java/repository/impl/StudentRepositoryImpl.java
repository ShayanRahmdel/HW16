package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Student;
import repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class StudentRepositoryImpl extends BaseEntityRepositoryImpl<Student,Integer>
        implements StudentRepository {
    public StudentRepositoryImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public Student findByUserAndPassword(String userName, String password) {
        String hql="SELECT s from Student s WHERE userName= :userName and Password= :password";
        TypedQuery<Student> query = entityManager.createQuery(hql, Student.class);
        query.setParameter("userName",userName);
        query.setParameter("password",password);
        return query.getSingleResult();
    }
}
