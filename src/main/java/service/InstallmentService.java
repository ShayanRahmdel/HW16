package service;

import base.service.BaseService;
import entity.PayInstallment;
import entity.Student;

import java.time.LocalDate;
import java.util.List;

public interface InstallmentService extends BaseService<PayInstallment,Integer> {



    List<Object[]> showPayedInstallment(Student student);
    List<Object[]> showNotPayedInstallment(Student student);
    PayInstallment findByNumberInstallment(Integer number,Student student,LocalDate dueDate);
}


