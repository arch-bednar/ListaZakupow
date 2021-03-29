package com.example.myapp.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.content.Context.*;

public class AppLogic implements Serializable {
    private BazaProduktow bazaProduktow;
    private BazaListZakupow bazaListZakupow;
    private BazaPrzepisow bazaPrzepisow;

    String odczyt = "/data/data/com.example.myapp/files/";
    String bazaProduktowADRES = "bazaProduktow.save";
    String bazaListZakupowADRES = "bazaListZakupow.save";
    String bazaPrzepisowADRES = "bazaPrzepisow.save";

    private File directory;

    public AppLogic(File dir){
        loadProducts();
        loadShoppingLists();
        loadRecipes();
        this.directory = dir;
    }

    public void save(){
        WriteObjectToFile(bazaProduktow, bazaProduktowADRES);
        WriteObjectToFile(bazaListZakupow, bazaListZakupowADRES);
        WriteObjectToFile(bazaPrzepisow, bazaPrzepisowADRES);
    }

    private void loadProducts() {
        //załadowanie bazy produktów z pliku
        bazaProduktow = (BazaProduktow) ReadObjectFromFile(bazaProduktowADRES);

        if (bazaProduktow == null){
            bazaProduktow = new BazaProduktow(1);
        }
    }
    public BazaProduktow getProductBase(){
        //cała reszta komunikacji z tą bazą odbywa się już poprzez samą klasę ListaProduktow
        //baza jest względnie dobrze zabezpieczona
        return bazaProduktow;
    }

    private void loadShoppingLists() {
        //załadowanie bazy list zakupów
        bazaListZakupow = (BazaListZakupow) ReadObjectFromFile(bazaListZakupowADRES);

        if(bazaListZakupow == null){
            bazaListZakupow = new BazaListZakupow(1);
        }
    }
    public BazaListZakupow getShoppingListBase(){
        return bazaListZakupow;
    }

    private void loadRecipes() {
        //załadowanie bazy przepisów
        bazaPrzepisow = (BazaPrzepisow) ReadObjectFromFile(bazaPrzepisowADRES);

        if(bazaPrzepisow == null){
            bazaPrzepisow = new BazaPrzepisow(1);
        }
    }
    public BazaPrzepisow getRecipesBase(){
        return bazaPrzepisow;
    }


    public void WriteObjectToFile(Object serObj, String filePath) {
        File file = new File(directory, filePath);
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("\n\n\n\n\n\n\nThe Object  was succesfully written to a filePATH: "+file+", DIR: "+directory);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("\n\n\n\n\n Błąd zapisu");
        }
    }
    public Object ReadObjectFromFile(String filePath){
        Object serObj = null;
        try{
            FileInputStream fileIn = new FileInputStream(odczyt+filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            serObj = (Object) objectIn.readObject();
            objectIn.close();
        }catch(Exception e){
            System.out.println("Upps, nie znaleziono pliku!");
            //e.printStackTrace();
        }
        return serObj;
    }
}
