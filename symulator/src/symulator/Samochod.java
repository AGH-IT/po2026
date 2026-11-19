package symulator;

public class Samochod {
    Silnik silnik;
    SkrzyniaBiegow skrzynia;
    Pozycja pozycja;

    public Samochod(Silnik silnik, SkrzyniaBiegow skrzynia, Pozycja pozycja) {
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.pozycja = pozycja;
    }

    void wlacz(){
        silnik.uruchom();
    }
    void wylacz(){
        silnik.zatrzymaj();
    }
}
