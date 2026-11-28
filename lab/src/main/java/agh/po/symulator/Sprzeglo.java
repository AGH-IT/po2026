package agh.po.symulator;

public class Sprzeglo extends Komponent {
    private boolean stanSprzegla;

    public Sprzeglo(String nazwa, double waga, double cena) {
        super(nazwa, waga, cena);
        this.stanSprzegla = false;
    }

    public void wcisnij() {
            stanSprzegla = true;
    }
    public void zwolnij() {
        stanSprzegla = false;
    }
    public boolean wcisniete() {
        return stanSprzegla;
    }
}

