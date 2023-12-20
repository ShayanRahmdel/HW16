package service.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import base.service.impl.BaseEntityServiceImpl;
import entity.Loan;
import entity.Student;
import entity.TypeLoan;
import repository.LoanRepository;
import service.LoanService;

import java.util.List;

public class LoanServiceImpl extends BaseEntityServiceImpl<Loan,Integer,LoanRepository> implements LoanService {

    public LoanServiceImpl(LoanRepository repository) {
        super(repository);
    }


    @Override
    public List<Integer> typeLoanCategoryByStudentId(Integer studentId) {
        return repository.typeLoanCategoryByStudentId(studentId);
    }

    @Override
    public Integer typeLoanCategoryByStudentIdOrderById(Integer studentId) {
        return repository.typeLoanCategoryByStudentIdOrderById(studentId);
    }

    @Override
    public Loan isPickLoanBefore(Student student) {
        return repository.isPickLoanBefore(student);
    }



}
