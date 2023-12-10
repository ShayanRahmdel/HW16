package util;

import entity.*;

import javax.validation.ConstraintViolation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Tuition {
    Loan loan = new Loan();
    public void tuitionLoan(Student student) {
        System.out.print("Enter Today date (format: dd/MM/yyyy): ");
        Date date = getDate();
        if (checkDateFirstTerm(date)) {
            System.out.println("You Can sign for Loan");
            System.out.println("Which bank want get Loan?\n Meli,\n" +
                    "    Refah,\n" +
                    "    Tejarat,\n" +
                    "    Maskan");
            System.out.println(AppContext.getCreditCardService().findCreditById(student.getId()));
            String bank = GiveInput.giveStringInput();
            Bank bank1 = null;
            try {
                bank1 = Bank.valueOf(bank);
            } catch (IllegalArgumentException e) {
                System.out.println("enter valid bank");
            }
            if (isUniversityTypeValid(student)&&student.getGrade().equals(Grade.Associate) || student.getGrade().equals(Grade.Bachelor_Continuous) ||
                    student.getGrade().equals(Grade.Bachelor_Discontinuous)) {
                LoanCategory loanCategory = new LoanCategory(3);
                loan = new Loan(1300000.0, null, date, loanCategory, student);
                validationLoan(loan);
                AppContext.getCreditCardService().updateBalanceById(1300000.0, bank1, student.getId());
            }
            if (isUniversityTypeValid(student)&&student.getGrade().equals(Grade.Masters_Continuous) || student.getGrade().equals(Grade.Masters_Discontinuous) ||
                    student.getGrade().equals(Grade.PHD_professional) || student.getGrade().equals(Grade.PHD_Continuous)) {
                LoanCategory loanCategory = new LoanCategory(3);
                loan = new Loan(2600000.0, null, date, loanCategory, student);
                validationLoan(loan);
                AppContext.getCreditCardService().updateBalanceById(2600000.0, bank1, student.getId());
            }
            if (isUniversityTypeValid(student)&&student.getGrade().equals(Grade.PHD_Discontinuous)) {
                LoanCategory loanCategory = new LoanCategory(3);
                loan = new Loan(65000000.0, null, date, loanCategory, student);
                validationLoan(loan);
                AppContext.getCreditCardService().updateBalanceById(65000000.0, bank1, student.getId());
            }else {
                System.out.println("Maybe Your State is wrong");
            }

        } else {
            System.out.println("You cant sign for Loan in this Date");
        }
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
    private Boolean isUniversityTypeValid(Student student) {
        TypeOfUniversity typeOfUniversity = student.getTypeOfUniversity();
        TypeStateUni typeStateUni = student.getTypeStateUni();
        return typeOfUniversity == TypeOfUniversity.Non_State ||
                typeOfUniversity == TypeOfUniversity.Non_profit_university ||
                typeOfUniversity == TypeOfUniversity.Pardis ||
                typeOfUniversity == TypeOfUniversity.Excess_capacity ||
                typeOfUniversity == TypeOfUniversity.Payam_noor ||
                typeOfUniversity == TypeOfUniversity.Applied_Science||
                typeStateUni==TypeStateUni.Shabane;
    }
}
