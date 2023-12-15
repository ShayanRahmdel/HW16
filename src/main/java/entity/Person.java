package entity;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Setter
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends BaseEntity<Integer> {


    @Column(nullable = false,length = 20)
    private String firstName;

    @Column(nullable = false,length = 20)
    private String lastName;

    @Column(nullable = false,length = 20)
    private String fathersName;

    @Column(nullable = false,length = 20)
    private String mothersName;

    @Column(length = 10,nullable = false)
    private String idNumber;

    @Column(length = 10,nullable = false)
    private String nationalNumber;

    @Temporal(value = TemporalType.DATE)
    private Date dob;
}
