package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.LoanCategory;
import repository.LoanCategoryRepository;

import javax.persistence.EntityManager;

public class LoanCategoryRepositoryImpl extends BaseEntityRepositoryImpl<LoanCategory,Integer> implements LoanCategoryRepository {
    public LoanCategoryRepositoryImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public Class<LoanCategory> getEntityClass() {
        return LoanCategory.class;
    }
}
