package entity;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Loan extends BaseEntity<Integer> {
    private Double amount;
    private Integer houseTrackingCode;
    @Temporal(TemporalType.DATE)
    private Date dateofRegistration;

    @ManyToOne(fetch = FetchType.LAZY)
    private LoanCategory loanCategory;


    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
    @OneToMany(mappedBy = "loan")
    private List<Installment> installments;

    public Loan(Double amount, Integer houseTrackingCode, Date dateofRegistration, LoanCategory loanCategory, Student student) {
        this.amount = amount;
        this.houseTrackingCode = houseTrackingCode;
        this.dateofRegistration = dateofRegistration;
        this.loanCategory = loanCategory;
        this.student = student;
    }
}
