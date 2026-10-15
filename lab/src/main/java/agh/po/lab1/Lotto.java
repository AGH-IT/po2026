package agh.po.lab1;

import java.util.ArrayList;
import java.util.Random;

public class Lotto {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Random r = new Random();
        int num;

        for(int j = 0; j < 6; j++) {
            num = r.nextInt(1, 49);
            if(arr.contains(num))
                arr.add(r.nextInt(1,49));
            else {
                arr.add(num);
            }
        }

        if(arr.size() == 6)
            System.out.println(arr);
        else
            System.out.println("Invalid lotto numbers");
    }
}
