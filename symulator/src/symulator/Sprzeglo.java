package symulator;

public class Sprzeglo extends Komponent{
    boolean stanSprzegla;

    public Sprzeglo(String producent, String model) {
        super(producent, model);
        this.stanSprzegla = false;
    }

    void wcisnij(){
        stanSprzegla=true;
    }
    void zwolnij(){
        stanSprzegla=false;
    }
}
