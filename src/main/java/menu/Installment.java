package menu;

import com.github.eloyzone.jalalicalendar.DateConverter;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import entity.Grade;
import util.GiveInput;
import util.SecurityContext;

import java.time.LocalDate;

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
        if ((SecurityContext.getStudent().getGrade().equals(Grade.Associate) || SecurityContext.getStudent().getGrade().equals(Grade.Masters_Discontinuous))
                && gregorian.getYear() == SecurityContext.getStudent().getYearOfEntry().getYear() + 2
                || (SecurityContext.getStudent().getGrade().equals(Grade.Bachelor_Discontinuous) || SecurityContext.getStudent().getGrade().equals(Grade.Bachelor_Continuous))
                && gregorian.getYear() == SecurityContext.getStudent().getYearOfEntry().getYear() + 4
                || SecurityContext.getStudent().getGrade().equals(Grade.Masters_Continuous)
                && gregorian.getYear() == SecurityContext.getStudent().getYearOfEntry().getYear() + 6
                || (SecurityContext.getStudent().getGrade().equals(Grade.PHD_Continuous) || SecurityContext.getStudent().getGrade().equals(Grade.PHD_Discontinuous) || SecurityContext.getStudent().getGrade().equals(Grade.PHD_professional))
                && gregorian.getYear() == SecurityContext.getStudent().getYearOfEntry().getYear() + 5) {
            Boolean flag = true;
            Integer select = 0;
            while (flag) {
                System.out.println("1_Payed installment");
                System.out.println("2_Not Payed installment");
                System.out.println("3_Pay installment");
                System.out.println("4_Exit");
                select = GiveInput.giveIntegerInput();
                switch (select) {
                    case 1 -> System.out.println("1");
                    case 2 -> System.out.println("2");
                    case 3 -> System.out.println("3");
                    case 4 -> flag = false;
                    default -> System.out.println("Wrong");
                }
            }
        } else {
            System.out.println("Can't see this menu");
        }
    }
}
