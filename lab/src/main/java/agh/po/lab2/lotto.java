import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class lotto {
    public static void main(String[] args) {

        ArrayList<Integer> lista = new ArrayList<>();
        Random r=new Random();

        int wylosowanaLiczba;
        for (int i=0; i<6; i++) {
            wylosowanaLiczba=r.nextInt(49)+1;
            while(lista.contains(wylosowanaLiczba)){
                wylosowanaLiczba=r.nextInt(49)+1;
            }
            lista.add(wylosowanaLiczba);
        }

        System.out.print("wylosowane liczby to ");
        System.out.println(lista);

        ArrayList<Integer> liczbygracza = new ArrayList<>();
        Scanner wczytaj = new Scanner(System.in);
        int liczbagracza;
        System.out.println("podaj 6 liczb od 1 do 49");
        for (int i=0; i<6; i++){
            liczbagracza=wczytaj.nextInt();
            while(liczbygracza.contains(liczbagracza) || liczbagracza <1 || liczbagracza>49){
                System.out.println("podaj odpowiednia i nowa liczbe");
                liczbagracza=wczytaj.nextInt();
            }
            liczbygracza.add(liczbagracza);
        }
        int liczbatrafien=0;
        for (int i=0; i<6; i++){
            if (lista.contains(liczbygracza.get(i))){
                liczbatrafien=liczbatrafien+1;
            }
        }
        System.out.print("liczba trafionych liczb: ");
        System.out.print(liczbatrafien);
    }
}