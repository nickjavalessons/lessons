package Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hospital {
    static Map<String, User> usersOfSystem;
    static User currentUser;
    public static void main(String[] args) {
        Hospital h = new Hospital("admin", "admin");
        h.open();

    }

    public static Map<String, User> getUsersOfSystem() {
        return usersOfSystem;
    }

    public static User getCurrentUser() {
        return currentUser;
    }


    public Hospital(String adminLog, String adminPass){
        usersOfSystem = new HashMap<>();
        User admin = new User();
        admin.setRole(new AdminRole());
        new AdminRole().getRoleMembers().put(adminLog, admin);
        admin.setLogin(adminLog);
        admin.setPass(adminPass);
        usersOfSystem.put(adminLog, admin);
    }
    private void open(){
        Scanner sc = new Scanner(System.in);
        String login;
        String pass;
        while(true){
            System.out.println("Введите логин");
            login = sc.next();
            System.out.println("Введите пароль");
            pass = sc.next();
            if(usersOfSystem.containsKey(login) && pass.equals(usersOfSystem.get(login).getPass())){
                System.out.println("Авторизация успешна");
                currentUser = usersOfSystem.get(login);
                currentUser.getRole().operateWithRole(sc);

            } else {
                System.out.println("Неправильная пара логин/пароль");
            }
        }
    }
}
