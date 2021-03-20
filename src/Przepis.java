import java.io.Serializable;

public class Przepis implements Serializable {

    private ProduktWPrzepisie[] lista;
    private int index = 0;
    private int sizeIncrease = 8;
    private String opis;
    private String nazwa;

    Przepis(int productAmount){
        this.lista = new ProduktWPrzepisie[productAmount];
    }

    Przepis(int productAmount, String nazwa ,String opis){
        this.lista = new ProduktWPrzepisie[productAmount];
        this.opis = opis;
        this.nazwa = nazwa;
    }

    public void setDescripion(String desc){
        this.opis=desc;
    }

    public String getDescription(){
        return this.nazwa;
    }

    public void addToList(ProduktWPrzepisie obj){
        if(index < lista.length){
            lista[index] = obj;
            index++;
        }else{
            increaseListSize();
            System.out.println("ZWIĘKSZONO ROZMIAR LISTY! :"+lista.length);
            lista[index] = obj;
            index++;
        }
    }
    public int getLength(){
        return lista.length;
    }

    public ProduktWPrzepisie getItem(int ind){
        return lista[ind];
    }

    private void increaseListSize() {
        ProduktWPrzepisie[] nowaLista = new ProduktWPrzepisie[lista.length + sizeIncrease];

        //TODO: Replace manual array copy
        //Przepisanie listy
        for(int i=0; i<lista.length; i++){
            nowaLista[i] = lista[i];
        }
        this.lista = nowaLista;
    }

    public void printOut(){
        System.out.println("Przepis: " + opis);
        for(int i=0; i<lista.length;i++){
            System.out.println("Element ["+i+"] :"+lista[i]);
        }
    }

}
