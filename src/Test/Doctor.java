package Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Doctor extends Role {
    public static final String NAME = "Доктор";
    private List<Command> commands;
    private static Map<String, User> roleMembers = new HashMap<>();
    private DoctorType docType;
    public Doctor(){
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
