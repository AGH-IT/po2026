package agh.po.lab1;

public class tree {
    public static void main(String[] args){
        int h = 10;
        for(int i=1; i<=h;i++){
            for(int j=1;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}