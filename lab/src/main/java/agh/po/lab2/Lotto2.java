package agh.po.lab2;

import java.util.ArrayList;
import java.util.Random;

public class Lotto2 {
    public static void main(String[] args) {

        Random r = new Random();

        ArrayList<Integer> numbers = new ArrayList<>();


        for (int i=1; i<=6;){
            int a = r.nextInt(49)+1;
            boolean jest = numbers.contains(a);

            if(jest != true){
                numbers.add(a);
                i++;
            }
        }

        ArrayList<Integer> types = new ArrayList<>();

        for(int j=0; j< args.length;j++){
            int x = Integer.parseInt(args[j]);
            types.add(x);
        }


        int trafione = 0;
        for(int z=0; z< types.size();z++){
            if(numbers.contains(types.get(z))){
                trafione++;
            }
        }

        System.out.println("Twoje typy:" + types);
        System.out.println("Wylosowane liczby:"+ numbers);
        System.out.println("Liczba trafien: " + trafione);



        }

    }
