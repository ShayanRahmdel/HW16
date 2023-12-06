package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GiveInput {

    private static Scanner input = new Scanner(System.in);

    public static String giveStringInput() {
        String i;
        while (true) {
            try {
                i = input.next();

                return i;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Enter just String Please");
            }
        }
    }

    public static Double giveDoubleInput() {
        Double i;
        while (true) {
            try {
                i = input.nextDouble();

                return i;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Enter just Double Please");
            }
        }
    }

    public static Integer giveIntegerInput() {
        int i;
        while (true) {
            try {
                i = input.nextInt();
                input.nextLine();
                return i;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Enter just number Please");
            }
        }
    }

    public static Character giveCharacterInput() {
        String gender = input.next();

        if (gender.length() == 1) {
            char character = gender.charAt(0);
            return character;

        } else {
            System.out.println("Invalid input. Please enter a single character.");
            return null;
        }

    }
}
