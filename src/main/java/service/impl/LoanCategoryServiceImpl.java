package service.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import base.service.impl.BaseEntityServiceImpl;
import entity.LoanCategory;
import repository.LoanCategoryRepository;
import service.LoanCategoryService;

public class LoanCategoryServiceImpl extends BaseEntityServiceImpl<LoanCategory,Integer,LoanCategoryRepository> implements LoanCategoryService {

    public LoanCategoryServiceImpl(LoanCategoryRepository repository) {
        super(repository);
    }
}
