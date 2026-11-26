package agh.po.symulator;

public class Pozycja {
    private double x;
    private double y;

    public Pozycja() {
        this(0, 0);
    }

    public Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void aktualizujPozycje(double deltaX, double deltaY) {
        x += deltaX;
        y += deltaY;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public void przemiesc(double dt, double v, Pozycja cel) {
        double droga = v * dt;
        double odleglosc = Math.sqrt(Math.pow(getX() - cel.x, 2) + Math.pow(getY() - cel.y, 2));
        double dx = v * dt * (cel.x - getX()) / odleglosc;
        double dy = v * dt * (cel.y - getY()) / odleglosc;
        if (droga > odleglosc) {
            y = cel.y;
            x = cel.x;
        } else {
            aktualizujPozycje(dx, dy);
        }
    }
    public String getPozycja() {
        return "(" + x + ", " + y + ")";
    }
}




