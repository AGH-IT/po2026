package symulator;

public class Pozycja {
    double x=0;
    double y=0;
    void aktualizujPozycje(double deltaX, double deltaY){
        x+=deltaX;
        y+=deltaY;
    }
    String getPozycja(){
        return "(" + x + ", " + y + ")";
    }
}
