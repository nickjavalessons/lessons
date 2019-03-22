package Test;

import java.util.*;

public class AdminRole extends Role {
    public static final String NAME = "Администратор";
    private List<Command> commands;
    private static Map<String, User> roleMembers = new HashMap<>();
    public AdminRole(){
       commands = new ArrayList<>();
       commands.add(new UserRegistrator());
       commands.add(new PatientRegistrator());
    }

    public Map<String, User> getRoleMembers() {
        return roleMembers;
    }

    @Override
    public List<Command> getCommands() {
        return commands;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
