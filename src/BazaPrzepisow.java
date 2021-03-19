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
        for(int i=0; i<lista.length;i++){
            System.out.println("Element ["+i+"] :"+lista[i]);
        }
    }

}
