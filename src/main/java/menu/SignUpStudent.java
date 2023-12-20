package menu;

import com.github.eloyzone.jalalicalendar.DateConverter;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import entity.Grade;
import entity.Student;
import entity.TypeOfUniversity;
import entity.TypeStateUni;
import menu.StudentMenu;
import util.*;

import javax.validation.ConstraintViolation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Set;

public class SignUpStudent {
    private DateConverter dateConverter = new DateConverter();

    public void signUp() {
        System.out.println("Enter Your FirstName:");
        String firstName = GiveInput.giveStringInput();
        System.out.println("Enter Your LastName:");
        String lastName = GiveInput.giveStringInput();
        System.out.println("Enter Your Fathers Name:");
        String fathersName = GiveInput.giveStringInput();
        System.out.println("Enter Your Mother Name:");
        String mothersName = GiveInput.giveStringInput();
        System.out.println("Enter ID Number:");
        String idNumber = Validate.nationalCodeValidation();
        System.out.println("Enter National Code:");
        String nationalCode = Validate.nationalCodeValidation();
        System.out.print("Enter a Birthday date (format: yyyy/mm/dd): ");
        LocalDate brithDay = getDateFormatString();
        System.out.println("Enter Your Student Number:");
        String studentNumber = GiveInput.giveStringInput();
        System.out.println("Enter Your Entry Year:");
        LocalDate entryYear = getDateFormatString();
        System.out.println("Enter Your University Name:");
        String universityName = GiveInput.giveStringInput();
        System.out.println("Enter City of University:");
        String cityOfUniversity = GiveInput.giveStringInput();
        TypeOfUniversity typeOfUniversity = typeOfuniversityMethod();
        TypeStateUni typeStateUni = null;
        if (typeOfUniversity.equals(TypeOfUniversity.State)) {
            typeStateUni = typeStateMethod();
        } else {
            typeStateUni = null;
        }
        System.out.println("Enter Your Grade: \n " +
                "Associate,\n" +
                "    Bachelor_Continuous,\n" +
                "    Bachelor_Discontinuous,\n" +
                "    Masters_Continuous,\n" +
                "    Masters_Discontinuous,\n" +
                "    PHD_professional,\n" +
                "    PHD_Continuous,\n" +
                "    PHD_Discontinuous");
        Grade grade = chooseGrade();
        System.out.println("Enter Address in this format 'State/city/street/...");
        String address = GiveInput.giveStringInput();
        Student student = new Student(firstName, lastName, fathersName, mothersName, idNumber, nationalCode, brithDay, studentNumber, entryYear, universityName,cityOfUniversity, typeOfUniversity, typeStateUni,grade,address);
        student.setUserName(student.getNationalNumber());
        student.setPassword(GenerateRandomPassword.generateRandomPassword());
        validation(student);






    }

    private static Grade chooseGrade() {
        String grade = GiveInput.giveStringInput();
        Grade grade1 = null;
        try {
            grade1= Grade.valueOf(grade);
        }catch (IllegalArgumentException e){
            System.out.println("Invalid grade entered. Please try again.");
        }
        return grade1;
    }

    private static void validation(Student student) {
        Set<ConstraintViolation<Student>> violations = Validate.validator.validate(student);
        if (violations.isEmpty()) {
            AppContext.getStudentService().saveOrUpdate(student);
            System.out.println("Your user name : " + student.getUserName());
            System.out.println("Your Password is : "+ student.getPassword() );
            MainMenu mainMenu = new MainMenu();
            mainMenu.menu();
        } else {
            for (ConstraintViolation<Student> violation : violations) {
                System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
            }
        }
    }

    private static TypeStateUni typeStateMethod() {
        System.out.println("Select type of State: Rozane , Shabane:");
        String typeState = GiveInput.giveStringInput();
        TypeStateUni typeStateUni = null;
        try {
            typeStateUni = TypeStateUni.valueOf(typeState);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid type entered. Please try again.");
        }
        return typeStateUni;
    }

    private static TypeOfUniversity typeOfuniversityMethod() {
        System.out.println("Select type of University: State,Non_State,Non_profit_university,Pardis,Excess_capacity,Payam_noor,Applied_Science :");
        String typeOfUniversity = GiveInput.giveStringInput();
        TypeOfUniversity typeOfUniversity1 = null;
        try {
            typeOfUniversity1 = TypeOfUniversity.valueOf(typeOfUniversity);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid type entered. Please try again.");

        }
        return typeOfUniversity1;
    }


    private LocalDate getDateFormatString(){
        String date = GiveInput.giveStringInput();
        String[] split = date.split("/");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        JalaliDate jalaliDate = new JalaliDate(year, month, day);
        LocalDate gregorianDate = dateConverter.jalaliToGregorian(jalaliDate);
        return gregorianDate;
    }
}
