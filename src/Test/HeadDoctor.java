package Test;

import java.util.*;

public class HeadDoctor extends Role {
    public static final String NAME = "Главный доктор";
    private List<Command> commands;
    private static Map<String, User> roleMembers = new HashMap<>();

    public HeadDoctor(){
        commands = new ArrayList<>();
    }

    @Override
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
