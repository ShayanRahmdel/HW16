package entity;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Card extends BaseEntity<Integer> {
    @Column(nullable = false,unique = true,length = 16)
    private String cardNumber;

    @Column(nullable = false)
    private String cvv2;

    @Temporal(value = TemporalType.DATE)
    private Date expiretionDate;

    @Enumerated(value = EnumType.STRING)
    private Bank bankName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
}
