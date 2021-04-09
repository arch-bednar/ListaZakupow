package com.example.myapp.logic;
import java.io.Serializable;

public class ProduktNaLiscie implements Serializable {
    private Produkt produkt;
    private double ilosc;
    private boolean wybrane;

    ProduktNaLiscie(Produkt produkt, double ilosc, boolean wybrane){
        this.produkt = produkt;
        this.ilosc = ilosc;
        this.wybrane = wybrane;
    }

    public ProduktNaLiscie(Produkt produkt, double ilosc){
        this.produkt = produkt;
        this.ilosc = ilosc;
    }

    public String toString() {
        return "Produkt: "+produkt.getName()+", jednostka: "+produkt.getUnit()+", ilosc: "+ilosc;
    }

    public double getAmount(){
        return ilosc;
    }

    public String getUnit(){
        return produkt.getUnit();
    }

    public boolean isActivated(){
        return wybrane;
    };

    public void setChecked(boolean state){
        this.wybrane = state;
    }

    public void setAmount(double a){
        ilosc = a;
    }

    public String getDescription() {
        return produkt.getName();
    }

    public  Produkt getProduct() {return produkt;}
}
