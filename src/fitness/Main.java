package fitness;

public class Main {
    public static void main(String[] args) {
        Human c1 = new Client("1", "1", 1900);
        Human c2 = new Client("2", "2", 1900);
        Human c3 = new DayClient("3", "3", 1900);
        Human c4 = new FullDayClient("4", "4", 1901);
        FitnessRegistrator fr = new FitnessRegistrator();
        try {
            fr.add(c1, FitnessServiceEnumeration.GYM);
            fr.add(c2, FitnessServiceEnumeration.GYM);
            fr.add(c3, FitnessServiceEnumeration.GYM);
            fr.add(c4, FitnessServiceEnumeration.GYM);
            fr.printClients();
            fr.add(c1, FitnessServiceEnumeration.POOL);
            fr.add(c2, FitnessServiceEnumeration.POOL);
            fr.add(c3, FitnessServiceEnumeration.POOL);
            fr.add(c4, FitnessServiceEnumeration.POOL);
            fr.printClients();
            fr.add(c1, FitnessServiceEnumeration.GROUP);
            fr.add(c2, FitnessServiceEnumeration.GROUP);
            fr.add(c3, FitnessServiceEnumeration.GROUP);
            fr.add(c4, FitnessServiceEnumeration.GROUP);

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
