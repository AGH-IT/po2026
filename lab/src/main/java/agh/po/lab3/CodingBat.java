package agh.po.lab3;

public class CodingBat {

    public boolean sleepIn(boolean weekday, boolean vacation) {
        if (!weekday || vacation) {
            return true;
        }
        return false;
    }

    public int diff21(int n) {
        if (n <= 21) {
            return 21 - n;
        } else {
            return (n - 21) * 2;
        }
    }

    public int countEvens(int[] nums) {
        int liczba = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                liczba++;
            }
        }
        return liczba;
    }

    public String helloName(String name) {
        return "Hello " + name + "!";
    }
}
