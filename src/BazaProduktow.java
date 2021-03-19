import java.io.Serializable;

public class BazaProduktow implements Serializable {

    private Produkt[] lista;
    private int index = 0;
    private int sizeIncrease = 8;

    BazaProduktow(int productAmount){
        this.lista = new Produkt[productAmount];
    }

    public void addToList(Produkt obj){
        //TODO: Sprawdzanie czy produkt już znajduje się na liście

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

    public Produkt getItem(int ind){
        return lista[ind];
    }

    private void increaseListSize() {
        Produkt[] nowaLista = new Produkt[lista.length + sizeIncrease];

        //TODO: Replace manual array copy
        //Przepisanie listy
        for(int i=0; i<lista.length; i++){
            nowaLista[i] = lista[i];
        }
        this.lista = nowaLista;
    }

    public void printOut(){
        for(int i=0; i<lista.length;i++){
            System.out.println("Element ["+i+"] :"+lista[i]);
        }
    }

}
