package agh.po.animals;

public class Parrot extends Animal{
    public Parrot(String name){
        this.name = name;
        this.legs = 2;
    }
    @Override
    String getDescription() {
        return name;
    }
}
