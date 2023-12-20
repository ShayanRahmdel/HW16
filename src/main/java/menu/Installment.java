package menu;

import com.github.eloyzone.jalalicalendar.DateConverter;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import entity.Grade;
import entity.PayInstallment;
import entity.Student;
import util.AppContext;
import util.GiveInput;
import util.SecurityContext;

import java.time.LocalDate;
import java.util.List;

public class Installment {

    DateConverter dateConverter = new DateConverter();
    public void menu() {
        System.out.print("Enter Today date (format: yyyy/mm/dd): ");
        String date = GiveInput.giveStringInput();
        String[] split = date.split("/");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        JalaliDate jalaliDate = new JalaliDate(year, month, day);
        LocalDate gregorian = dateConverter.jalaliToGregorian(jalaliDate);
        SecurityContext.fillContext(gregorian);
        if ((SecurityContext.getStudent().getGrade().equals(Grade.Associate) || SecurityContext.getStudent().getGrade().equals(Grade.Masters_Discontinuous))
                && gregorian.getYear() >= SecurityContext.getStudent().getYearOfEntry().getYear() + 2
                || (SecurityContext.getStudent().getGrade().equals(Grade.Bachelor_Discontinuous) || SecurityContext.getStudent().getGrade().equals(Grade.Bachelor_Continuous))
                && gregorian.getYear() >= SecurityContext.getStudent().getYearOfEntry().getYear() + 4
                || SecurityContext.getStudent().getGrade().equals(Grade.Masters_Continuous)
                && gregorian.getYear() >= SecurityContext.getStudent().getYearOfEntry().getYear() + 6
                || (SecurityContext.getStudent().getGrade().equals(Grade.PHD_Continuous) || SecurityContext.getStudent().getGrade().equals(Grade.PHD_Discontinuous) || SecurityContext.getStudent().getGrade().equals(Grade.PHD_professional))
                && gregorian.getYear() >= SecurityContext.getStudent().getYearOfEntry().getYear() + 5) {
            Boolean flag = true;
            Integer select = 0;
            while (flag) {
                System.out.println("1_Payed installment");
                System.out.println("2_Not Payed installment");
                System.out.println("3_Pay installment");
                System.out.println("4_Exit");
                select = GiveInput.giveIntegerInput();
                switch (select) {
                    case 1 -> showPayedInstallment(SecurityContext.getStudent());
                    case 2 -> showNotPayedInstallment(SecurityContext.getStudent());
                    case 3 -> payInstallment(SecurityContext.getStudent(),gregorian);
                    case 4 -> flag = false;
                    default -> System.out.println("Wrong");
                }
            }
        } else {
            System.out.println("Can't see this menu");
        }
    }

    private void showPayedInstallment(Student student) {
        List<Object[]> resultList = AppContext.getInstallmentService().showPayedInstallment(student);
        for (Object[] row : resultList) {
            Integer number = (Integer) row[0];
            java.sql.Date paymentDateSql = (java.sql.Date) row[1];
            LocalDate paymentDate = paymentDateSql.toLocalDate();
            JalaliDate jalaliDate = dateConverter.gregorianToJalali(paymentDate.getYear(),paymentDate.getMonth(),paymentDate.getDayOfMonth());

            System.out.println("Number: " + number + ", Payment Date: " + jalaliDate);
        }
    }

    private void showNotPayedInstallment(Student student) {
        List<Object[]> resultList = AppContext.getInstallmentService().showNotPayedInstallment(student);
        for (Object[] row : resultList) {
            Integer number = (Integer) row[0];
            java.sql.Date dueDateSql = (java.sql.Date) row[1];
            LocalDate dueDate = dueDateSql.toLocalDate();
            Double amount = (Double) row[2];
            JalaliDate jalaliDate = dateConverter.gregorianToJalali(dueDate.getYear(),dueDate.getMonth(),dueDate.getDayOfMonth());


            System.out.println("Number: " + number + ", DueDate: " + jalaliDate + ", Amount: " + amount);
        }
    }

    private void payInstallment(Student student,LocalDate localDate) {
        System.out.println("enter your installmentNumber number");
        Integer number = GiveInput.giveIntegerInput();
        System.out.println("enter your due date (format: yyyy/mm/dd)");
        String duedate = GiveInput.giveStringInput();
        String[] split1 = duedate.split("/");
        int year1 = Integer.parseInt(split1[0]);
        int month1 = Integer.parseInt(split1[1]);
        int day1 = Integer.parseInt(split1[2]);
        JalaliDate jalaliDate1 = new JalaliDate(year1, month1, day1);
        LocalDate gregorian2 = dateConverter.jalaliToGregorian(jalaliDate1);
        PayInstallment installment = AppContext.getInstallmentService().findByNumberInstallment(number, SecurityContext.getStudent(),gregorian2);
        System.out.println("You should pay: " + installment.getAmount());
        System.out.println("enter your credit card number");
        String creditNumber = GiveInput.giveStringInput();
        System.out.println("Enter your cvv2");
        String cvv2 = GiveInput.giveStringInput();
        System.out.println("Enter your expiration date (format: yyyy/mm/dd)");
        String date = GiveInput.giveStringInput();
        String[] split = date.split("/");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        JalaliDate jalaliDate = new JalaliDate(year, month, day);
        LocalDate gregorian = dateConverter.jalaliToGregorian(jalaliDate);
        if(AppContext.getCreditCardService().isExitThisCredit(student, creditNumber, cvv2, gregorian).equals(true)) {
            System.out.println("enter your amount");
            Double amount = GiveInput.giveDoubleInput();
            if (Double.compare(amount, installment.getAmount()) == 0){
                installment.setIsPayed(true);
                installment.setPaymentDate(localDate);
                AppContext.getInstallmentService().saveOrUpdate(installment);
                System.out.println("Payment is successful");
            }else {
                installment.setIsPayed(false);
            }
        }else {
            System.out.println("cant find this credit card");
        }


    }

}
