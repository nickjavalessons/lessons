package lesson9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class CollectionExample {
    public static void main(String[] args) throws IOException {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        bookArrayList.add(new Book("Book"));
        bookArrayList.add(new Book("Book"));
        bookArrayList.add(new Book("Cars"));
        bookArrayList.add(2,new Book("Alphabet"));
        for(Book book: bookArrayList){
            System.out.println(book);
        }
        Set<Book> bookSet = new HashSet<>(bookArrayList);
        for(Book book: bookSet){
            System.out.println(book);
        }
        LinkedList<Book> bookLinkedList = new LinkedList<>();
        bookLinkedList.add(new Book("Book 1"));
        bookLinkedList.add(new Book("Book 2"));
        bookLinkedList.add(new Book("Book 3"));
        for(Book book: bookLinkedList){

        }
        TreeSet<String> strings = new TreeSet<>();
        strings.add("Hello");
        strings.add("Hi");
        strings.add("Asd");
        for(String str: strings){
            System.out.println(str);
        }

        TreeSet<Book> bookTreeSet = new TreeSet<>();
        bookTreeSet.add(new Book("Book 1"));
        bookTreeSet.add(new Book("Book 2"));

        User user1 = new User("qwe", 23);
        User user2 = new User("asd", 23);
        TreeSet<User> users = new TreeSet<>(new UserComparator());
        users.add(user1);
        users.add(user2);


        ClassLoader loader = CollectionExample.class.getClassLoader();
        File file = new File(loader.getResource("text.txt").getFile());
        List<String> lines = Files.readAllLines(file.toPath());
        List<String> words = new ArrayList<>();

        for(String line: lines){
            String[] worldSplit = line.toLowerCase()
                    .replaceAll("\\p{Punct}", " ")
                    .trim()
                    .split("\\s");
            for(String s:worldSplit){
                if(s.length() > 0){
                    words.add(s.trim());
                }
            }
        }
        for(String s: words){
            System.out.println(s);
        }
    }
}

class Book implements Comparable<Book>{
    String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        return getTitle() != null ? getTitle().equals(book.getTitle()) : book.getTitle() == null;
    }

    @Override
    public int hashCode() {
        return getTitle() != null ? getTitle().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public int compareTo(Book b) {
        return title.compareTo(b.getTitle());
    }
}

class User{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getAge() != user.getAge()) return false;
        return getName() != null ? getName().equals(user.getName()) : user.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getAge();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class UserComparator implements Comparator<User>{

    @Override
    public int compare(User o1, User o2) {
        return o1.getName().compareTo(o2.getName());
    }
}