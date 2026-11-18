package agh.po.zadania;

import agh.po.animals.Animal;
import agh.po.animals.Dog;
import agh.po.animals.Parrot;
import agh.po.animals.Snake;

import java.util.ArrayList;
import java.util.Random;

public class Zoo {
    public static int countLegs(ArrayList<Animal> list){
        int legCount=0;
        for(Animal animal:list){
            System.out.println(animal.getDescription());
            legCount=legCount+animal.getLegs();
        }
        return legCount;
    }

    public  static void main(String[] args){
        ArrayList<Animal> list = new ArrayList<>();
        Random rand = new Random();
        int animalsCount= rand.nextInt(10)+5;

        for(int i = 0; i<animalsCount; i++){
            int choice= rand.nextInt(3);
                if(choice==0){
                    list.add(new Parrot());
                }else if(choice==1) {
                    list.add(new Dog());
                }
                else{
                    list.add(new Snake());
                }
        }
        System.out.println(countLegs(list));
    }
}
