import java.io.Serializable;

public class ListaZakupow implements Serializable {

    private ProduktNaLiscie[] lista;
    private int index = 0;
    private int sizeIncrease = 8;
    private String opis;

    ListaZakupow(int productAmount){
        this.lista = new ProduktNaLiscie[productAmount];
    }

    ListaZakupow(int productAmount, String opis){
        this.lista = new ProduktNaLiscie[productAmount];
        this.opis = opis;
    }

    public void setDescripion(String desc){
        this.opis=desc;
    }

    public String getDescription(){
        return this.opis;
    }

    public void addToList(ProduktNaLiscie obj){
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

    public ProduktNaLiscie getItem(int ind){
        return lista[ind];
    }

    private void increaseListSize() {
        ProduktNaLiscie[] nowaLista = new ProduktNaLiscie[lista.length + sizeIncrease];

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
