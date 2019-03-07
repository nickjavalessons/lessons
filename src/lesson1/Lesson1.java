package lesson1;

public class Lesson1 {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
    }
    private static void task1(){
        int high = 20;
        int width = 45;
        int s = high*width;
        String result = "Площадь равна " + s + " квадратного чего-то, " + ((high>width)?"Высота больше ширины ":"Ширина больше высоты");
        System.out.println(result);
    }

    private static void task2(){
        int s = 10 * 10*10; //м2
        int sGardenBed = 15*25;
        int freeSpace = s - sGardenBed;
        System.out.println("Свободно " + freeSpace + " метров квадратных");
    }

    private static void task3(){
        int s1 = 15 * 100 /*to sm sq*/;
        int s2 = 600;
        int sDiff = s1 - s2;
        System.out.println("Площадь овала " + sDiff);
    }

    private static void task4(){
        double n = 8.5;
        double m = 11.45;
        double ten = 10;
        System.out.println((Math.abs(ten - n)> Math.abs(ten - m))? "m ближе": "n ближе");
    }

    private static void task5(){
        double a = - 4.;
        double b = 28.;
        double c =  - 49.;
        double D = b * b - 4 * a * c;
        double epsilon = 0.0000001;
        if( Math.abs(D) < epsilon ) {
            double x = -b/(2*a);
            System.out.println("Один вещественный корень x = " + x);
        } else if (D < 0) System.out.println("Действительных корней нет");
        else {
            double x1 = (-b + Math.sqrt(D))/(2 * a);
            double x2 = (-b - Math.sqrt(D))/(2 * a);
            System.out.println("Два действительных корня x1 = " + x1 + ", x2 = " + x2);
        }
    }

    private static void task6(){
        int value = 465;
        byte max = (byte) (value%10);
        byte digit;
        while(value > 0){
            digit =(byte) (value % 10);
            if( digit > max) max = digit;
            value /= 10;
        }
        System.out.println("Максимальная цифра " + max);
    }
}
