package entity;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PayInstallment extends BaseEntity<Integer> {

    private Integer number;

    private Double amount;

    private Boolean isPayed;

    private LocalDate dueDate;

    private LocalDate paymentDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Loan loan;

    public PayInstallment(Integer number, Double amount, LocalDate dueDate, Loan loan) {
        this.number = number;
        this.amount = amount;
        this.dueDate = dueDate;
        this.loan = loan;
    }
}
