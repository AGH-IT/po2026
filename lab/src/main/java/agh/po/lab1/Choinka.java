public class Choinka {
    public static void main(String[] args) {
        int wysokosc = Integer.parseInt(args[0]);

        for (int i = 1; i <= wysokosc; i++) {
            for (int j = 1; j <= wysokosc - i; j++) {
                System.out.print(" ");
            }

            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

        for (int i = 1; i <= wysokosc - 1; i++) {
            System.out.print(" ");
        }
    }
}
