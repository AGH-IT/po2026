package agh.po.Animals;

public abstract class Animal {
    protected String name;
    protected int legs;

    public Animal(String name, int legs){
        this.name = name;
        this.legs = legs;
    }

    public abstract class String getDescription();

    public String getName() {
        return name;
    }
    public int getLegs() {
        return legs;
    }
}
