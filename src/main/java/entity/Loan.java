package entity;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Loan extends BaseEntity<Integer> {

    private Integer houseTrackingCode;


    private LocalDate dateofRegistration;

    @ManyToOne(fetch = FetchType.LAZY)
    private LoanCategory loanCategory;


    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
    @OneToMany(mappedBy = "loan")
    private List<PayInstallment> installments;

    public Loan(Integer houseTrackingCode, LocalDate dateofRegistration, LoanCategory loanCategory, Student student) {

        this.houseTrackingCode = houseTrackingCode;
        this.dateofRegistration = dateofRegistration;
        this.loanCategory = loanCategory;
        this.student = student;
    }
}
