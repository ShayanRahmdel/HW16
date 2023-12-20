package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Grade;
import entity.PayInstallment;
import entity.Student;
import repository.InstallmentRepository;
import service.InstallmentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class InstallmentServiceImpl extends BaseEntityServiceImpl<PayInstallment,Integer,InstallmentRepository> implements InstallmentService {

    public InstallmentServiceImpl(InstallmentRepository repository) {
        super(repository);
    }


    @Override
    public List<Object[]> showPayedInstallment(Student student) {
        return repository.showPayedInstallment(student);
    }

    @Override
    public List<Object[]> showNotPayedInstallment(Student student) {
        return repository.showNotPayedInstallment(student);
    }

    @Override
    public PayInstallment findByNumberInstallment(Integer number, Student student,LocalDate dueDate) {
        return  repository.findByNumberInstallment(number,student,dueDate);
    }
}
