package agh.po.lab2;
import java.util.ArrayList;
import java.util.Random;


public class Lotto {
    public static void main(String[] args){
        Random rand = new Random();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        while (numbers.size()<6){
            int new_int = rand.nextInt(50);
            if(!numbers.contains(new_int)){
                numbers.add(new_int);
            }
        }
    }
}
