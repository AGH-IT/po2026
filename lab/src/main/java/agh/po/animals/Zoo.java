package agh.po.animals;

import java.util.*;

public class Zoo {
    ArrayList<Animal> animals = new ArrayList<>(100);
    public void losowanie(){
        Random random = new Random();
        for(int i = 0; i < animals.size(); i++){
            int wylosowane = random.nextInt(3);
            switch (wylosowane){
                case 0:
                    animals.add(new Parrot("Jacek" + i));
                    break;
                case 1:
                    animals.add(new Dog("Piotrek" + i));
                    break;
                case 2:
                    animals.add(new Snake("Slawek" + i));
                    break;
            }
        }
    }
    public int liczenie_stop(){
        int legi = 0;
        for(Animal a : animals){
            legi += a.getLegs();
        }
        return legi;
    }
    public static void main(String[] args) {
        Zoo zuu = new Zoo();
        zuu.losowanie();
        System.out.print("Suma nog " + zuu.liczenie_stop());


    }
}
