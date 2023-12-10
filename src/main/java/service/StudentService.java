package service;

import base.service.BaseService;
import entity.Student;

public interface StudentService extends BaseService<Student,Integer> {
    Student findByUserNameAndPassword(String userName,String password);
}
