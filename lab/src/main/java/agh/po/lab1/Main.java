package agh.po.lab1;

public class Main{
    public static void main(String[] args){
        System.out.println("Hello, World! ");

        if(args.length <= 0){
            System.out.println("Podaj argument");
        }
        else{
            try{
                int n=Integer.parseInt(args[0]);
                for (int i=0;i<n+1;i++){
                    for (int ii=1;ii<i+1;ii++){
                        System.out.print("*");
                    }
                    System.out.print("\n");
                }
            }
            catch(Exception e){
                System.out.println("Podaj liczbe calkowita");
            }
        }

    }
}