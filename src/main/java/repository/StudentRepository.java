package repository;

import base.repository.BaseEntityRepository;
import entity.Student;
import entity.StudentSpouse;

public interface StudentRepository extends BaseEntityRepository<Student,Integer> {
    Student findByUserAndPassword(String userName,String password);
}
