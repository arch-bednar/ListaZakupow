import java.io.Serializable;

public class BazaListZakupow implements Serializable {

    private ListaZakupow[] lista;
    private int index = 0;
    private int sizeIncrease = 8;
    private String opis;

    BazaListZakupow(int productAmount){
        this.lista = new ListaZakupow[productAmount];
    }

    BazaListZakupow(int productAmount, String opis){
        this.lista = new ListaZakupow[productAmount];
        this.opis = opis;
    }

    public void setDescripion(String desc){
        this.opis=desc;
    }

    public String getDescription(){
        return this.opis;
    }

    public void addToList(ListaZakupow obj){
        //Tutaj raczej nie trzeba sprawdzać czy już jest taka lista
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

    public ListaZakupow getItem(int ind){
        return lista[ind];
    }

    private void increaseListSize() {
        ListaZakupow[] nowaLista = new ListaZakupow[lista.length + sizeIncrease];

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
