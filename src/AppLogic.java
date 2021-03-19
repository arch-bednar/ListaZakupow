import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AppLogic {
    private BazaProduktow bazaProduktow;
    private BazaListZakupow bazaListZakupow;
    private BazaPrzepisow bazaPrzepisow;

    String bazaProduktowADRES = "bazaProduktow.save";
    String bazaListZakupowADRES = "bazaListZakupow.save";
    String bazaPrzepisowADRES = "bazaPrzepisow.save";

    AppLogic(){
        loadProducts();
        loadShoppingLists();
        loadRecipes();
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

    public BazaProduktow getProductList(){
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



    public void WriteObjectToFile(Object serObj, String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file: "+filePath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object ReadObjectFromFile(String filePath){
        Object serObj = null;
        try{
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            serObj = objectIn.readObject();
            objectIn.close();
        }catch(Exception e){
            System.out.println("Uppsi, nie znaleziono pliku!");
            e.printStackTrace();
        }
        return serObj;
    }
}
