package menu;

import entity.Grade;
import entity.Student;
import entity.TypeOfUniversity;
import entity.TypeStateUni;
import menu.StudentMenu;
import util.AppContext;
import util.GenerateRandomPassword;
import util.GiveInput;
import util.Validate;

import javax.validation.ConstraintViolation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class SignUpStudent {

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
        Date brithDay = getBrithDay();
        System.out.println("Enter Your Student Number:");
        String studentNumber = GiveInput.giveStringInput();
        System.out.println("Enter Your Entry Year:");
        Integer entryYear = GiveInput.giveIntegerInput();
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
            StudentMenu studentMenu = new StudentMenu();
            studentMenu.menu(student);
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
        System.out.println("Select type of University: State , Non_State :");
        String typeOfUniversity = GiveInput.giveStringInput();
        TypeOfUniversity typeOfUniversity1 = null;
        try {
            typeOfUniversity1 = TypeOfUniversity.valueOf(typeOfUniversity);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid type entered. Please try again.");

        }
        return typeOfUniversity1;
    }

    private static Date getBrithDay() {
        System.out.print("Enter a date (format: dd/MM/yyyy): ");
        String dob = GiveInput.giveStringInput();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dob);
            System.out.println("Parsed date: " + date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter a date in the format dd/MM/yyyy.");
        }
        return date;
    }
}
