package menu;

import entity.*;
import util.AppContext;
import util.GiveInput;
import util.SecurityContext;
import util.Validate;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Tuition {
    Loan loan = new Loan();


    public void tuitionLoan() {


        System.out.println("You Can sign for Loan");
        System.out.println("Which bank want get Loan?\n Meli,\n" +
                "    Refah,\n" +
                "    Tejarat,\n" +
                "    Maskan");
        System.out.println(AppContext.getCreditCardService().findCreditById(SecurityContext.getStudent().getId()));
        String bank = GiveInput.giveStringInput();
        Bank bank1 = null;
        try {
            bank1 = Bank.valueOf(bank);
        } catch (IllegalArgumentException e) {
            System.out.println("enter valid bank");
        }
        if (isPickLoanBefoe(SecurityContext.getStudent(),SecurityContext.getTodayDate()).equals(false)) {
            if (isUniversityTypeValid(SecurityContext.getStudent()) && SecurityContext.getStudent().getGrade().equals(Grade.Associate) || SecurityContext.getStudent().getGrade().equals(Grade.Bachelor_Continuous) ||
                    SecurityContext.getStudent().getGrade().equals(Grade.Bachelor_Discontinuous)) {
                LoanCategory loanCategory = new LoanCategory(9);
                loan = new Loan(null, SecurityContext.getTodayDate(), loanCategory, SecurityContext.getStudent());
                validationLoan(loan);
                AppContext.getCreditCardService().updateBalanceById(1300000.0, bank1, SecurityContext.getStudent().getId());
                if (AppContext.getLoanService().typeLoanCategoryByStudentIdOrderById(SecurityContext.getStudentId()) == 9) {


                    Integer year = returnYear(SecurityContext.getStudent());
                    createInstallment(1300000.0, SecurityContext.getStudent().getYearOfEntry().getYear() + year);
                }

            }
            if (isUniversityTypeValid(SecurityContext.getStudent()) && SecurityContext.getStudent().getGrade().equals(Grade.Masters_Continuous) || SecurityContext.getStudent().getGrade().equals(Grade.Masters_Discontinuous) ||
                    SecurityContext.getStudent().getGrade().equals(Grade.PHD_professional) || SecurityContext.getStudent().getGrade().equals(Grade.PHD_Continuous)) {
                LoanCategory loanCategory = new LoanCategory(8);
                loan = new Loan(null, SecurityContext.getTodayDate(), loanCategory, SecurityContext.getStudent());
                validationLoan(loan);
                AppContext.getCreditCardService().updateBalanceById(2600000.0, bank1, SecurityContext.getStudent().getId());
                if (AppContext.getLoanService().typeLoanCategoryByStudentIdOrderById(SecurityContext.getStudentId()) == 8) {

                    Integer year = returnYear(SecurityContext.getStudent());
                    createInstallment(2600000.0, SecurityContext.getStudent().getYearOfEntry().getYear() + year);
                }

            }
            if (isUniversityTypeValid(SecurityContext.getStudent()) && SecurityContext.getStudent().getGrade().equals(Grade.PHD_Discontinuous)) {
                LoanCategory loanCategory = new LoanCategory(7);
                loan = new Loan(null, SecurityContext.getTodayDate(), loanCategory, SecurityContext.getStudent());
                validationLoan(loan);
                AppContext.getCreditCardService().updateBalanceById(65000000.0, bank1, SecurityContext.getStudent().getId());
                if (AppContext.getLoanService().typeLoanCategoryByStudentIdOrderById(SecurityContext.getStudentId()) == 7) {

                    Integer year = returnYear(SecurityContext.getStudent());
                    createInstallment(2600000.0, SecurityContext.getStudent().getYearOfEntry().getYear() + year);
                }
            }
        } else System.out.println("You already have a loan");


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
                typeOfUniversity == TypeOfUniversity.Applied_Science ||
                typeStateUni == TypeStateUni.Shabane;
    }


    public static Boolean studentHasActiveLoan(Student student, LocalDate date) {
        List<Loan> loans = student.getLoans();
        if (!loans.isEmpty()) {
            try {
                for (Loan loan : loans) {
                    if (loan.getLoanCategory().getTypeLoan().equals(TypeLoan.TuitionLoan) &&
                            loan.getDateofRegistration().getYear() == date.getYear())
                        return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
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
        if (pastLoan.getDateofRegistration().getYear() == date.getYear()&&pastLoan.getLoanCategory().getTypeLoan().equals(TypeLoan.TuitionLoan)){
            return true;
        }
        return false;
    }
}

