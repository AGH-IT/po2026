package agh.po.lab2;

import java.util.ArrayList;
import java.util.Random;

public class Zad3 {
    public static void main(String[] args){
        if(args.length!=6){
            System.out.println("Podaj 6 argumentów!!!");
        }else{
            try{
                Random rand = new Random();
                ArrayList<Integer> numbers = new ArrayList<Integer>();
                ArrayList<Integer> user_numbers = new ArrayList<Integer>();
                int liczba_trafien=0;
                int liczba_losowań=0;
                long start_time=0;
                long end_time=0;

                for(int i=0;i<args.length;i++){
                    int val = Integer.parseInt(args[i]);
                    if(val<1 || val>49){
                        System.out.println("Liczby musza byc z przedialu 1-49");
                        return;
                    }
                    if(user_numbers.contains(val)){
                        System.out.println("Liczby nie mogą się powtarzać");
                        return;
                    }
                    user_numbers.add(val);
                }
                start_time = System.currentTimeMillis();
                while (liczba_trafien<6){
                    liczba_losowań++;
                    liczba_trafien=0;
                    numbers.clear();

                    while (numbers.size()<6) {
                        int new_int = rand.nextInt(49)+1;
                        if (!numbers.contains(new_int)) {
                            numbers.add(new_int);
                        }
                    }

                    for(int x=0;x<numbers.size();x++){
                        if(numbers.contains(user_numbers.get(x))){
                            liczba_trafien++;
                        }
                    }
                }
                end_time=System.currentTimeMillis();
                double time =end_time-start_time;

                System.out.println("Liczby użytkownika: "+ user_numbers);
                System.out.println("Liczby komputera: "+numbers);
                System.out.println("Ilość prób: "+ liczba_losowań);
                System.out.println("Czas: " + time);

            }catch(Exception e){
                System.out.println("Podaj liczby całkowite!!");
            }
        }
    }
}