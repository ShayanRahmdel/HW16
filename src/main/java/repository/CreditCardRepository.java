package repository;


import base.repository.BaseEntityRepository;
import entity.Bank;
import entity.CreditCard;

import java.util.List;

public interface CreditCardRepository extends BaseEntityRepository<CreditCard,Integer> {

    void updateBalanceById(Double balance, Bank bank,Integer studentId);

    List<CreditCard> findCardByStudentId(Integer studentId);
}
