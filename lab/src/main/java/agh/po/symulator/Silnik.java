package agh.po.symulator;

public class Silnik extends Komponent {
    private int maxObroty;
    private int obroty;

    public Silnik(String nazwa, double waga, double cena, int maxObroty) {
        super(nazwa, waga, cena);
        this.maxObroty = maxObroty;
        this.obroty = 0;
    }

    public void uruchom() {
        obroty = 800; //bieg jałowy
        System.out.println("silnik uruchomiony.");
    }

    public void zatrzymaj() {
        obroty = 0; //stop
        System.out.println("silnik zatrzymany.");
    }

    public void zwiekszObroty() {
        obroty = obroty + 100;
        if (obroty > maxObroty) obroty = maxObroty;
    }

    public void zmniejszObroty() {
        obroty = obroty - 100;
        if (obroty < 800) obroty = 800;
    }

    public int getObroty() {
        return obroty;
    }

    public int getMaxObroty() {
        return maxObroty;
    }
}
