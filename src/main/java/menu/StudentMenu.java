package menu;

import entity.Student;
import menu.SignUpLoan;
import util.GiveInput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentMenu {
    SignUpLoan signUpLoan = new SignUpLoan();

    Installment installment = new Installment();

    public void menu() {
        Boolean flag = true;
        Integer select = 0;
        while (flag) {
            System.out.println("1_signUp Loan");
            System.out.println("2_Installment");
            System.out.println("3_Exit");
            select = GiveInput.giveIntegerInput();
            switch (select) {
                case 1 -> {
                    try {
                        signUpLoan.menu();
                    }catch (Exception e){
                        System.out.println("SomeThing Wrong");
                    }
                }
                case 2 -> {
                    try {
                        installment.menu();
                    }catch (Exception e){
                        System.out.println("SomeThing Wrong");
                    }
                }
                case 3 -> flag = false;
                default -> System.out.println("Wrong");
            }
        }
    }

}
