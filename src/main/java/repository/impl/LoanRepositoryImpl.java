package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Loan;
import entity.TypeLoan;
import repository.LoanRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class LoanRepositoryImpl extends BaseEntityRepositoryImpl<Loan,Integer> implements LoanRepository {
    public LoanRepositoryImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }

    @Override
    public Integer typeLoanCategoryByStudentId(Integer studentId) {
        String sql = "SELECT l.loancategory_id FROM loan l where l.student_id=?";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1,studentId);
        Object singleResult = nativeQuery.getSingleResult();
        if (singleResult != null) {
            return (Integer) singleResult;
        } else {
            return null;
        }
    }

    @Override
    public Boolean isSignForLoanInFirstTerm(Integer year , Integer studentId, TypeLoan typeLoan) {
        String sql = "select l.dateofRegistration from loan l inner join loancategory on l.loancategory_id = loancategory.id where l.student_id=?and typeloan=? and l.dateofRegistration between? and?";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1, studentId);
        nativeQuery.setParameter(2, typeLoan);

        LocalDate startDate =   LocalDate.of(year,9,23);
        LocalDate endDate =   LocalDate.of(year,9,23);


        nativeQuery.setParameter(3, startDate);
        nativeQuery.setParameter(4, endDate);

        try {
            Object singleResult = nativeQuery.getSingleResult();
            return singleResult != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public Boolean isSignForLoanInSecondTerm(Integer year, Integer studentId,TypeLoan typeLoan) {
        String sql = "select l.dateofRegistration from loan l inner join loancategory on l.loancategory_id = loancategory.id where l.student_id=?and typeloan=? and l.dateofRegistration between? and?";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1, studentId);
        nativeQuery.setParameter(2, typeLoan);

        LocalDate startDate =   LocalDate.of(year,9,23);
        LocalDate endDate =   LocalDate.of(year,9,23);


        nativeQuery.setParameter(3, startDate);
        nativeQuery.setParameter(4, endDate);

        List<?> result = nativeQuery.getResultList();

        return !result.isEmpty();
    }
}
