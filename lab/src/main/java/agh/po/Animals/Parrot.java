package agh.po.Animals;

public class Parrot extends Animal{
    public Parrot(String name) {
        super(name, 2)
    }

    public String getDescription() {
        return "Papuga: " + name + ", liczba nóg: " + legs;
    }
}
