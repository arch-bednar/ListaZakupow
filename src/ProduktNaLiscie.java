import java.io.Serializable;

public class ProduktNaLiscie implements Serializable {
    public Produkt produkt;
    public double ilosc;
    public boolean wybrane;

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

    public double getAmount(){
        return ilosc;
    }

    public void setAmount(double a){
        ilosc = a;
    }
    public String getName(){
        return produkt.getName();
    }
    public String getDescription() {
        return produkt.getName();
    }
}
