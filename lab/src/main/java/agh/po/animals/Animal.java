package agh.po.animals;

public abstract class Animal {
    String name;
    int legs;
    abstract String getDescription();
    public int getLegs(){
        return legs;
    }
}
