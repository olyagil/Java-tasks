package com.epam.task3.validator;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.regex.Pattern;

public class Validator {

    private static final String REGEX_LOGIN = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{6,19}$";
    private static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    private static final String REGEX_NUMERIC = "-?\\d+(\\.\\d+)?";

    public static boolean checkLogin(String login) {
        return login != null && Pattern.matches(REGEX_LOGIN, login);
    }

    public static boolean checkPassword(String password) {
        return password != null && Pattern.matches(REGEX_PASSWORD, password);
    }

    public static String getPhoto(MultipartFile file) throws IOException {
        byte[] imageBytes = file.getBytes();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static Long getNumber(String num) {
        String newNumber = num.replaceAll(",", "");

        if (newNumber.matches(REGEX_NUMERIC)) {
            return Long.valueOf(newNumber);
        }
        return -1L;
    }
}
