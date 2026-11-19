package agh.po.symulator;

public class SkrzyniaBiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;
    private Sprzeglo sprzeglo;

    public SkrzyniaBiegow(String nazwa, double waga , double cena, int iloscBiegow, Sprzeglo sprzeglo){
        super(nazwa, waga, cena);
        this.iloscBiegow = iloscBiegow;
        this.sprzeglo = sprzeglo;
        this.aktualnyBieg = 0;
    }
    public void zwiekszBieg(){
        if (aktualnyBieg < iloscBiegow){
            aktualnyBieg++;
        }
    }
    public void zmniejszBieg(){
        if(aktualnyBieg > 0 ) {
            aktualnyBieg--;
        }
    }
    public void zeruj(){
        aktualnyBieg = 0;
    }
    public int getAktualnyBieg() {
        return aktualnyBieg;
    }
    public Sprzeglo getSprzeglo(){
        return sprzeglo;
    }
}
