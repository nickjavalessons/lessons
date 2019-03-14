package lesson3;

import java.util.Arrays;
import java.util.Scanner;

public class Lesson3 {
    public static void main(String[] args) {
        Lesson3 l = new Lesson3();
        //l.task1();
        //l.task2();
        //l.task3();
        //l.task4();
        //l.strings1();
        //l.strings2();
        l.strings3();

    }
    private void task1(){
        int[] a = new int[10];
        for(int i = 0; i < 10; i++){
            a[i] = i*2 + 2;
        }
        System.out.println(Arrays.toString(a));
        for(int i: a){
            System.out.println(i);
        }
    }
    private void task2(){
        int[] a = new int[50];
        for (int i = 0; i < a.length ; i++) {
            a[i] = 2*i + 1;
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        for (int i = a.length - 1; i >= 0; i--) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    private void task3(){
        int[] a = new int[15];
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random()*10);
            if(a[i]%2 == 0) count++;
        }
        System.out.println(Arrays.toString(a));
        System.out.println("Четных " + count);


    }
    private void task4(){
        Scanner s = new Scanner(System.in);
        boolean ok = false;
        int value = 0;
        System.out.println("Введите натуральное четное число");
        while (!ok){
            String input = s.next();
            try {
                value = Integer.parseInt(input);
                if(value%2 == 0) ok = true;
                else System.out.println("Вас просили четное");
            } catch (NumberFormatException e){
                System.out.println("Вас просили число");
            }
        }
        int[] a = new int[value];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int)((Math.random()*11) - 5);
        }
        int absLeft = 0;
        int absRight = 0;
        for (int i = 0; i < a.length/2; i++) {
            absLeft = absLeft + Math.abs(a[i]);
            absRight = absRight + Math.abs(a[a.length - 1 - i]);
        }
        String message;
        if(absLeft == absRight) message = "Суммы модулей равны";
        else if(absLeft > absRight) message = " Сумма левой части больше";
        else message = "Сумма правой части больше";
        System.out.println(Arrays.toString(a));
        System.out.println(message);
    }

    private void strings1(){
        Scanner s = new Scanner(System.in);
        String string = "Съешь еще этих сладких французских булок да выпей чаю";
        String substring1;
        String substring2;
        System.out.println(string);
        System.out.println("Введите строку для замены");
        substring1 = s.next();
        System.out.println("Введите новую строку");
        substring2 = s.next();
        string = string.replaceAll(substring1, substring2);
        System.out.println(string);

    }
    private void strings2(){
        String string = "abc cde def";
        StringBuilder sb = new StringBuilder(string);
        sb.reverse();
        string = sb.toString().replaceAll("(?s)(.)(?=.*\\1)", "").replaceAll(" ", "" );
        string = new StringBuilder(string).reverse().toString();
        System.out.println(string);
    }
    private void strings3(){
        String string = "дом 48, корпус 9, парадная 7, этаж 4";
        //string =  string.replaceAll("[^0-9 $]", "");
        String[] numbersInString = string.replaceAll("[^0-9 $]", "").replaceAll("\\s+", " ").trim().split(" ");
        //String[] numbersInString = string.split("[^0-9 $]");
        int[] numbersInInt = new int[numbersInString.length];
        System.out.println(string);
        System.out.println(Arrays.toString(numbersInString));
        for (int i = 0; i < numbersInString.length; i++) {
            numbersInInt[i] = Integer.parseInt(numbersInString[i].replaceAll(" ", ""));
        }
        System.out.println(Arrays.toString(numbersInInt));
    }
}
