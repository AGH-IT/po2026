package symulator;

public class SkrzyniaBiegow extends Komponent{
    int aktualnyBieg;
    int iloscBiegow;

    public SkrzyniaBiegow(String producent, String model, int iloscBiegow) {
        super(producent, model);
        this.iloscBiegow = Math.max(1, iloscBiegow);
        this.aktualnyBieg = 0;
    }

    void zwiekszBieg(){
        if(aktualnyBieg<iloscBiegow){
            aktualnyBieg++;
        }
    }
    void zmniejszBieg(){
        if(aktualnyBieg>1){
            aktualnyBieg--;
        }
    }
}
