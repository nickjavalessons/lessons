package lesson9homework.enums;

import java.util.*;

public class MessageTask {
    private static List<Message> messages;
    private static int messageCount = 30;

    public static void main(String[] args) {
        messages = MessageGenerator.generate(messageCount);
        countEachPriority(messages);
        countEachCode(messages);
    }

    public static void countEachPriority(List<Message> messageList) {
        // Подсчитать количество сообщений для каждого приоритела
        // Ответ в консоль
        int[] priorityCount = new int[MessagePriority.values().length];
        for(Message m: messages){
            priorityCount[m.getPriority().ordinal()] ++;
        }
        for (int i = 0; i < priorityCount.length; i++) {
            System.out.println(MessagePriority.getPriority(i) + ": " + priorityCount[i]);
        }
    }

    public static void countEachCode(List<Message> messageList) {
        // Подсчитать количество сообщений для каждого кода сообщения
        // Ответ в консоль
        int[] codeCount = new int[10];
        for (Message m: messages){
            codeCount[m.getCode()]++;
        }
        for (int i = 0; i < codeCount.length; i++) {
            System.out.println(i + ": " + codeCount[i]);
        }
    }

    private static void uniqueMessageCount(List<Message> messageList) {
        // Подсчитать количество уникальных сообщений
        // Ответ в консоль
        Set<Message> messageSet = new HashSet<>();
        for(Message m: messageList){
            messageSet.add(m);
        }
        System.out.println(messageSet.size());
    }

    public static List<Message> uniqueMessagesInOriginalOrder(List<Message> messageList){
        // вернуть только неповторяющиеся сообщения и в том порядке, в котором они
        // встретились в первоначальном списке
        // Например, было
//        [{URGENT, 4}, {HIGH, 9}, {LOW, 3}, {HIGH, 9}]
        // на выходе:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}]
        Set<Message> uniquieSet = new TreeSet<>();
        for(Message m: messageList) uniquieSet.add(m);
        List<Message> list = new ArrayList<>(uniquieSet);
        return list;
    }

    public static void removeEach(List<Message> messageList, MessagePriority priority){
        // удалить из коллекции каждое сообщение с заданным приоритетом
        // вывод до удалеия и после удаления
        System.out.println(messageList);
        for (int i = 0; i < messageList.size(); i++  ) {
            if(messageList.get(i).getPriority() == priority) messageList.remove(i);
        }
        System.out.println(messageList);
    }

    public static void removeOther(List<Message> messageList, MessagePriority priority){
        // удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет
        // вывод до удалеия и после удаления
        System.out.println(messageList);
        for (int i = 0; i < messageList.size(); i++  ) {
            if(messageList.get(i).getPriority() != priority) messageList.remove(i);
        }
        System.out.println(messageList);
    }


}