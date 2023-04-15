package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task3 {
    public static void main(String[] args) {
        String fileName = "./files/words.txt";
        Map<String, Integer> wordFrequency = countWordFrequency(fileName);
        printWordFrequency(wordFrequency);
    }

    public static Map<String, Integer> countWordFrequency(String fileName) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            int c;
            StringBuilder sb = new StringBuilder();
            while ((c = fileReader.read()) != -1) {
                char ch = (char) c;
                if (Character.isLetter(ch)) {
                    sb.append(ch);
                } else if (sb.length() > 0) {
                    String word = sb.toString();
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    sb.setLength(0);
                }
            }
            if (sb.length() > 0) {
                String word = sb.toString();
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordFrequency;
    }

    public static void printWordFrequency(Map<String, Integer> wordFrequency) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordFrequency.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
