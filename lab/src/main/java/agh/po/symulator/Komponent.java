package agh.po.symulator;

public class Komponent {
    public String nazwa;
    public double waga;
    public double cena;

    public Komponent(String nazwa, double waga,double cena){
        this.nazwa = nazwa;
        this.waga = waga;
        this.cena = cena;
    }
    public String getNazwa(){
        return nazwa;
    }
    public double getWaga(){
        return waga;
    }
    public double getCena(){
        return cena;
    }
}


