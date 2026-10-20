package agh.po.lab2;

import java.util.ArrayList;
import java.util.Random;

public class Lotto3 {public static void main(String[] args){

    Random r = new Random();

    int licznik = 0;
    int trafione = 0;

    ArrayList<Integer> types = new ArrayList<>();
    ArrayList<Integer> numbers = new ArrayList<>();

    long t_start = System.currentTimeMillis();

    for(int j=0; j< args.length;j++){
        int x = Integer.parseInt(args[j]);
        types.add(x);
    }

    while (trafione != 6){

        numbers.clear();
        trafione = 0;

        for (int i=1; i<=6;){
            int a = r.nextInt(49)+1;
            boolean jest = numbers.contains(a);

            if(jest != true){
                numbers.add(a);
                i++;
            }
        }

        for(int z=0; z< types.size();z++){
            if(numbers.contains(types.get(z))){
                trafione++;
            }
        }

        licznik++;



    }

    long t_end = System.currentTimeMillis();

    double diff = (t_end - t_start)/1000.0 ;

    System.out.println("Typy: "+ types);
    System.out.println("Wylosowane liczby :" + numbers);
    System.out.println("Liczba losowan potrzebna do trafienia szostki: " + licznik);
    System.out.println("Czas dzialania programu w sekundach: " + diff);




}
}
