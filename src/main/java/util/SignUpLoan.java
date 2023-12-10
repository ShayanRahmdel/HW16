package util;

import entity.*;

import javax.validation.ConstraintViolation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class SignUpLoan {
    Tuition tuition = new Tuition();
    private Loan loan=new Loan();
    public void menu(Student student) {

        Boolean flag = true;
        Integer select = 0;
        while (flag) {
            System.out.println("1_ADD Your Creditcard:");
            System.out.println("2_TuitionLoan");
            System.out.println("3_EducationLoan");
            System.out.println("4_HousingLoan");
            System.out.println("5_Exit");
            select = GiveInput.giveIntegerInput();
            switch (select) {
                case 1 -> getCreditInfo(student);
                case 2 -> tuition.tuitionLoan(student);
                case 3 -> educationalLoan(student);
                case 4 -> housingLoan(student);
                case 5 ->flag=false;
                default -> System.out.println("Wrong");
            }
        }
    }




    private void educationalLoan(Student student) {
        System.out.print("Enter Today date (format: dd/MM/yyyy): ");
        Date date = getDate();
        if (checkDateFirstTerm(date)) {
            System.out.println("You Can sign for Loan");
        } else {
            System.out.println("You cant sign for Loan in this Date");
        }


    }

    private void housingLoan(Student student) {
        System.out.print("Enter Today date (format: dd/MM/yyyy): ");
        Date date = getDate();
        if (checkDateFirstTerm(date)) {
            System.out.println("You Can sign for Loan");
            getCreditInfo(student);

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


    private void getCreditInfo(Student student) {
        System.out.println("Enter your CreditCard Number ");
        String creditNummber = GiveInput.giveStringInput();
        System.out.println("Enter Your cvv2:");
        String cvv2 = GiveInput.giveStringInput();
        System.out.println("Enter epireDate:");
        Date date = getDate();
        System.out.println("Enter Your Bank name:");
        Bank bank = findBank(creditNummber);
        CreditCard creditCard = new CreditCard(creditNummber, cvv2, date, bank, student);
        validation(creditCard);


    }

    private Bank findBank(String cardNumber) {
        Bank bank = null;
        if (cardNumber.startsWith("6037")) {
            bank = Bank.Meli;
            return bank;
        } else if (cardNumber.startsWith("5894")) {
            bank = Bank.Refah;
            return bank;
        } else if (cardNumber.startsWith("6280")) {
            bank = Bank.Maskan;
            return bank;
        } else if (cardNumber.startsWith("5859")) {
            bank = Bank.Tejarat;
            return bank;
        }
        return bank;
    }

    private static void validation(CreditCard card) {
        Set<ConstraintViolation<CreditCard>> violations = Validate.validator.validate(card);
        if (violations.isEmpty()) {
            AppContext.getCreditCardService().saveOrUpdate(card);
            System.out.println("Your card add sucsessfully");
        } else {
            for (ConstraintViolation<CreditCard> violation : violations) {
                System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
            }
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
}
