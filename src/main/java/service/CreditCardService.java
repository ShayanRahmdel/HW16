package service;



import base.service.BaseService;
import entity.Bank;
import entity.CreditCard;

import java.util.List;

public interface CreditCardService extends BaseService<CreditCard,Integer> {
    void updateBalanceById(Double balance, Bank bank,Integer studentId);
    List<CreditCard> findCreditById(Integer studentId);
}
