package fitness;

import fitness.exception.FitnessException;
import fitness.exception.NoAccessException;
import fitness.exception.QueueException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class FitnessRegistrator {
    private final int CAPACITY = 20;
    Comparator<Human> comparator = new Comparator<Human>() {
        @Override
        public int compare(Human o1, Human o2) {
            return o1.getSurname().compareTo(o2.getSurname());
        }
    }.thenComparing(new Comparator<Human>() {
        @Override
        public int compare(Human o1, Human o2) {
            return o1.getName().compareTo(o2.getName());
        }
    });

    private Map<FitnessServiceEnumeration, Set<Human>> clients = new HashMap<>();
    {
        for(FitnessServiceEnumeration fse: FitnessServiceEnumeration.values()){
            clients.put(fse, new TreeSet<>(comparator));
        }
    }
    private FitnessLogger logger = FitnessLogger.getIinstance();

    public void add(Human human, FitnessServiceEnumeration type) throws FitnessException, IOException {
        LocalDateTime time = LocalDateTime.now();
        if(!isAccessEnable(human, type)) {
            String message = "No access to " + type.toString() + " for " + human;
            logger.write(human, type, time, message);
            throw new NoAccessException(message);
        }
        if(clients.get(type).size() > CAPACITY){
            String message = type.toString() + " не резиновый";
            logger.write(human, type, time, message);
            throw new QueueException(message);
        }
        String message = "OK";
        deleteFromEverywhere(human); //удаляем из других мест
        clients.get(type).add(human);
        logger.write(human, type, time, message);
    }

    public void printClients(){
        for(Human human: clients.get(FitnessServiceEnumeration.GYM)){
            System.out.println(human.getSurname() + " " + human.getName() + " " + human.getClass().getSimpleName() + " GYM" );
        }
        for(Human human: clients.get(FitnessServiceEnumeration.POOL)){
            System.out.println(human.getSurname() + " " + human.getName() + " " + human.getClass().getSimpleName() + " POOL" );
        }
        for(Human human: clients.get(FitnessServiceEnumeration.GROUP)){
            System.out.println(human.getSurname() + " " + human.getName() + " " + human.getClass().getSimpleName() + " GROUP" );
        }
    }
    public void deleteFromEverywhere(Human human) throws IOException {
        for(FitnessServiceEnumeration type: clients.keySet()){
            if(clients.get(type).remove(human)){
                String message = "leave " + type;
                logger.write(human, type, LocalDateTime.now(), message);
            }
        }
    }

    public boolean isAccessEnable(Human human, FitnessServiceEnumeration type){
        Class hClass = human.getClass();
        if(hClass.isAnnotationPresent(AccessMode.class)){
            AccessMode accessMode = (AccessMode) hClass.getDeclaredAnnotation(AccessMode.class);
            if(FitnessServiceEnumeration.GYM.equals(type)){
                if(Integer.parseInt(accessMode.gym()) > LocalDateTime.now().getHour()) return true;
            } else if(FitnessServiceEnumeration.POOL.equals(type)){
                if(Integer.parseInt(accessMode.pool()) > LocalDateTime.now().getHour()) return true;
            } else if(FitnessServiceEnumeration.POOL.equals(type)) {
                if (Integer.parseInt(accessMode.gym()) > LocalDateTime.now().getHour()) return true;
            }
        }
        return false;
    }
}
