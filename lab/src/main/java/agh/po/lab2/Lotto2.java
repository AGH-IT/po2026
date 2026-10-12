package agh.po.lab2;

import java.util.ArrayList;
import java.util.Random;

public class Lotto2 {
    public static void main(String[] args) {
        ArrayList<Integer> guess = new ArrayList<>();
        ArrayList<Integer> lottery = new ArrayList<>();
        Random r = new Random();
        for (int a = 0; a < 6; a++) {
            guess.add(Integer.parseInt((args[a])));
        }
        for (int i = 0; i < 6; i++) {
            int randomNum = r.nextInt(50); //Random number created here
            if (lottery.contains(randomNum))// Here, code checks if same random number generated before.
            {
                randomNum = r.nextInt(50);//If random number is same, another number generated.
            }
            lottery.add(randomNum);
        }

        lottery.sort(null);
        guess.sort(null);
        System.out.println(lottery);
        System.out.println(guess);
        lottery.retainAll(guess);
        //System.out.println(lottery);
        System.out.println("trafiono:"+ lottery.size());

    }
}
