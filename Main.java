class Choinka {
    static void main(String[] args) {
        int rozmiar = Integer.parseInt(args[0]);
        for(int i = 1; i <= rozmiar; i++) {
            for(int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }
}
