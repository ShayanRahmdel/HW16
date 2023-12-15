package entity;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentSpouse extends BaseEntity<Integer> {
    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(unique = true, length = 8)
    private String studentNumber;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, length = 10, unique = true)
    private String nationalNumber;


    private Boolean isStudent;

    @OneToOne
    private Student student;
}
