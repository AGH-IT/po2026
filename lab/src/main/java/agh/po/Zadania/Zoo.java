package agh.po.Zadania;
import agh.po.Animals.Animal;
import agh.po.Animals.Dog;
import agh.po.Animals.Parrot;
import agh.po.Animals.Snake;

import java.util.Random;



public class Zoo {
    private Animal[] animals = new Animal[100];
    private int count = 0;

    public void addAnimal(Animal animal){
        if (count < animals.length) {
            animals[count] = animal;
            count++;
        }else {
            System.out.println("Zoo jest pełne");
        }
    }
    public void fillZoo() {
        Random random = new Random();
        String[] names = {"Koko","Mia","Jackie","Melusia","Azor","Dolar","Filemon"};
        for (int i = 0; i < animals.length; i++) {
            int animalType = random.nextInt(3);
            String name = names[random.nextInt(names.length)] + i;

            Animal randomAnimal;
            if(animalType==0) {
                randomAnimal = new Dog(name);
            } else if (animalType == 1) {
                randomAnimal = new Parrot(name);
            } else {
                randomAnimal = new Snake(name);
            }
            animals[i] = randomAnimal;
        }
        count = animals.length;
    }

    public int allLegs() {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += animals[i].getLegs();
        }
        return sum;
    }
    public void showAnimal() {
        for (int i = 0; i < count; i++) {
            Animal animal = animals[i];
            int legs = animal.getLegs();
            System.out.println(animals[i].getDescription());
        }
    }
    public void makeAllSounds() {
        for(int i = 0; i < count; i++) {
            animals[i].makeSound();;
        }
    }
    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        zoo.fillZoo();
        zoo.showAnimal();
        System.out.println("Suma nóg: " + zoo.allLegs());
        zoo.makeAllSounds();
    }

}
