package agh.po.symulator;

public class Samochod {
    private boolean stanWlaczenia;
    private String nrRejest;
    private String model;
    private int predkoscMax;

    private Silnik silnik;
    private SkrzyniaBiegow skrzynia;
    private Pozycja aktualnaPozycja;

    public Samochod(String nrRejest, String model, int predkoscMax,
                    Silnik silnik, SkrzyniaBiegow skrzynia, Pozycja pozycja) {
        this.nrRejest = nrRejest;
        this.model = model;
        this.predkoscMax = predkoscMax;
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.aktualnaPozycja = pozycja;
        this.stanWlaczenia = false;
    }

    public void wlacz() {
        silnik.uruchom();
        stanWlaczenia = true;
    }

    public void wylacz() {
        silnik.zatrzymaj();
        skrzynia.zeruj();
        stanWlaczenia = false;
    }

    public Silnik getSilnik() {
        return silnik;
    }

    public SkrzyniaBiegow getSkrzynia() {
        return skrzynia;
    }

    public Pozycja getAktPozycja() {
        return aktualnaPozycja;
    }

    public double getWaga() {
        return silnik.getWaga() + skrzynia.getWaga() + skrzynia.getSprzeglo().getWaga();
    }


    public String toString() {
        return model + " [" + nrRejest + "]"
                + " wlaczony: " + stanWlaczenia
                + " obroty: " + silnik.getObroty()
                + " bieg: " + skrzynia.getAktualnyBieg()
                + " sprzeglo wcisniete: " + skrzynia.getSprzeglo().isWcisniete()
                + " pozycja: " + aktualnaPozycja.getPozycja();
    }

    public static void main(String[] args) {
        Sprzeglo sprzeglo = new Sprzeglo("Sprzeglo LUK", 8.5, 900);
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("Skrzynia 5b", 45, 4000, 5, sprzeglo);
        Silnik silnik = new Silnik("Silnik 2.0", 110, 15000, 6500);
        Pozycja pozycja = new Pozycja(0, 0);

        Samochod auto = new Samochod("KR77777", "Kia Stinger", 180, silnik, skrzynia, pozycja);

        System.out.println(auto);
        auto.wlacz();
        System.out.println(auto);

        sprzeglo.wcisnij();
        skrzynia.zwiekszBieg();
        skrzynia.zwiekszBieg();
        sprzeglo.zwolnij();
        silnik.zwiekszObroty();
        silnik.zwiekszObroty();
        silnik.zwiekszObroty();
        System.out.println(auto);

        skrzynia.zmniejszBieg();
        pozycja.aktualizujPozycje(10, 5);
        System.out.println(auto);

        auto.wylacz();
        System.out.println(auto);
    }
}



