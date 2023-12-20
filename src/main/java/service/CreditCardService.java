package service;



import base.service.BaseService;
import entity.Bank;
import entity.CreditCard;
import entity.Student;

import java.time.LocalDate;
import java.util.List;

public interface CreditCardService extends BaseService<CreditCard,Integer> {
    void updateBalanceById(Double balance, Bank bank,Integer studentId);
    List<CreditCard> findCreditById(Integer studentId);


    Boolean isExitThisCredit(Student student, String cardNumber, String cvv2, LocalDate expiretionDate);
}
