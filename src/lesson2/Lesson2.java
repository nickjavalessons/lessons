package lesson2;

public class Lesson2 {
    public static void main(String[] args) {
        task1(3, 9, -1);
        task1(2, 4, 3);
        task1(7, 0, -5);
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
    }
    private static void task1(int a, int b, int c){
        int tmp;
        while (!(a < b && b < c)){
            if(a > b){
                tmp = a;
                a = b;
                b = tmp;
            }
            if(b > c){
                tmp = b;
                b = c;
                c = tmp;
            }
            if(a > c){
                tmp = a;
                a = c;
                c = tmp;
            }
        }
        System.out.println("a: " + a + ", b = " + b + ", c = " + c);
    }
    private static void task2(){
        for(int i = 90; i >= 0; i = i - 5){
            System.out.println(i);
        }
    }
    private static void task3(){
        int exp = 1;
        for(int i = 0; i < 20; i++){
            exp = exp * 2;
            System.out.println(exp);
        }
    }
    private static void task4(){
        System.out.println(isPrime(8291));
    }
    private static void task5(){
        int[] f = new int[11];
        f[0] = 1;
        f[1] = 1;
        for(int i = 2; i < f.length; i++){
            f[i] = f[i - 1] + f[i - 2];
        }
        for(int i: f){
            System.out.print(i + " ");
        }



    }
    private static void task6(){
        int counter = 0;
        for(int i = 0; i < 1000; i++){
            for(int j = 1; j < 1000; j++){
                if(getSumOfDigits(i) == getSumOfDigits(j)) counter++;
            }
        }
        System.out.println(counter);
    }
    private static void task7(){
        int counter = 0;
        for(int i = 0; i < 24; i++){
            for(int j = 0; j < 59; j++){
                if(i == getInvertedValue(j, 2)){
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
    private static void task8(){
        int count = 0;
        for(int i = 1; i < 50001; i++){
            if(Integer.valueOf(i).toString().contains("2")) count++;
        }
        System.out.println(count);
    }
    private static void task9(){
        int count = 0;
        for(int i = 1; i < 100000; i++){
            if(Integer.valueOf(i).toString().contains("4")||Integer.valueOf(i).toString().contains("13")) {
                System.out.println(i);
                count ++;
            }
        }
        System.out.println(count);
    }

    private static boolean isPrime(int primeNumber){
        int limit = (int)Math.sqrt(primeNumber);
        boolean[] A = new boolean[limit + 1];
        for(int i = 0; i < A.length; i++){
            A[i] = true;
        }
        for(int i = 2; i <= limit; i++){
            if(A[i]){
                int j = 0;
                while((i*i + i*j)<= limit){
                    A[i*i + i*j] = false;
                    j++;
                }
            }
        }
        for(int i  = 2; i < A.length; i++){
            if(A[i]){
                if(primeNumber%i == 0) return false;
            }
        }
        return true;
    }
    private static int getSumOfDigits(int a){
        int sum = 0;
        while(a > 0){
            sum = sum + a%10;
            a = a/10;
        }
        return sum;
    }
    private static int getInvertedValue(int a, int length){
        int iValue = 0;
        for(int i = 0; i < length; i++){
            int digit = a%10;
            iValue = iValue*10 + digit;
            a = a/10;
        }
        return iValue;
    }
}
