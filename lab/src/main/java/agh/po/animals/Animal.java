package agh.po.animals;

public abstract class Animal {
    protected String name;
    protected int legs;

    public abstract String getDescription();
    public int getLegs(){
        return legs;
    }
}
