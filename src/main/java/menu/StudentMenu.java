package menu;

import entity.Student;
import menu.SignUpLoan;
import util.GiveInput;

public class StudentMenu {
    SignUpLoan signUpLoan = new SignUpLoan();
    public void menu(Student student){
        Boolean flag = true;
        Integer select = 0;
        while (flag) {
            System.out.println("1_signUp Loan");
            System.out.println("2_Installment");
            System.out.println("3_Exit");
            select = GiveInput.giveIntegerInput();
            switch (select) {
                case 1->signUpLoan.menu(student);
                case 2-> System.out.println("installment");
                case 3 ->flag=false;
                default -> System.out.println("Wrong");
            }
        }

    }


}
