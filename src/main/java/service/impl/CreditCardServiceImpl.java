package service.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import base.service.impl.BaseEntityServiceImpl;
import entity.Bank;
import entity.CreditCard;
import repository.CreditCardRepository;
import service.CreditCardService;

import java.util.List;

public class CreditCardServiceImpl extends BaseEntityServiceImpl<CreditCard,Integer,CreditCardRepository> implements CreditCardService {

    public CreditCardServiceImpl(CreditCardRepository repository) {
        super(repository);
    }

    @Override
    public void updateBalanceById(Double balance, Bank bank,Integer studentId) {
        repository.updateBalanceById(balance,bank,studentId);
    }

    @Override
    public List<CreditCard> findCreditById(Integer studentId) {
        return repository.findCardByStudentId(studentId);
    }
}
