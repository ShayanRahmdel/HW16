package entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student extends Person {


    @Column(nullable = false,unique = true,length = 8)
    private String studentNumber;

    @Max(value = 1403,message = "Your year is greater than rules")
    @Min(value = 1395,message = "Your year less than rules")
    private Integer yearOfEntry;

    @Column(nullable = false,length = 30)
    private String universityName;

    @Enumerated(value = EnumType.STRING)
    private TypeOfUniversity typeOfUniversity;

    @Enumerated(value = EnumType.STRING)
    private TypeStateUni typeStateUni;

    @Column(unique = true,length = 10,nullable = false)
    private String userName;
    @Column(nullable = false,length = 8)
    private String Password;

    @Enumerated(value = EnumType.STRING)
    private Grade grade;

    private String address;

    @OneToMany(mappedBy = "student")
    private List<CreditCard> creditCards;


    @OneToMany(mappedBy = "student")
    private List<Loan> loans;

    public Student(String firstName, String lastName, String fathersName, String mothersName, String idNumber, String nationalNumber, Date dob, String studentNumber, Integer yearOfEntry, String universityName, TypeOfUniversity typeOfUniversity, TypeStateUni typeStateUni, Grade grade) {
        super(firstName, lastName, fathersName, mothersName, idNumber, nationalNumber, dob);
        this.studentNumber = studentNumber;
        this.yearOfEntry = yearOfEntry;
        this.universityName = universityName;
        this.typeOfUniversity = typeOfUniversity;
        this.typeStateUni = typeStateUni;
        this.grade = grade;
    }



}
