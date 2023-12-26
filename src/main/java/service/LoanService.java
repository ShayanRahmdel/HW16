package service;

import base.repository.BaseEntityRepository;
import base.service.BaseService;
import entity.Loan;
import entity.Student;
import entity.TypeLoan;

import java.util.List;

public interface LoanService extends BaseService<Loan,Integer> {
    List<Integer> typeLoanCategoryByStudentId(Integer studentId);
    Integer typeLoanCategoryByStudentIdOrderById(Integer studentId);

    Loan isPickLoanBefore(Student student);
    List<Loan> isPickLoanBefore2(Student student);




}
