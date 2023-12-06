package util;

import java.util.Random;

public class GenerateRandomPassword {


    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "@$!%*?&";

    public static String generateRandomPassword() {
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        // Append a random uppercase character
        password.append(UPPERCASE_CHARS.charAt(random.nextInt(UPPERCASE_CHARS.length())));

        // Append a random lowercase character
        password.append(LOWERCASE_CHARS.charAt(random.nextInt(LOWERCASE_CHARS.length())));

        // Append a random digit
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));

        // Append a random special character
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        // Append remaining characters to meet length requirements
        int remainingLength = 4; // Random length between 5 and 7
        for (int i = 0; i < remainingLength; i++) {
            String allChars = LOWERCASE_CHARS + UPPERCASE_CHARS + DIGITS + SPECIAL_CHARS;
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        return password.toString();
    }
}
