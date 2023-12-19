package menu;

import entity.Student;
import util.AppContext;
import util.GiveInput;
import util.SecurityContext;

import javax.persistence.NoResultException;

public class MainMenu {
    SignUpStudent signUpStudent = new SignUpStudent();
    SignUpLoan signUpLoan = new SignUpLoan();

    StudentMenu studentMenu = new StudentMenu();

    public void menu(){

        Boolean flag = true;
        Integer select = 0;
        while (flag) {
            System.out.println("1_Sign Up");
            System.out.println("2_Sign In");
            System.out.println("3_Exit");
            select = GiveInput.giveIntegerInput();
            switch (select) {
                case 1 -> signUpStudent.signUp();
                case 2 ->signIn();
                case 3 ->flag=false;
                default -> System.out.println("Wrong");
            }
        }
    }
    private void signIn(){
        System.out.println("Enter UserName:");
        String userName = GiveInput.giveStringInput();
        System.out.println("Enter Password:");
        String password = GiveInput.giveStringInput();

        try {
            Student student = AppContext.getStudentService().findByUserNameAndPassword(userName, password);
            System.out.println("login sucsessfully");
            SecurityContext.fillContext(student);
            studentMenu.menu();
        }catch (NoResultException e){
            System.out.println("not found this user");
        }
    }
}
