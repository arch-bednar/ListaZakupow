package com.example.myapp.logic;
import java.io.Serializable;

public class Przepis implements Serializable {

    public ProduktWPrzepisie[] lista;
    private int index = 0;
    private int sizeIncrease = 8;
    private String opis;
    private String nazwa;
    private int itemCount =0;

    Przepis(int productAmount){
        this.lista = new ProduktWPrzepisie[productAmount];
    }

    public Przepis(int productAmount, String nazwa, String opis){
        this.lista = new ProduktWPrzepisie[productAmount];
        this.opis = opis;
        this.nazwa = nazwa;
    }

    public String getRecipe(){
        return opis;
    }

    public void setRecipe(String recipe){
        this.opis = recipe;
    }


    public String getDescription(){
        return this.nazwa;
    }

    public String getName(){
        return this.nazwa;
    }

    public void addToList(ProduktWPrzepisie obj){


        boolean flagaDuplikatu = false;

        for(int i=0; i<getLength(); i++){
            if(lista[i] != null){
                if(lista[i].getName().equals(obj.getName())){
                    flagaDuplikatu = true;
                    lista[i].setAmount(lista[i].getAmount() + obj.getAmount());
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
            System.out.println("Dopisano produkt do listy! "+obj.getName());
        }else{
            System.out.println("Produkt znajduje się już na liście!");

        }

    }

    public String getItemName(int index){
        return getItem(index).getName();
    }

    public int getLength(){
        return lista.length;
    }

    public ProduktWPrzepisie getItem(int ind){
        return lista[ind];
    }

    private void increaseListSize() {
        ProduktWPrzepisie[] nowaLista = new ProduktWPrzepisie[lista.length + sizeIncrease];

        //Przepisanie listy
        for(int i=0; i<lista.length; i++){
            nowaLista[i] = lista[i];
        }
        this.lista = nowaLista;
    }

    public void printOut(){
        System.out.println("Przepis: " + getDescription());
        for(int i=0; i<lista.length;i++){
            if(lista[i] != null)
                System.out.println("Element ["+i+"] :"+lista[i]);
        }
    }

    public int getItemCount() {
        return index;
    }

    public void removeElement(int indeks){
        ProduktWPrzepisie[] nowaLista = new ProduktWPrzepisie[lista.length-1];
        //Przepisanie listy
        for(int i=0,k=0; i<lista.length; i++){
            if (i==indeks){
                continue;
            }
            nowaLista[k++] = lista[i];
        }
        this.lista = nowaLista;
        this.index--;
    }
}
