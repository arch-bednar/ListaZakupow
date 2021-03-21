import java.io.Serializable;


public class ProduktWPrzepisie implements Serializable {
    private Produkt produkt;
    private double ilosc;


    ProduktWPrzepisie(Produkt produkt, double ilosc, boolean wybrane){
        this.produkt = produkt;
        this.ilosc = ilosc;

    }

    ProduktWPrzepisie(Produkt produkt, double ilosc){
        this.produkt = produkt;
        this.ilosc = ilosc;
    }

    public String toString() {
        return "Produkt: "+produkt.getName()+", jednostka: "+produkt.getUnit()+", ilosc: "+ilosc;
    }
    public  Produkt getProdukt(){ return produkt;}
    public double getAmount(){
        return ilosc;
    }
    public String getName(){ return produkt.getName();
    }
    public void setAmount(double a){
        ilosc = a;
    }
}
