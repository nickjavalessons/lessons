package lesson4;

public class Cat {
    private static final int PENSION_AGE = 8;
    private String name;
    private int age;
    private int weight;
    private int power;

    public Cat(String name, int age, int weight, int power) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getTotalPower(){
        int totalPower = power + ((age > PENSION_AGE)? PENSION_AGE-age : age) + weight;
        return totalPower;
    }

    public boolean fight(Cat cat){
        if(getTotalPower() > cat.getTotalPower()) return true;
        return false;
    }
}
