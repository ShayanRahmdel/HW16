package service;

import base.repository.BaseEntityRepository;
import base.service.BaseService;
import entity.Loan;
import entity.TypeLoan;

public interface LoanService extends BaseService<Loan,Integer> {
    Integer typeLoanCategoryByStudentId(Integer studentId);

    Boolean isSignForLoanInFirstTerm(Integer year, Integer studentId, TypeLoan typeLoan);
    Boolean isSignForLoanInSecondTerm(Integer year, Integer studentId, TypeLoan typeLoan);
}
