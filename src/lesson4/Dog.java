package lesson4;

public class Dog {
    private String name = "Блоховоз";
    private String color = "Цвета уличной грязи";
    private String address = "Не дом и не улица.";
    private int age;
    private int weight;

    public Dog(String name){
        this.name = name;
    }
    public Dog(String name, int weight, int age){
        this.name = name;
        this.weight = weight;
        this.age = age;
    }
    public Dog(String name, int age){
        this.name = name;
        this.age = age;
    }
    public Dog(int weight, String color){
        this.weight = weight;
        this.color = color;
    }
    public Dog(int weight, String color, String address){
        this.weight = weight;
        this.color = color;
        this.address = address;
    }
}
