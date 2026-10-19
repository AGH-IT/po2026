package agh.Project.Wiktor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rollset {
    static Random random = new Random();
    static List<Integer> list = new ArrayList<>();
    static List<Integer> liststiff = new ArrayList<>();
    public List<Integer> getlistmod() {
        Dicepack.getDices();
        for (int x=1; x<=Dicepack.dices.size();x++){
            int index = random.nextInt(Dicepack.dices.size());
            list.add(Dice.rollNumber(Dicepack.dices.get(index)));
            //System.out.println(Dice.rollNumber(Dicepack.dices.get(index)));
        }
        list.sort(null);
        return list;
    }
    public static List<Integer> getliststiff(int input,int input2)   {
        Dicepack.getDicesstiff(input,input2);
        for (int x=1; x<=Dicepack.dicesstiff.size();x++){
            int index = random.nextInt(Dicepack.dicesstiff.size());
            liststiff.add(Dice.rollNumber(Dicepack.dicesstiff.get(index)));
            //System.out.println(Dice.rollNumber(Dicepack.dices.get(index)));
        }
        liststiff.sort(null);
        return liststiff;
    }
    public static void main(String[] args) {
        Dicepack.getDices();
        for (int x=1; x<=Dicepack.dices.size();x++){
            int index = random.nextInt(Dicepack.dices.size());
            list.add(Dice.rollNumber(Dicepack.dices.get(index)));
            //System.out.println(Dice.rollNumber(Dicepack.dices.get(index)));
        }
        list.sort(null);
        System.out.print("your dices:");
        System.out.println(list);
    }
}