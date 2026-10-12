package agh.po.lab2;
import java.util.ArrayList;
public class Lotto2 {
    public static void main(String[] args)
    {
        int[] guess = new int[6];
        int[] lottery = new int[6];
        int randomNum;

        for (int i = 0; i < 6; i++)
        {
            randomNum = (int) (Math.random() * 50); //Random number created here.
            for (int x = 0; x < i; x++)
            {
                if (lottery[i] == randomNum) // Here, code checks if same random number generated before.
                {
                    randomNum = (int) (Math.random() * 50);//If random number is same, another number generated.
                }

            }
            lottery[i] = randomNum;
        }
        for (int a = 0; a < 6; a++)
        {
           int number =

        }

        for (int i = 0; i < lottery.length; i++)
            System.out.print(lottery[i] + " ");

    }

}
