package Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class Role {

    public static Role  parseRole(String role){
        if("hd".equals(role)){ ;
            return new HeadDoctor();

        } else if("doc".equals(role)) {
            return new Doctor();

        } else if("patient".equals(role)){
            return new Patient();

        } else if("admin".equals(role)){
            return new AdminRole();
        }
        return null;
    }
    public  void operateWithRole(Scanner sc){
        System.out.println("Роль " + getName());
        String currentCommandStr = "";
        int currentCommandI;
        Command currentCommand;
        while(!"exit".equals(currentCommandStr)){
            System.out.println("Введите команду: " + System.lineSeparator());
            for (int i = 0; i < getCommands().size(); i++) {
                System.out.println("[" + i + "] " + getCommands().get(i).getName());
            }
            currentCommandStr = sc.next();

            try{
                currentCommandI = Integer.parseInt(currentCommandStr);
                currentCommand = getCommands().get(currentCommandI);
                currentCommand.run(sc);

            } catch(NumberFormatException e){

            }
        }

    }
    public abstract Map<String, User> getRoleMembers();
    public abstract List<Command> getCommands();
    public abstract String getName();
}
