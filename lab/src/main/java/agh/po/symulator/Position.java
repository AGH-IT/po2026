package agh.po.symulator;

public class Position {
    private double x;
    private double y;

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String getPosition() {
        return "Position of car: "+x+", "+y+" (x,y).";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
