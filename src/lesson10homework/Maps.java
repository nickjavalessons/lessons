package lesson10homework;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Maps {
    public static void main(String[] args) throws IOException {
        new Maps().run();
    }
    private void run() throws IOException {
        ClassLoader loader = Maps.class.getClassLoader();
        File file = new File(loader.getResource("wp.txt").getFile());
        List<String> lines = Files.readAllLines(file.toPath());
        List<String> wordsOfText = new ArrayList<>();
        for(String str: lines){
            String[] words = str.toLowerCase()
                    .replaceAll("\\p{Punct}", " ")
                    .replaceAll("\\d","")
                    .trim()
                    .split("\\s");
            for(String s: words){
                if(s.length() > 0)  wordsOfText.add(s.trim());
            }
        }
        System.out.println(lines);
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "3");
        treeMap.put(1, "1");
        treeMap.put(6, "6");
        treeMap.put(2, "2");
        System.out.println(treeMap);
        task1(wordsOfText);
    }
    private void task1(List<String> list){
        Map<String, Integer> wordsFriquency = new HashMap<>();
        for(String word: list){
            if(wordsFriquency.containsKey(word)){
                wordsFriquency.put(word, wordsFriquency.get(word) + 1);
            } else {
                wordsFriquency.put(word, 1);
            }
        }
        System.out.println(wordsFriquency);
    }

}
