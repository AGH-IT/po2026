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
    private void sprawdzSprzeglo(){
        if (sprzeglo == null || !sprzeglo.wcisniete()){
            throw new IllegalStateException("Nie można zmienić biegu bez wcisnietego sprzegła!");
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
