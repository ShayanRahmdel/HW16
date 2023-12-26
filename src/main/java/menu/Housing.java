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

public class Housing {
    private Loan loan = new Loan();

    public void housingLoan() {

        System.out.println("You Can sign for Loan");
        System.out.println("======================");
        if (isGetLoanBefore(SecurityContext.getStudent()).equals(false)) {
            System.out.println("You already signed");
        } else {
            StudentSpouse spouseInfo = getSpouseInfo(SecurityContext.getStudent());
            if (isSpouseHasHouseLoan(spouseInfo, SecurityContext.getStudent()).equals(false) || isGetLoanBefore(SecurityContext.getStudent()).equals(false)) {
                System.out.println("You Or Your Spouse Already have houseLoan or you already have");
            } else {
                System.out.println("Enter Your HouseTrackingCode");
                Integer houseTrackCode = GiveInput.giveIntegerInput();
                System.out.println("Which bank want get Loan?\n");
                System.out.println(AppContext.getCreditCardService().findCreditById(SecurityContext.getStudent().getId()));
                String bank = GiveInput.giveStringInput();
                Bank bank1 = null;
                try {
                    bank1 = Bank.valueOf(bank);
                } catch (IllegalArgumentException e) {
                    System.out.println("enter valid bank");
                }
                if (SecurityContext.getStudent().getCityOfUniversity().equals("tehran") && !isStudentHaveDorm(SecurityContext.getStudent())) {
                    LoanCategory loanCategory = new LoanCategory(1);
                    loan = new Loan(houseTrackCode, SecurityContext.getTodayDate(), loanCategory, SecurityContext.getStudent());
                    validationLoan(loan);
                    AppContext.getCreditCardService().updateBalanceById(32000000.0, bank1, SecurityContext.getStudent().getId());
                    System.out.println("Your loan sucessfully added");
                    if (AppContext.getLoanService().typeLoanCategoryByStudentIdOrderById(SecurityContext.getStudentId()) == 1) {


                        Integer year = returnYear(SecurityContext.getStudent());
                        createInstallment(32000000.0, SecurityContext.getStudent().getYearOfEntry().getYear() + year);
                    }

                } else if (!isStudentHaveDorm(SecurityContext.getStudent()) && SecurityContext.getStudent().getCityOfUniversity().equals("gilan") || !isStudentHaveDorm(SecurityContext.getStudent()) && SecurityContext.getStudent().getCityOfUniversity().equals("esfahan") ||
                        !isStudentHaveDorm(SecurityContext.getStudent()) && SecurityContext.getStudent().getCityOfUniversity().equals("azarbayjansharghi") || !isStudentHaveDorm(SecurityContext.getStudent()) && SecurityContext.getStudent().getCityOfUniversity().equals("fars") ||
                        !isStudentHaveDorm(SecurityContext.getStudent()) && SecurityContext.getStudent().getCityOfUniversity().equals("khozestan") || !isStudentHaveDorm(SecurityContext.getStudent()) && SecurityContext.getStudent().getCityOfUniversity().equals("ghom") ||
                        !isStudentHaveDorm(SecurityContext.getStudent()) && SecurityContext.getStudent().getCityOfUniversity().equals("khorasan") || !isStudentHaveDorm(SecurityContext.getStudent()) && SecurityContext.getStudent().getCityOfUniversity().equals("alborz")) {
                    LoanCategory loanCategory = new LoanCategory(2);
                    loan = new Loan(houseTrackCode, SecurityContext.getTodayDate(), loanCategory, SecurityContext.getStudent());
                    validationLoan(loan);
                    AppContext.getCreditCardService().updateBalanceById(26000000.0, bank1, SecurityContext.getStudent().getId());
                    System.out.println("Your loan sucessfully added");
                    if (AppContext.getLoanService().typeLoanCategoryByStudentIdOrderById(SecurityContext.getStudentId()) == 2) {


                        Integer year = returnYear(SecurityContext.getStudent());
                        createInstallment(26000000.0, SecurityContext.getStudent().getYearOfEntry().getYear() + year);
                    }
                } else {
                    LoanCategory loanCategory = new LoanCategory(3);
                    loan = new Loan(houseTrackCode, SecurityContext.getTodayDate(), loanCategory, SecurityContext.getStudent());
                    validationLoan(loan);
                    AppContext.getCreditCardService().updateBalanceById(19500000.0, bank1, SecurityContext.getStudent().getId());
                    System.out.println("Your loan sucessfully added");
                    if (AppContext.getLoanService().typeLoanCategoryByStudentIdOrderById(SecurityContext.getStudentId()) == 3) {


                        Integer year = returnYear(SecurityContext.getStudent());
                        createInstallment(19500000.0, SecurityContext.getStudent().getYearOfEntry().getYear() + year);
                    }
                }
            }
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

    private Boolean isStudentHaveDorm(Student student) {
        return findCityStudent(student).equals(student.getCityOfUniversity());
    }


    private Boolean isSpouseHasHouseLoan(StudentSpouse studentSpouse, Student student) {
        if (AppContext.getStudentSpouseService().isSpouseStudent(student.getId())) {
            Student byStudentNumber = AppContext.getStudentService().findByStudentNumber(studentSpouse.getNationalNumber());
            List<Integer> allLoans = AppContext.getLoanService().typeLoanCategoryByStudentId(byStudentNumber.getId());
            for (Integer loan : allLoans) {
                if (loan == 1 || loan == 2 || loan == 3) {
                    return false;
                }
            }
        }return true;
    }

    private String findCityStudent(Student student) {
        String[] city = student.getAddress().split("/");
        return city[0];
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

    public static Boolean studentHasActiveLoan(Student student) {
        List<Loan> loans = student.getLoans();
        if (!loans.isEmpty()) {
            try {
                for (Loan loan : loans) {
                    if (loan.getLoanCategory().getTypeLoan().equals(TypeLoan.HousingLoan))
                        return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private Boolean isGetLoanBefore(Student student) {
        List<Integer> allLoans = AppContext.getLoanService().typeLoanCategoryByStudentId(student.getId());
        for (Integer loan:allLoans) {
            if (loan==1||loan==2 || loan==3) {
                return false;

            }
        }
        return true;
    }


}
