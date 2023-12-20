package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.PayInstallment;
import entity.Student;
import repository.InstallmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class InstallmentRepositoryImpl extends BaseEntityRepositoryImpl<PayInstallment,Integer> implements InstallmentRepository {
    public InstallmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public Class<PayInstallment> getEntityClass() {
        return PayInstallment.class;
    }

    @Override
    public List<Object[]> showPayedInstallment(Student student) {
        String sql = "SELECT p.number , p.paymentdate FROM payinstallment p" +
                " inner join loan l on l.id = p.loan_id WHERE l.student_id = ? AND p.ispayed = true";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1, student.getId());
        return nativeQuery.getResultList();
    }

    @Override
    public List<Object[]> showNotPayedInstallment(Student student) {
        String sql = "SELECT p.number , p.duedate ,p.amount FROM payinstallment p" +
                " inner join loan l on l.id = p.loan_id WHERE l.student_id = ? AND p.ispayed is null or p.ispayed = false";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1, student.getId());
        return nativeQuery.getResultList();
    }

    @Override
    public PayInstallment findByNumberInstallment(Integer number, Student student, LocalDate dueDate) {
        String hql = "SELECT p FROM PayInstallment p inner join Loan l on l.id = p.loan.id WHERE l.student.id= :studentId AND p.number=:number AND p.duedate = :dueDate";
        TypedQuery<PayInstallment> query = entityManager.createQuery(hql, PayInstallment.class);
        query.setParameter("studentId",student.getId());
        query.setParameter("number",number);
        query.setParameter("dueDate",dueDate);
         return query.getSingleResult();
    }
}
