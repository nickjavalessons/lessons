package lesson9homework;

public class Planets {
    public static void main(String[] args) {
        for (PlanetsOfSolarSystem planet: PlanetsOfSolarSystem.values()){
            System.out.println(planet);
        }

    }
}

enum PlanetsOfSolarSystem{
    MERCURY("Меркурий", 0.32868E24, 2.439E6, 58E10), VENUS("Венера", 4.81068E24,6.052E6, 108E10 ),
    EARTH("Земля",5.976E24,6.378E6, 150E10),  MARS("Марс",0.63345E24,3.488E6, 228E10),
    JUPITER("Юпитер",1876.64328E24,71.300E6, 778E10), SATURN("Сатурн",561.80376E24, 60.100E6, 1429E10),
    URANUS("Уран",86.05440E24, 26.500E6,	2871E10), NEPTUNE("Нептун",101.592E24, 24.750E6, 4504E10);
    private String name;
    private double weight;
    private double pRadius;
    private double oRaduis;
    PlanetsOfSolarSystem(String name, double weight, double pRadius, double oRadius){
        this.name = name;
        this.weight = weight;
        this.pRadius = pRadius;
        this.oRaduis = oRadius;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getpRadius() {
        return pRadius;
    }

    public double getoRaduis() {
        return oRaduis;
    }

    @Override
    public String toString() {
        return "PlanetsOfSolarSystem{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", pRadius=" + pRadius +
                ", oRaduis=" + oRaduis +
                '}';
    }
}
