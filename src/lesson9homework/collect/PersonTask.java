package lesson9homework.collect;

import java.util.*;

public class PersonTask {
    public static void main(String[] args) {
        List<Person> personList = PersonGenerator.generate(1000);
        Collections.sort(personList, new NameComparator());
        System.out.println(personList);
        Collections.sort(personList, new NameComparator().thenComparing(new SalaryComparator()));
        System.out.println(personList);
        Collections.sort(personList, new NameComparator().thenComparing(new SalaryComparator()).thenComparing(new AgeComparator()).thenComparing(new CompanyComparator()));
        System.out.println(personList);
    }
}

class Person {
    String name;
    int age;
    int salary;
    String company;

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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", company='" + company + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                salary == person.salary &&
                Objects.equals(name, person.name) &&
                Objects.equals(company, person.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary, company);
    }
}
class PersonGenerator{
    public static List<Person> generate(int num){
        Random random = new Random();
        List<Person> list = new ArrayList<>(num);
        int nameCount = Name.values().length;
        int companyCount = Company.values().length;
        int ageMax = 65;
        int ageMin = 18;
        int salaryMax = 200000;
        int salaryMin = 10000;
        for(int i = 0; i < num; i++){
            Person person = new Person();
            String name = Name.values()[random.nextInt(nameCount)].toString();
            String company = Company.values()[random.nextInt(companyCount)].toString();
            int age = random.nextInt(ageMax - ageMin + 1) + ageMin;
            int salary = random.nextInt(salaryMax - salaryMin + 1) + salaryMin;
            person.setName(name);
            person.setCompany(company);
            person.setAge(age);
            person.setSalary(salary);
            list.add(person);
        }
        return list;
    }
}
enum Name{
    Софья, Анастасия, Дарья, Мария, Анна, Виктория, Полина, Елизавета, Екатерина, Ксения,
    Артём, Александр, Максим, Даниил, Дмитрий, Иван, Кирилл, Никита, Михаил, Егор;
    public static Name getName(int ord){
        for (Name name : values()){
            if (ord == name.ordinal()) {
                return name;
            }
        }
        throw new AssertionError("Wrong ordinal: " + ord);
    }
}
enum  Company {
    Газпром, ЛУКойл, Роснефть, Сбербанк, РЖД, X5, Группа, Сургутнефтегаз, Магнит, РоссийскиеСети;
    public static Company getCompany(int ord){
        for (Company company : values()){
            if (ord == company.ordinal()) {
                return company;
            }
        }
        throw new AssertionError("Wrong ordinal: " + ord);
    }
}
class NameComparator implements Comparator<Person>{
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
class SalaryComparator implements Comparator<Person>{

    @Override
    public int compare(Person o1, Person o2) {
        return Integer.compare(o1.getSalary(), o2.getSalary());
    }
}
class AgeComparator implements Comparator<Person>{

    @Override
    public int compare(Person o1, Person o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
class CompanyComparator implements Comparator<Person>{

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getCompany().compareTo(o2.getCompany());
    }
}