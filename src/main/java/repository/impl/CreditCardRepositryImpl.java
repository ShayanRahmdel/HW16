package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Bank;
import entity.CreditCard;
import repository.CreditCardRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class CreditCardRepositryImpl extends BaseEntityRepositoryImpl<CreditCard,Integer> implements CreditCardRepository {
    public CreditCardRepositryImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public Class<CreditCard> getEntityClass() {
        return CreditCard.class;
    }

    @Override
    public void updateBalanceById(Double balance, Bank bank,Integer studentId) {
        beginTransaction();
        String hql = "UPDATE CreditCard SET balance = :newBalance WHERE bankName = :bankName and student.id = :studentId";
        try {
            beginTransaction();
            Query query = entityManager.createQuery(hql);
            query.setParameter("newBalance",balance);
            query.setParameter("bankName",bank);
            query.setParameter("studentId",studentId);
            query.executeUpdate();
            commitTransaction();
        }catch (Exception e){
            rollBack();
        }

    }

    @Override
    public List<CreditCard> findCardByStudentId(Integer studentId) {
        String hql="select c from CreditCard c where student.id= :studentId";
        TypedQuery<CreditCard> query = entityManager.createQuery(hql, CreditCard.class);
        query.setParameter("studentId",studentId);
        List<CreditCard> resultList = query.getResultList();
        return resultList;
    }
}
