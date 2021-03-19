import java.io.Serializable;


public class ProduktWPrzepisie implements Serializable {
    private Produkt produkt;
    private double ilosc;
    private boolean wybrane;

    ProduktWPrzepisie(Produkt produkt, double ilosc, boolean wybrane){
        this.produkt = produkt;
        this.ilosc = ilosc;
        this.wybrane = wybrane;
    }

    ProduktWPrzepisie(Produkt produkt, double ilosc){
        this.produkt = produkt;
        this.ilosc = ilosc;
    }

    public String toString() {
        return "Produkt: "+produkt.getName()+", jednostka: "+produkt.getUnit()+", ilosc: "+ilosc;
    }
}
