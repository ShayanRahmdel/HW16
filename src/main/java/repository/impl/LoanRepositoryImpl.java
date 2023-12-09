package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Loan;
import repository.LoanRepository;

import javax.persistence.EntityManager;

public class LoanRepositoryImpl extends BaseEntityRepositoryImpl<Loan,Integer> implements LoanRepository {
    public LoanRepositoryImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }
}
