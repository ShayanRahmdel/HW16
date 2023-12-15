package repository;

import base.repository.BaseEntityRepository;
import entity.StudentSpouse;

public interface StudentSpouseRepository extends BaseEntityRepository<StudentSpouse,Integer> {
    Boolean isSpouseIsStudent(Integer studentID);
}
