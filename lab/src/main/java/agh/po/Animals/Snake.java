package agh.po.Animals;

public class Snake extends Animal{
    public Snake(String name) {
        super(name, 0)
    }

    public String getDescription() {
        return "Wąż: " + name + ", liczba nóg: " + legs;
    }
}
