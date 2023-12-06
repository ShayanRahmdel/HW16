package entity;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Loan extends BaseEntity<Integer> {
    private Double amount;
    private TypeLoan typeLoan;
    private Integer houseTrackingCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private LoanCategory loanCategory;


    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
    @OneToMany(mappedBy = "loan")
    private List<Installment> installments;

}
