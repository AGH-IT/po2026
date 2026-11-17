package agh.po.Animals;

public class Dog extends Animal {
    public Dog(String name) {
        super(name, 4);
    }

    public String getDescription() {
        return "Pies: " + name + ", liczba nóg: " + legs;
    }

    public void makeSound() {
        System.out.println(name + "Mówi: Hau hau!");
    }

}
