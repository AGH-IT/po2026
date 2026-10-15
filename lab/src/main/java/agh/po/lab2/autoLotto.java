package agh.po.lab2;

import java.util.ArrayList;
import java.util.Random;

public class autoLotto {
    public static void main(String[] args) {
        // Tworzymy dwie tablice: jedna do przechowywania wylosowanych liczb, druga do przechowywania typów
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> correct = new ArrayList<>();
        // Inicjalizujemy generator liczb losowych
        Random r = new Random();
        int num;
        boolean match = false;
        long counter = 0;

        // Pętla do losowania 6 unikalnych liczb
        for(int j = 0; j < 6; j++) {
            num = r.nextInt(1, 50);
            if(!arr.contains(num))
                arr.add(num);
        }

        // Pętla do losowania 6 typów oraz sprawdzanie trafień
        long startTime = System.currentTimeMillis();
        while(!match) {
            counter++;
            correct.clear();
            while (correct.size() < 6) {
                num = r.nextInt(1, 50);
                if (!correct.contains(num)) {
                    correct.add(num);
                }
            }

            if(correct.containsAll(arr)) {
                match = true;
            }
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;

        System.out.println("Znaleziono wszystko.");
        System.out.println("Liczba prób: " + counter);
        System.out.println("Czas wykonania: " + time + " ms");
        System.out.println("Typy programu: " + correct);
        System.out.println("Wylosowane liczby: " +arr);

    }
}


