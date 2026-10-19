package agh.po.lab2;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lotto {
    public static void main(String[] args) {
        ArrayList<Integer> lista = new ArrayList<>();
        Random generator = new Random();

        for (int i = 0; lista.size() <= 5; i++) {
            int l = generator.nextInt(49);
            boolean jest = lista.contains(l);
            if (jest == false) {
                lista.add(l);
            }
        }
        System.out.println(lista);

        ArrayList<Integer> strzaly = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            strzaly.add(Integer.parseInt((args[i])));
        }
        System.out.println(strzaly);

        int trafy = 0;
        for (int i = 0; i < 6; i++) {
            if (lista.contains(strzaly.get(i)) == true) {
                trafy++;
            }
        }
        System.out.println("Liczba trafień: " + trafy);

        ArrayList<Integer> lista1 = new ArrayList<>();
        while (trafy != 6) {
            lista1.clear();
            Random generator1 = new Random();
            for (int i = 0; lista1.size() <= 5; i++) {
                int l = generator1.nextInt(49);
                boolean jest = lista1.contains(l);
                if (jest == false) {
                    lista1.add(l);
                }
            }
            trafy = 0;
            for (int i = 0; i < 6; i++) {
                if (lista1.contains(strzaly.get(i)) == true) {
                    trafy++;
                    if (trafy == 6) {
                        System.out.println("Gratulacje! Trafiłeś 6/6");
                        break;
                    }
                }
            }
            System.out.println(lista1);
        }
    }
}