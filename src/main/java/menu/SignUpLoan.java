package menu;

import com.github.eloyzone.jalalicalendar.DateConverter;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import entity.*;
import util.AppContext;
import util.GiveInput;
import util.SecurityContext;
import util.Validate;

import javax.validation.ConstraintViolation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class SignUpLoan {
    Tuition tuition = new Tuition();

    Housing housing = new Housing();
    Educational educational = new Educational();
    private Loan loan = new Loan();
    DateConverter dateConverter = new DateConverter();

    public void menu() {
        System.out.print("Enter Today date (format: yyyy/mm/dd): ");
        String date = GiveInput.giveStringInput();
        String[] split = date.split("/");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        JalaliDate jalaliDate = new JalaliDate(year, month, day);
        if (month == 7 && day >= 1 && day <= 8 ||
                month == 11 && day >= 25 || month == 12 && day <= 2) {
            System.out.println(true);
            LocalDate gregorianDate = dateConverter.jalaliToGregorian(jalaliDate);
            SecurityContext.fillContext(gregorianDate);

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
                    case 1 -> getCreditInfo();
                    case 2 -> tuition.tuitionLoan();
                    case 3 -> educational.educationalLoan();
                    case 4 -> housing.housingLoan();
                    case 5 -> flag = false;
                    default -> System.out.println("Wrong");
                }
            }

        } else {
            System.out.println("You cannot create a loan on this date");
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


    private void getCreditInfo() {
        Student student = SecurityContext.getStudent();
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
