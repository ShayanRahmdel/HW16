package repository;

import base.repository.BaseEntityRepository;
import entity.Loan;
import entity.Student;
import entity.TypeLoan;

public interface LoanRepository extends BaseEntityRepository<Loan,Integer> {

    Integer typeLoanCategoryByStudentId(Integer studentId);

    Boolean isSignForLoanInFirstTerm(Integer year,Integer studentId,TypeLoan typeLoan);
    Boolean isSignForLoanInSecondTerm(Integer year, Integer studentId, TypeLoan typeLoan);
}
