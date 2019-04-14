package fitness;

import java.time.LocalDate;

public abstract class Human {
    protected String name;
    private String surname;
    private int bYear;
    private LocalDate regDate;

    public Human(String name, String surname, int bYear, LocalDate regDate) {
        this.name = name;
        this.surname = surname;
        this.bYear = bYear;
        this.regDate = regDate;
    }

    public Human(String name, String surname, int bYear) {
        this.name = name;
        this.surname = surname;
        this.bYear = bYear;
        this.regDate = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getbYear() {
        return bYear;
    }

    public void setbYear(int bYear) {
        this.bYear = bYear;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
