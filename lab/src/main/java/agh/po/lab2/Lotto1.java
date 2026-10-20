package agh.po.lab2;

import java.util.ArrayList;
import java.util.Random;

public class Lotto1 {public static void main(String[] args){

    Random r = new Random();

    ArrayList<Integer> numbers = new ArrayList<>();

    for (int i=1; i<=6;){
        int a = r.nextInt(49)+1;
        boolean jest = numbers.contains(a);

        if(jest != true){
            //numbers.aaremove(a);
            //a = r.nextInt(50);
            numbers.add(a);
            i++;
        }
        //numbers.add(a);
        }

    System.out.println(numbers);


}
}
