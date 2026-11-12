package agh.po.animals;

public class Snake extends Animal{
    public Snake(){
        this.name="Snake";
        this.legs=0;
    }

    @Override
    public String getDescription() {
        return "Wąż ma "+ legs +" nóg";
    }
}
