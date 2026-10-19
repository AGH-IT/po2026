package agh.Project.Wiktor;

import java.util.ArrayList;
import java.util.List;

public class Dicepack {
    public static List<Integer> dices = new ArrayList<>();
    public static List<Integer> dicesstiff = new ArrayList<>();
    public static List<Integer> getDices() {
        System.out.print("How many dices do you want to roll?:");
        int ile = Skan.readNumber();
        for (int i = 1; i <=ile ; i++) {
            dices.add(Dice.ask());
        }
        return dices;
    }
    public static List<Integer> getDicesstiff(int ile,int jakie) {
        for (int i = 1; i <=ile ; i++) {
            dicesstiff.add(jakie);
        }
        return dicesstiff;
    }
    public static void main(String[] args) {
        System.out.println("Welcome to the Dicepack!");
        System.out.print("How many dices do you want to roll?:");
        int ile = Skan.readNumber();
        for (int i = 1; i <=ile ; i++) {
            dices.add(Dice.ask());
        }
        System.out.println("You have " + ile + " dice rolled.");
        System.out.println("which are:");
        System.out.println(dices);
    }
}