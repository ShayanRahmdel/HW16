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
    @Enumerated(value = EnumType.STRING)
    private TypeLoan typeLoan;

    @Column(nullable = false)
    private Double amount;

    @OneToMany(mappedBy = "loanCategory")
    private List<Loan> loan;
}
