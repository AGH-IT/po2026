package agh.po.lab2;
import java.util.Random;
import java.util.ArrayList;

public class Zad1 {
    public static void main(String[] args){
           Random rand = new Random();
           ArrayList<Integer> numbers = new ArrayList<Integer>();
           while (numbers.size()<6){
               int new_int= rand.nextInt(49)+1;
               if(!numbers.contains(new_int)){
                   numbers.add(new_int);
               }
           }
           System.out.println(numbers);
    }
}
