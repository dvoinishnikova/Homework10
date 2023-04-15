package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class User {
    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public void setName(String name) {
        this.name =name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public static void main(String[] args) {
        String inputFile = "./files/file2.txt";
        String outputFile = "./files/user.json";
        List<User> userList = readUserListFromFile(inputFile);
        writeUserListToJsonFile(userList, outputFile);
    }
    public static List<User> readUserListFromFile(String inputFile) {
        List<User> userList = new ArrayList<>();
        try (FileReader reader = new FileReader(inputFile)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
            String[] lines = sb.toString().split("\n");
            String[] header = lines[0].split(" ");
            for (int i = 1; i < lines.length; i++) {
                String[] columns = lines[i].split(" ");
                User user = new User();
                for (int j = 0; j < columns.length; j++) {
                    String columnName = header[j];
                    String columnValue = columns[j];
                    if ("name".equalsIgnoreCase(columnName)) {
                        user.setName(columnValue);
                    } else if ("age".equalsIgnoreCase(columnName)) {
                        user.setAge(Integer.parseInt(columnValue));
                    }
                }
                userList.add(user);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public static void writeUserListToJsonFile(List<User> userList, String outputFile) {
        try (FileWriter writer = new FileWriter(outputFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(userList, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
