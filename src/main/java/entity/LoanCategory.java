package entity;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoanCategory extends BaseEntity<Integer> {
    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private TypeLoan typeLoan;



    @OneToMany(mappedBy = "loanCategory")
    private List<Loan> loan;

    public LoanCategory(TypeLoan typeLoan) {
        this.typeLoan = typeLoan;

    }

    public LoanCategory(Integer integer) {
        super(integer);
    }
}
