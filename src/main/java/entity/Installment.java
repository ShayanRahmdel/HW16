package entity;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Installment extends BaseEntity<Integer> {

    private Integer number;

    private Double amount;

    private Boolean isPayed;
    @Temporal(value = TemporalType.DATE)
    private Date dueDate;
    @Temporal(value = TemporalType.DATE)
    private Date paymentDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Loan loan;
}
