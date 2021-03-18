import java.io.Serializable;

public class Produkt implements Serializable {
    private String nazwa;
    private String jednostka;
    private boolean ulubione;

    Produkt(String nazwa, String jednostka, boolean ulubione){
        this.nazwa = nazwa;
        this.jednostka = jednostka;
        this.ulubione = ulubione;
    }

    public void setName(String name){
        this.nazwa = name;
    }

    public void setUnit(String unit){
        this.jednostka = unit;
    }

    public void setFavorite(boolean fav){
        this.ulubione = fav;
    }

    public String getName(){
        return nazwa;
    }

    public String getUnit(){
        return jednostka;
    }

    public boolean getFavorite(){
        return ulubione;
    }
    
    public String toString() {return nazwa;}
}
