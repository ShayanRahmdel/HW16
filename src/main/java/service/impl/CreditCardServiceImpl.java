package service.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import base.service.impl.BaseEntityServiceImpl;
import entity.CreditCard;
import repository.CreditCardRepository;
import service.CreditCardService;

public class CreditCardServiceImpl extends BaseEntityServiceImpl<CreditCard,Integer,CreditCardRepository> implements CreditCardService {

    public CreditCardServiceImpl(CreditCardRepository repository) {
        super(repository);
    }
}
