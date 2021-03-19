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
        //TODO: Sprawdzanie czy produkt już znajduje się na liście
        boolean flagaDuplikatu = false;

        for(int i=0; i<getLength(); i++){
            if(lista[i] != null){
                if(lista[i].getDescription().equals(obj.getDescription())){
                    flagaDuplikatu = true;
                    i=getLength();
                }
            }
        }

        if(!flagaDuplikatu){
            if(index < lista.length){
                lista[index] = obj;
                index++;
            }else{
                increaseListSize();
                System.out.println("ZWIĘKSZONO ROZMIAR LISTY! :"+lista.length);
                lista[index] = obj;
                index++;
            }
            System.out.println("Dopisano do listy! "+obj.getDescription());
        }else{
            System.out.println("Plik o nazwie "+obj.getDescription()+" już znajduje się w bazie!");
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
        System.out.println("\nBaza List Zakupów!");
        for(int i=0; i<lista.length;i++){
            if(lista[i] == null){
                //System.out.println("Element ["+i+"]: "+lista[i]);
            }
            else
                System.out.println("Element ["+i+"]: "+lista[i].getDescription());
        }
    }

}
