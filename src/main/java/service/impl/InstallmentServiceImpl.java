package service.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import base.service.impl.BaseEntityServiceImpl;
import entity.Installment;
import repository.InstallmentRepository;
import service.InstallmentService;

public class InstallmentServiceImpl extends BaseEntityServiceImpl<Installment,Integer,InstallmentRepository> implements InstallmentService {

    public InstallmentServiceImpl(InstallmentRepository repository) {
        super(repository);
    }
}
