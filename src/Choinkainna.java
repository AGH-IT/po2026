public class Choinkainna{

    public static void main(String[] args) {

        int h = Integer.parseInt(args[0]);
        for(int i=1; i<=h; ++i){
            for(int j=1; j<=i; ++j){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}