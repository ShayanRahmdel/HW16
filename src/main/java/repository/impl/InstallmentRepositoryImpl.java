package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Installment;
import repository.InstallmentRepository;

import javax.persistence.EntityManager;

public class InstallmentRepositoryImpl extends BaseEntityRepositoryImpl<Installment,Integer> implements InstallmentRepository {
    public InstallmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public Class<Installment> getEntityClass() {
        return Installment.class;
    }
}
