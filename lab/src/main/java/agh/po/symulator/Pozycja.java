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
        if (cel == null || v == 0 || dt == 0) {
            return;
        }

        double odleglosc = Math.sqrt(Math.pow(cel.x - x, 2) + Math.pow(cel.y - y, 2));

        if (odleglosc == 0) {
            return;
        }

        double droga = v * dt;

        if (droga >= odleglosc) {
            this.x = cel.x;
            this.y = cel.y;
        } else {
            double dx = droga * (cel.x - x) / odleglosc;
            double dy = droga * (cel.y - y) / odleglosc;
            aktualizujPozycje(dx, dy);
        }
    }
    public String getPozycja() {
        return "(" + x + ", " + y + ")";
    }
}




