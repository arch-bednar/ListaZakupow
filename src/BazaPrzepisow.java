import java.io.Serializable;

public class BazaPrzepisow implements Serializable {

    private Przepis[] lista;
    private int index = 0;
    private int sizeIncrease = 8;
    private String opis;

    BazaPrzepisow(int productAmount){
        this.lista = new Przepis[productAmount];
    }

    BazaPrzepisow(int productAmount, String opis){
        this.lista = new Przepis[productAmount];
        this.opis = opis;
    }

    public void setDescripion(String desc){
        this.opis=desc;
    }

    public String getDescription(){
        return this.opis;
    }

    public void addToList(Przepis obj){
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
                System.out.println("ZWIĘKSZONO ROZMIAR LISTY PRZEPISOW! :"+lista.length);
                lista[index] = obj;
                index++;
            }
            System.out.println("Dopisano przepis do listy! "+obj.getDescription());
        }else{
            System.out.println("Przepis o nazwie "+obj.getDescription()+" już znajduje się w Bazie Produktów!");
        }
    }


    public int getLength(){
        return lista.length;
    }

    public Przepis getItem(int ind){
        return lista[ind];
    }

    private void increaseListSize() {
        Przepis[] nowaLista = new Przepis[lista.length + sizeIncrease];

        //TODO: Replace manual array copy
        //Przepisanie listy
        for(int i=0; i<lista.length; i++){
            nowaLista[i] = lista[i];
        }
        this.lista = nowaLista;
    }


    public void printOut(){
        System.out.println("Baza Przepisow!");
        for(int i=0; i<lista.length;i++){
            if(lista[i] != null){
                System.out.println("Element ["+i+"] :"+lista[i].getDescription());
            }

        }
    }

}
