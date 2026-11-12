package agh.po.zadania;

import agh.po.animals.*;
import java.util.Random;

public class Zoo {
    private Animal[] animals = new Animal[100];

    public void fillAnimalR() {
        Random random = new Random();

        for (int i = 0; i < animals.length; i++) {
            int choice = random.nextInt(3);

            switch (choice) {
                case 0:
                    animals[i] = new Dog("Dog_" + i);
                    break;
                case 1:
                    animals[i] = new Parrot("Parrot_" + i);
                    break;
                case 2:
                    animals[i] = new Snake("Snake_" + i);
                    break;
            }
        }
    }

    public int countLegs() {
        int totalLegs = 0;
        for (Animal animal : animals) {
            if (animal != null) {
                totalLegs += animal.getLegs();
            }
        }
        return totalLegs;
    }

    public static void main(String[] args) {
        Zoo myZoo = new Zoo();
        myZoo.fillAnimalR();

        System.out.println("Total number of legs: " + myZoo.countLegs());
    }
}

