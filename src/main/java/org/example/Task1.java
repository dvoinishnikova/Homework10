package org.example;

import java.io.FileReader;
import java.io.IOException;

public class Task1 {
    public static void main(String[] args) {
        String fiePath = "./files/file.txt";
        validatePhoneNumbers(fiePath);

    }
    public static void validatePhoneNumbers(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            StringBuilder phoneNumber = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                char ch = (char) c;
                if (Character.isDigit(ch)) {
                    phoneNumber.append(ch);
                } else if (ch == '(' || ch == ')' || ch == ' ' || ch == '-') {
                    phoneNumber.append(ch);
                } else {
                    String phone = phoneNumber.toString();
                    if (isValidPhoneNumber(phone))
                        System.out.println(phone);
                    phoneNumber.setLength(0);
                }
            }
            String lastPhone = phoneNumber.toString();
            if (isValidPhoneNumber(lastPhone)) {
                System.out.println(lastPhone);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("\\(\\d{3}\\) \\d{3}-\\d{4}"))
            return true;
        if (phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}"))
            return true;
        return false;
    }
}
