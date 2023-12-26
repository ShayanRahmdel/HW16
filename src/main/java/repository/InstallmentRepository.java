package repository;

import base.repository.BaseEntityRepository;
import entity.PayInstallment;
import entity.Student;

import java.time.LocalDate;
import java.util.List;

public interface InstallmentRepository extends BaseEntityRepository<PayInstallment,Integer> {


    List<Object[]> showPayedInstallment(Student student);
    List<Object[]> showNotPayedInstallment(Student student);

    PayInstallment findByNumberInstallment(Integer number, Integer studentId,Double amount);



    List<PayInstallment> findByStudent(Student student);
}
