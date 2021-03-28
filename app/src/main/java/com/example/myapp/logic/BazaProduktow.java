package com.example.myapp.logic;
import java.io.Serializable;

public class BazaProduktow implements Serializable {

    private Produkt[] lista;
    private int index = 0;
    private int sizeIncrease = 8;
    private int itemCount = 0;

    BazaProduktow(int productAmount){
        this.lista = new Produkt[productAmount];
    }

    public void addToList(Produkt obj){
        //TODO: Sprawdzanie czy produkt już znajduje się na liście
        boolean flagaDuplikatu = false;

        for(int i=0; i<getLength(); i++){
            if(lista[i] != null){
                if(lista[i].getName().equals(obj.getName())){
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
            System.out.println("Dopisano do listy! "+obj.getName());
            itemCount++;
        }else{
            System.out.println("Plik o nazwie "+obj.getName()+" już znajduje się w Bazie Produktów!");
        }
    }

    public int getLength(){
        return lista.length;
    }

    public int getItemCount(){
        return itemCount;
    }

    public Produkt getItem(int ind){
        return lista[ind];
    }

    public Produkt[] getWholeProductList(){
        return lista;
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
        System.out.println("\nBaza Produktow!");
        for(int i=0; i<lista.length;i++){
            if(lista[i] != null){
                System.out.println("Element ["+i+"] :"+lista[i]);
            }

        }
    }

    public String getItemName(int position) {

        return getItem(position).getName();
        //return "XD";
    }
}
