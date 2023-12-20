package entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Student extends Person {


    @Column(nullable = false,unique = true,length = 8)
    private String studentNumber;


    private LocalDate yearOfEntry;

    @Column(nullable = false,length = 30)
    private String universityName;

    @Enumerated(value = EnumType.STRING)
    private TypeOfUniversity typeOfUniversity;

    @Column(nullable = true)
    private TypeStateUni typeStateUni;

    @Column(unique = true,length = 10,nullable = false)
    private String userName;
    @Column(nullable = false,length = 8)
    private String Password;

    @Enumerated(value = EnumType.STRING)
    private Grade grade;

    private String address;

    private String cityOfUniversity;

    @OneToMany(mappedBy = "student")
    private List<CreditCard> creditCards;


    @OneToMany(mappedBy = "student")
    private List<Loan> loans;

    public Student(String firstName, String lastName, String fathersName, String mothersName, String idNumber, String nationalNumber, LocalDate dob, String studentNumber, LocalDate yearOfEntry, String universityName, String cityOfUniversity, TypeOfUniversity typeOfUniversity, TypeStateUni typeStateUni, Grade grade, String address) {
        super(firstName, lastName, fathersName, mothersName, idNumber, nationalNumber, dob);
        this.studentNumber = studentNumber;
        this.yearOfEntry = yearOfEntry;
        this.universityName = universityName;
        this.cityOfUniversity=cityOfUniversity;
        this.typeOfUniversity = typeOfUniversity;
        this.typeStateUni = typeStateUni;
        this.grade = grade;
        this.setAddress(address);
    }

    public Student(Integer id){
        this.setId(id);
    }


}
