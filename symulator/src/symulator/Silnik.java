package symulator;

public class Silnik extends Komponent{
    int maxObroty;
    int obroty;

    public Silnik(String producent, String model, int maxObroty) {
        super(producent, model);
        this.maxObroty = Math.max(0, maxObroty);
        this.obroty = 0;
    }

    void uruchom(){
        this.obroty=1000;
    }
    void zatrzymaj(){
        this.obroty=0;
    }
}
