package agh.po.lab1;
import agh.Project.Wiktor.Skan;
public class Choinka2 {
    public static void main(String[] args){
        int rozmiar = Skan.readNumber();
        for(int i=0; i<rozmiar; i++){
            for (int a=rozmiar-i/2;a!=0;a--){
                System.out.print(" ");
            }
            for (int x=i; x!=0; x--) {

                System.out.print("*");

            }
            System.out.println("");
        }
    }
}
/* Działa prawie
  public class Choinka {
    public static void main(String[] args){;
        int rozmiar = Integer.parseInt(args[0]);
        for(int i=0; i<rozmiar; i++){
            for (int x=i; x!=0; x--) {
                for (int a=10-i/2;a!=0;a--){
                    System.out.print(" ");
                }
                System.out.print("*");
                ;
            }
            System.out.println("");
        }
    }
}
 */
/*public class Choinka {
    public static void main(String[] args){;
        for(int i=0; i<10; i++){
            for (int x=i; x!=0; x--) {
                if (x%2==0){
                    System.out.print("");
                }
                System.out.print("*");
                ;
            }
            System.out.println("");
        }
    }
}
*/



