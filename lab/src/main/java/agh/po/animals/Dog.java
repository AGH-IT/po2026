package agh.po.animals;

public class Dog extends Animal{
    public Dog(String name){
        this.name = name;
        this.legs = 4;
    }
    @Override
    String getDescription() {
        return name;
    }
}
