package agh.po.animals;

public class Parrot extends Animal{
    public Parrot(){
        this.name="Parrot";
        this.legs=2;
    }


    @Override
    public String getDescription() {
        return "Papuga ma "+legs+" nogi";
    }
}
