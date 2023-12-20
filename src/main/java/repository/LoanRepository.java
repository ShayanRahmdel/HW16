package repository;

import base.repository.BaseEntityRepository;
import entity.Loan;
import entity.Student;
import entity.TypeLoan;

import java.util.List;

public interface LoanRepository extends BaseEntityRepository<Loan,Integer> {

    List<Integer> typeLoanCategoryByStudentId(Integer studentId);
    Integer typeLoanCategoryByStudentIdOrderById(Integer studentId);

    Loan isPickLoanBefore(Student student);



}
