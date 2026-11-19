package agh.po.symulator;

public class Car {
    private Engine engine;
    private Transmission transmission;
    private Position position;

    public Car(Engine e, Transmission t, Position p) {
        engine = e;
        transmission = t;
        position = p;
    }

    public void startCar() {
        engine.engineOn();
    }

    public void turnOffCar() {
        engine.engineOff();
    }

    public static void main(String[] args) {
        Transmission t1 = new Transmission();
        Engine e1 = new Engine();
        Position p = new Position();
        p.setPosition(0.0,0.0);
        Car car1 = new Car(e1, t1, p);
    }
}
