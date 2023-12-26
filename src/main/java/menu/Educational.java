package menu;

import entity.*;
import util.AppContext;
import util.GiveInput;
import util.SecurityContext;
import util.Validate;

import javax.validation.ConstraintViolation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Educational {
    private Loan loan = new Loan();

    public void educationalLoan() {
        System.out.println("You Can sign for Loan");
        System.out.println("Which bank want get Loan?\n");
        System.out.println(AppContext.getCreditCardService().findCreditById(SecurityContext.getStudent().getId()));
        String bank = GiveInput.giveStringInput();
        Bank bank1 = null;

        try {
            bank1 = Bank.valueOf(bank);
        } catch (IllegalArgumentException e) {
            System.out.println("enter valid bank");
        }
        if (duplicateLoan(SecurityContext.getStudent(), SecurityContext.getTodayDate()).equals(false)) {
            if (SecurityContext.getStudent().getGrade().equals(Grade.Associate) || SecurityContext.getStudent().getGrade().equals(Grade.Bachelor_Continuous) ||
                    SecurityContext.getStudent().getGrade().equals(Grade.Bachelor_Discontinuous)) {
                LoanCategory loanCategory = new LoanCategory(4);
                loan = new Loan(null, SecurityContext.getTodayDate(), loanCategory, SecurityContext.getStudent());
                validationLoan(loan);
                AppContext.getCreditCardService().updateBalanceById(1900000.0, bank1, SecurityContext.getStudent().getId());
                if (AppContext.getLoanService().typeLoanCategoryByStudentIdOrderById(SecurityContext.getStudentId()) == 4) {


                    Integer year = returnYear(SecurityContext.getStudent());
                    createInstallment(1900000.0, SecurityContext.getStudent().getYearOfEntry().getYear() + year);
                }

            }
            if (SecurityContext.getStudent().getGrade().equals(Grade.Masters_Continuous) || SecurityContext.getStudent().getGrade().equals(Grade.Masters_Discontinuous) ||
                    SecurityContext.getStudent().getGrade().equals(Grade.PHD_professional) || SecurityContext.getStudent().getGrade().equals(Grade.PHD_Continuous)) {
                LoanCategory loanCategory = new LoanCategory(5);
                loan = new Loan(null, SecurityContext.getTodayDate(), loanCategory, SecurityContext.getStudent());
                validationLoan(loan);
                AppContext.getCreditCardService().updateBalanceById(2250000.0, bank1, SecurityContext.getStudent().getId());
                if (AppContext.getLoanService().typeLoanCategoryByStudentIdOrderById(SecurityContext.getStudentId()) == 5) {


                    Integer year = returnYear(SecurityContext.getStudent());
                    createInstallment(2250000.0, SecurityContext.getStudent().getYearOfEntry().getYear() + year);
                }

            }
            if (SecurityContext.getStudent().getGrade().equals(Grade.PHD_Discontinuous)) {
                LoanCategory loanCategory = new LoanCategory(6);
                loan = new Loan(null, SecurityContext.getTodayDate(), loanCategory, SecurityContext.getStudent());
                validationLoan(loan);
                AppContext.getCreditCardService().updateBalanceById(2600000.0, bank1, SecurityContext.getStudent().getId());
                if (AppContext.getLoanService().typeLoanCategoryByStudentIdOrderById(SecurityContext.getStudentId()) == 6) {


                    Integer year = returnYear(SecurityContext.getStudent());
                    createInstallment(2600000.0, SecurityContext.getStudent().getYearOfEntry().getYear() + year);
                }
            }
        } else {
            System.out.println("You already signed for Loan");
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

    public static Boolean studentHasActiveLoan(Student student, LocalDate date) {
        List<Loan> loans = student.getLoans();
        if (!loans.isEmpty()) {
            try {
                for (Loan loan : loans) {
                    if (loan.getLoanCategory().getTypeLoan().equals(TypeLoan.EducationLoan) &&
                            loan.getDateofRegistration().getYear() == date.getYear())
                        return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return false;
        }
        return false;
    }

    private void createInstallment(Double loanAmount, Integer year) {
        List<PayInstallment> installments = new ArrayList<>();
        LocalDate repaymentDate = LocalDate.of(year, 3, 21);
        double repayAmount = (loanAmount * 4) / 100 + loanAmount;
        int count = 1;
        for (int i = 0; i < 5; i++) {
            double amount = (repayAmount / 31) * Math.pow(2, i);
            for (int j = 0; j < 12; j++) {
                PayInstallment payInstallment = new PayInstallment();
                payInstallment.setNumber(count++);
                payInstallment.setAmount(amount / 12);
                payInstallment.setDueDate(repaymentDate);
                payInstallment.setLoan(loan);
                installments.add(payInstallment);
                repaymentDate = repaymentDate.plusMonths(1);

            }
        }
        for (PayInstallment ins : installments) {
            AppContext.getInstallmentService().saveOrUpdate(ins);
        }
    }


    private Integer returnYear(Student student) {
        if (student.getGrade().equals(Grade.Associate) || student.getGrade().equals(Grade.Masters_Discontinuous)) {
            return 2;
        }
        if (student.getGrade().equals(Grade.Bachelor_Discontinuous) || student.getGrade().equals(Grade.Bachelor_Continuous)) {
            return 4;
        } else if (student.getGrade().equals(Grade.Masters_Continuous)) {
            return 6;
        } else return 5;
    }

    private Boolean isPickLoanBefoe(Student student,LocalDate date) {
        Loan pastLoan = AppContext.getLoanService().isPickLoanBefore(student);
        if (pastLoan==null){
            return false;
        }
        else if (pastLoan.getDateofRegistration().getYear() == date.getYear()&&pastLoan.getLoanCategory().getTypeLoan().equals(TypeLoan.EducationLoan)){
            return true;
        }
        return false;
    }

    private Boolean duplicateLoan(Student student,LocalDate date) {
        List<Loan> loans = AppContext.getLoanService().isPickLoanBefore2(student);
        for (Loan loan : loans) {
            if (loan.getDateofRegistration().getYear()==date.getYear()){
                return true;
            }
        }
        return false;
    }
}
