package com.example.myapp.logic;
import java.io.Serializable;


public class ProduktWPrzepisie implements Serializable {
    public Produkt produkt;
    public double ilosc;


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

    public double getAmount(){
        return ilosc;
    }

    public void setAmount(double a){
        ilosc = a;
    }
}
