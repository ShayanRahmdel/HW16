package service.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import base.service.impl.BaseEntityServiceImpl;
import entity.Loan;
import repository.LoanRepository;
import service.LoanService;

public class LoanServiceImpl extends BaseEntityServiceImpl<Loan,Integer,LoanRepository> implements LoanService {

    public LoanServiceImpl(LoanRepository repository) {
        super(repository);
    }


    @Override
    public Integer typeLoanCategoryByStudentId(Integer studentId) {
        return repository.typeLoanCategoryByStudentId(studentId);
    }
}
