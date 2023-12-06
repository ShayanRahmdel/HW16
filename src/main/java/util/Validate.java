package util;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validate {

    private static Scanner input = new Scanner(System.in);
    public static ValidatorFactory validatorFactory = javax.validation.Validation.buildDefaultValidatorFactory();

    public static Validator validator = validatorFactory.getValidator();

    public static Boolean isValidPassword(String password){
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8}$");
        return password.matches(pattern.pattern());
    }

    public static Boolean isNationalCode(String password){
        Pattern pattern = Pattern.compile("^([0-9]){10}$");
        return password.matches(pattern.pattern());
    }

    public static String nationalCodeValidation() {
        String nationalCode="";
        boolean flag = true;
        while (flag){
            nationalCode = input.next();
            if (Validate.isNationalCode(nationalCode)){
                flag = false;
            }else System.out.println("enter valid userName");
        }
        return nationalCode;
    }


}
