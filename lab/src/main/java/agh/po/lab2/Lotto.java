package agh.po.lab2;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lotto {
    public static void main(String[] args) {
        ArrayList<Integer> lista = new ArrayList<>();
        Random generator = new Random();

        for(int i=0; lista.size()<=5; i++) {
            int l = generator.nextInt(49);
            boolean jest = lista.contains(l);
            if (jest == false) {
                lista.add(l);
            }
        }
        System.out.println(lista);

        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj swoje liczby: ");

        for (int i = 0; i < 6; i++) {

        }


    }
}