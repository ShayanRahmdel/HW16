package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.PayInstallment;
import repository.InstallmentRepository;

import javax.persistence.EntityManager;

public class InstallmentRepositoryImpl extends BaseEntityRepositoryImpl<PayInstallment,Integer> implements InstallmentRepository {
    public InstallmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public Class<PayInstallment> getEntityClass() {
        return PayInstallment.class;
    }
}
