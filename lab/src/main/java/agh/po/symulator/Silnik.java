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
        if(obroty > 0) {
            obroty = obroty + 100;
            if (obroty > maxObroty) {
                obroty = maxObroty;
            }
        }
    }

    public void zmniejszObroty() {
        if (obroty > 0) {
            obroty = obroty - 100;
            int minObroty = 800;
            if (obroty < minObroty) {
                obroty = minObroty;
            }
        }
    }

    public int getObroty() {
        return obroty;
    }

    public int getMaxObroty() {
        return maxObroty;
    }
}
