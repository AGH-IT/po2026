package agh.Project.Wiktor;

import java.util.ArrayList;
import java.util.Random;

import static agh.Project.Wiktor.Skan.readNumber;

public class Dice {

    static Random random = new Random();

    public static int ask() {
        System.out.print("What dice do you want to roll: d");
        int choice = readNumber();
        return choice;
    }

    public static ArrayList<Integer> createoutcomes(int choice) {
        ArrayList<Integer> outcomes = new ArrayList<>();

        for (int i = 1; i <= choice; i++) {
            outcomes.add(i);
        }

        return outcomes;
    }

    public static int rollNumber(int choice) {
        ArrayList<Integer> dice = createoutcomes(choice);

        int index = random.nextInt(dice.size());

        return dice.get(index);
    }

    public static void main(String[] args) {
        int choice = ask();

        int result = rollNumber(choice);
        System.out.println("Rolled: " + result);

    }
}