package Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Patient extends Role {

    public static final String NAME = "Пациент";
    private List<Command> commands;
    private static Map<String, User> roleMembers = new HashMap<>();

    public Patient(){
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
        return null;
    }
}
