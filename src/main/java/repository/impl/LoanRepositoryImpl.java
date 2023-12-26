package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Loan;
import entity.Student;
import entity.TypeLoan;
import repository.LoanRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class LoanRepositoryImpl extends BaseEntityRepositoryImpl<Loan, Integer> implements LoanRepository {
    public LoanRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }

    @Override
    public List<Integer> typeLoanCategoryByStudentId(Integer studentId) {
        String sql = "SELECT l.loancategory_id FROM loan l where l.student_id=?";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1, studentId);
        return (List<Integer>) nativeQuery.getResultList();

    }

    @Override
    public Integer typeLoanCategoryByStudentIdOrderById(Integer studentId) {
        String sql = "SELECT l.loancategory_id FROM loan l WHERE l.student_id = ? ORDER BY l.id DESC LIMIT 1";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1, studentId);

        Integer loanCategory = (Integer) nativeQuery.getSingleResult();
        return loanCategory;
    }

    @Override
    public Loan isPickLoanBefore(Student student) {
        String sql = "SELECT * FROM loan l INNER JOIN loancategory o ON l.loancategory_id = o.id " +
                "WHERE l.student_id = ? " +
                "ORDER BY l.id DESC LIMIT 1";

        Query nativeQuery = entityManager.createNativeQuery(sql, Loan.class);
        nativeQuery.setParameter(1, student.getId());


        List<Loan> resultList = nativeQuery.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

    @Override
    public List<Loan> isPickLoanBefore2(Student student) {
        String hql = "SELECT l from Loan l where student.id=:student_id";
        TypedQuery<Loan> query = entityManager.createQuery(hql, Loan.class);
        query.setParameter("student_id", student.getId());
        return query.getResultList();

    }

}
