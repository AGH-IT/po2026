package agh.po.lab2;
import java.util.ArrayList;
import java.util.Random;


public class Lotto {
    public static void main(String[] args){
        if(args.length!=6){
            System.out.println("Podaj 6 argumentów!!!");
        }else{
            try{
                Random rand = new Random();
                ArrayList<Integer> numbers = new ArrayList<Integer>();
                ArrayList<Integer> user_numbers = new ArrayList<Integer>();
                int liczba_trafien=0;

                for(int i=0;i<args.length;i++){
                    user_numbers.add(Integer.parseInt(args[i]));
                }
                while (numbers.size()<6) {
                    int new_int = rand.nextInt(50);
                    if (!numbers.contains(new_int)) {
                        numbers.add(new_int);
                    }
                }
                for(int x=0;x<numbers.size();x++){
                    if(numbers.contains(user_numbers.get(x))){
                        liczba_trafien++;
                    }
                }
                System.out.println("Liczby użytkownika");
                System.out.println(user_numbers);
                System.out.println("Liczby komputera");
                System.out.println(numbers);
                System.out.println("Liczba trafień: "+ liczba_trafien);
            }catch(Exception e){
                System.out.println("Podaj liczby całkowite!!!");
            }
        }
    }
}
