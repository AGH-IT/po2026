package agh.Project.Wiktor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    static Random random = new Random();
    static List<Integer> list = new ArrayList<>();
    public List<Integer> getlist() {
        Dicepack.getDices();
        for (int x=1; x<=Dicepack.dices.size();x++){
            int index = random.nextInt(Dicepack.dices.size());
            list.add(Dice.rollNumber(Dicepack.dices.get(index)));
            //System.out.println(Dice.rollNumber(Dicepack.dices.get(index)));
        }
        list.sort(null);
        return list;
    }
    public static void main(String[] args) {
        Dicepack.getDices();
        for (int x=1; x<=Dicepack.dices.size();x++){
            int index = random.nextInt(Dicepack.dices.size());
            list.add(Dice.rollNumber(Dicepack.dices.get(index)));
            //System.out.println(Dice.rollNumber(Dicepack.dices.get(index)));
        }
        list.sort(null);
        System.out.print("your rolls:");
        System.out.println(list);
    }
}