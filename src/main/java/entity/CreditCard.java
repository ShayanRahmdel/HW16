package entity;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CreditCard extends BaseEntity<Integer> {

    private Double balance;
    @Column(nullable = false,unique = true,length = 16)
    private String cardNumber;

    @Column(nullable = false)
    private String cvv2;


    private LocalDate expiretionDate;

    @Enumerated(value = EnumType.STRING)
    private Bank bankName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    public CreditCard(String cardNumber, String cvv2, LocalDate expiretionDate, Bank bankName, Student student) {
        this.cardNumber = cardNumber;
        this.cvv2 = cvv2;
        this.expiretionDate = expiretionDate;
        this.bankName = bankName;
        this.student = student;
    }

    @Override
    public String toString() {
        return "Your CreditCards" +"\n"+
                "cardNumber=====> " + cardNumber + "\n" +
                "cvv2===========> " + cvv2 + "\n" +
                "expiretionDate=> " + expiretionDate +"\n"+
                "bankName=======> " + bankName+"\n"+
                "=================================";
    }
}
