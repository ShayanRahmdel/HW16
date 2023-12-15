package menu;

import entity.*;
import util.AppContext;
import util.GiveInput;
import util.Validate;

import javax.validation.ConstraintViolation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Housing {
    private Loan loan = new Loan();

    public void housingLoan(Student student) {
        System.out.print("Enter Today date (format: dd/MM/yyyy): ");
        Date date = getDate();
        if (checkDateFirstTerm(date)) {
            System.out.println("You Can sign for Loan");
            System.out.println("======================");
            StudentSpouse spouseInfo = getSpouseInfo(student);
            if (isSpouseHasHouseLoan(spouseInfo, student).equals(true)) {
                System.out.println("Your Spouse Already have houseLoan");
            } else {
                System.out.println("Enter Your HouseTrackingCode");
                Integer houseTrackCode = GiveInput.giveIntegerInput();
                System.out.println("Which bank want get Loan?\n");
                System.out.println(AppContext.getCreditCardService().findCreditById(student.getId()));
                String bank = GiveInput.giveStringInput();
                Bank bank1 = null;
                try {
                    bank1 = Bank.valueOf(bank);
                } catch (IllegalArgumentException e) {
                    System.out.println("enter valid bank");
                }
                if (student.getCityOfUniversity().equals("tehran")&&!isStudentHaveDorm(student)) {
                    LoanCategory loanCategory = new LoanCategory(1);
                    loan = new Loan(houseTrackCode, date, loanCategory, student);
                    validationLoan(loan);
                    AppContext.getCreditCardService().updateBalanceById(32000000.0, bank1, student.getId());
                    System.out.println("Your loan sucessfully added");
                }
                else if (!isStudentHaveDorm(student)&&student.getCityOfUniversity().equals("gilan") || !isStudentHaveDorm(student)&&student.getCityOfUniversity().equals("esfahan") ||
                        !isStudentHaveDorm(student)&& student.getCityOfUniversity().equals("azarbayjansharghi") || !isStudentHaveDorm(student)&&student.getCityOfUniversity().equals("fars") ||
                        !isStudentHaveDorm(student)&&student.getCityOfUniversity().equals("khozestan") || !isStudentHaveDorm(student)&&student.getCityOfUniversity().equals("ghom") ||
                       !isStudentHaveDorm(student) &&student.getCityOfUniversity().equals("khorasan") ||!isStudentHaveDorm(student)&& student.getCityOfUniversity().equals("alborz")) {
                    LoanCategory loanCategory = new LoanCategory(2);
                    loan = new Loan(houseTrackCode, date, loanCategory, student);
                    validationLoan(loan);
                    AppContext.getCreditCardService().updateBalanceById(26000000.0, bank1, student.getId());
                    System.out.println("Your loan sucessfully added");
                } else {
                    LoanCategory loanCategory = new LoanCategory(3);
                    loan = new Loan(houseTrackCode, date, loanCategory, student);
                    validationLoan(loan);
                    AppContext.getCreditCardService().updateBalanceById(19500000.0, bank1, student.getId());
                    System.out.println("Your loan sucessfully added");
                }
            }


        } else {
            System.out.println("You cant sign for Loan in this Date");
        }
    }

    private static StudentSpouse getSpouseInfo(Student student) {
        System.out.println("Enter  Your Spouse firstName: ");
        String firstName = GiveInput.giveStringInput();
        System.out.println("Enter Your Spouse LastName:");
        String lastName = GiveInput.giveStringInput();
        System.out.println("Enter Your Spouse NationalNumber:");
        String nationalNumber = GiveInput.giveStringInput();
        System.out.println("Is Your Spouse Student?");
        String answer = GiveInput.giveStringInput();
        Boolean isStudent = null;
        String studentNumber = null;
        if (answer.equals("yes")) {
            isStudent = true;
            System.out.println("Enter Your Spouse StudentNumber");
            studentNumber = GiveInput.giveStringInput();
        } else if (answer.equals("no")) {
            isStudent = false;
        }

        StudentSpouse studentSpouse = new StudentSpouse(firstName, studentNumber, lastName, nationalNumber, isStudent, student);
        validationSpouse(studentSpouse);
        return studentSpouse;
    }

    private static Date getDate() {
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


    public static boolean checkDateFirstTerm(Date date) {

        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDate();

        if (month == 11 && day >= 1 && day <= 7 && year >= 1000) {
            return true;
        } else {
            return false;
        }
    }

    private static void validationLoan(Loan loan) {
        Set<ConstraintViolation<Loan>> violations = Validate.validator.validate(loan);
        if (violations.isEmpty()) {
            AppContext.getLoanService().saveOrUpdate(loan);
            System.out.println("Your Loan add sucsessfully");
        } else {
            for (ConstraintViolation<Loan> violation : violations) {
                System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
            }
        }
    }

    private static void validationSpouse(StudentSpouse studentSpouse) {
        Set<ConstraintViolation<StudentSpouse>> violations = Validate.validator.validate(studentSpouse);
        if (violations.isEmpty()) {
            AppContext.getStudentSpouseService().saveOrUpdate(studentSpouse);
            System.out.println("Your Spouse add sucsessfully");
        } else {
            for (ConstraintViolation<StudentSpouse> violation : violations) {
                System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
            }
        }
    }

    private Boolean isStudentHaveDorm(Student student){
        return findCityStudent(student).equals(student.getCityOfUniversity());
    }


    private Boolean isSpouseHasHouseLoan(StudentSpouse studentSpouse, Student student) {
        if(AppContext.getStudentSpouseService().isSpouseStudent(student.getId())){
            Student byStudentNumber = AppContext.getStudentService().findByStudentNumber(studentSpouse.getNationalNumber());
            if (AppContext.getLoanService().typeLoanCategoryByStudentId(byStudentNumber.getId()) == 1 ||
                    AppContext.getLoanService().typeLoanCategoryByStudentId(byStudentNumber.getId()) == 2 ||
                    AppContext.getLoanService().typeLoanCategoryByStudentId(byStudentNumber.getId()) == 3) {
                return true;
        }

        } else {
            return false;
        }
        return false;
    }

    private String findCityStudent(Student student) {
        String[] city = student.getAddress().split("/");
        return city[0];
    }
}
