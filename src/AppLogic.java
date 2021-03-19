import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AppLogic {
    private ListaProduktow bazaProduktow;
    private BazaListZakupow bazaListZakupow;
    private BazaPrzepisow bazaPrzepisow;

    AppLogic(){
        loadProducts();
        loadShoppingLists();
        loadRecipes();
    }

    private void loadProducts() {
        //załadowanie bazy produktów z pliku
        String bazaProduktowADRES = "bazaProduktow.save";
        bazaProduktow = (ListaProduktow) ReadObjectFromFile(bazaProduktowADRES);

        if (bazaProduktow == null){
            bazaProduktow = new ListaProduktow(1);
        }
    }

    public ListaProduktow getProductList(){
        //cała reszta komunikacji z tą bazą odbywa się już poprzez samą klasę ListaProduktow
        //baza jest względnie dobrze zabezpieczona
        return bazaProduktow;
    }

    private void loadShoppingLists() {
        //załadowanie bazy list zakupów
        String bazaListZakupowADRES = "bazaListZakupow.save";
        bazaListZakupow = (BazaListZakupow) ReadObjectFromFile(bazaListZakupowADRES);

        if(bazaListZakupow == null){
            bazaListZakupow = new BazaListZakupow(1);
        }
    }

    public BazaListZakupow getShoppingListBase(){
        return bazaListZakupow;
    }

    private void loadRecipes() {
        String bazaPrzepisowADRES = "bazaPrzepisow.save";
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
            System.out.println("The Object  was succesfully written to a file");
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
            e.printStackTrace();
        }
        return serObj;
    }
}
