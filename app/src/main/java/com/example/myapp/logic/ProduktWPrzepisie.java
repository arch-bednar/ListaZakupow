package com.example.myapp.logic;
import java.io.Serializable;


public class ProduktWPrzepisie implements Serializable {
    public Produkt produkt;
    public double ilosc;
    private boolean wybrane = false;


    public ProduktWPrzepisie(Produkt produkt, double ilosc, boolean wybrane){
        this.produkt = produkt;
        this.ilosc = ilosc;

    }

    public ProduktWPrzepisie(Produkt produkt, double ilosc){
        this.produkt = produkt;
        this.ilosc = ilosc;
    }

    public String toString() {
        return "Produkt: "+produkt.getName()+", jednostka: "+produkt.getUnit()+", ilosc: "+ilosc;
    }
    public boolean isActivated(){
        return wybrane;
    };

    public double getAmount(){
        return ilosc;
    }

    public void setAmount(double a){
        ilosc = a;
    }

    public String getUnit(){
        return produkt.getUnit();
    }

    public String getName() {
        return produkt.getName();
    }
}
