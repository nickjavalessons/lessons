package Test;

import java.util.Scanner;

public class UserRegistrator implements Command {
    public final String NAME = "Регистрация пользователя в системе";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void run(Scanner sc) {
        String login;
        String pass;
        Role role;
        System.out.println("Введите новый логин");
        login = sc.next();
        System.out.println("Введите новый пароль");
        pass = sc.next();
        System.out.println("Введите роль");
        role = Role.parseRole(sc.next());
        System.out.println(role.getClass());
        User user = new User();
        user.setLogin(login);
        user.setPass(pass);
        user.setRole(role);
        role.getRoleMembers().put(login, user);
        Hospital.getUsersOfSystem().put(login, user);
    }

}
