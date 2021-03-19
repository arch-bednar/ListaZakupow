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

    ProduktNaLiscie(Produkt produkt, double ilosc){
        this.produkt = produkt;
        this.ilosc = ilosc;
    }

    public String toString() {
        return "Produkt: "+produkt.getName()+", jednostka: "+produkt.getUnit()+", ilosc: "+ilosc;
    }
}
