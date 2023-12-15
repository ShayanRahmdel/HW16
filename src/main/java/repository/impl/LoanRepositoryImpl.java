package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Loan;
import repository.LoanRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
}
