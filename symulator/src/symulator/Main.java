package symulator;

public class Main {
    static void main(String[] args){
        Silnik silnik = new Silnik("BMW","N57",7000);
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("BMW","ZF 8HP",8);
        Pozycja pozycja = new Pozycja();

        Samochod autko = new Samochod(silnik,skrzynia,pozycja);
        autko.wlacz();
        autko.skrzynia.zwiekszBieg();
        autko.skrzynia.zwiekszBieg();
        autko.skrzynia.zmniejszBieg();
    }
}
