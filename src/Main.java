import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        AppLogic logikaAplikacji = new AppLogic();
        //BazaProduktow bP = logikaAplikacji.getProductList();
        logikaAplikacji.save();



        
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


    public void aTakieTam(){

        Main klasaMain = new Main();

        //======================================================================
        //BAZA PRODUKTOW
        //======================================================================

        //nazwa pliku do którego zapiszemy bazę produktow
        String nazwaPliku = "bazaProduktow.save";

        //tworzę produkty do zapisu na bazie
        Produkt maslo = new Produkt("maslo", Produkt.UNIT_KG, true);
        Produkt maka = new Produkt("mąka", Produkt.UNIT_KG, true);
        Produkt miod = new Produkt("miód", Produkt.UNIT_KG, true);
        Produkt mleko = new Produkt("mleko", Produkt.UNIT_L, false);
        

        //tworzę listę bazy i dodaję produkty
        BazaProduktow bazaProduktow2 = new BazaProduktow(1);
        bazaProduktow2.addToList(maslo);
        bazaProduktow2.addToList(maka);
        bazaProduktow2.addToList(miod);
        bazaProduktow2.addToList(mleko);

        //zapisuję listę produktów do pliku (to jest nasza baza produktow)
        klasaMain.WriteObjectToFile(bazaProduktow2, nazwaPliku);

        //załadowanie bazy z pliku
        BazaProduktow nowaLista2 = (BazaProduktow) klasaMain.ReadObjectFromFile(nazwaPliku);

        //wypisanie zawartości bazy produktów
        nowaLista2.printOut();

        //======================================================================
        //PRODUKTY NA LIŚCIE ZAKUPÓW
        //======================================================================
        String listaNR1= "listaNR1.save";

        //nowa lista zakupów
        ListaZakupow listaZakupow1 = new ListaZakupow(1);

        //produkty na tej liście
        ProduktNaLiscie maselko = new ProduktNaLiscie(maslo, 0.5, false);
        ProduktNaLiscie mleczko = new ProduktNaLiscie(mleko, 3, true);

        //dodanie produktow do listy
        listaZakupow1.addToList(maselko);
        listaZakupow1.addToList(mleczko);

        //zapis do pliku
        klasaMain.WriteObjectToFile(listaZakupow1, listaNR1);

        //załadowanie listy z pliku
        ListaZakupow listaPierwsza = (ListaZakupow) klasaMain.ReadObjectFromFile(listaNR1);

        //wypisanie listy zakupów
        listaPierwsza.printOut();

        //======================================================================
        //PRZEPISY
        //======================================================================
        String pMiodownikNR1 = "przepisMiodownik.save";

        //Tworzymy produkty do przepisu
        ProduktWPrzepisie pMaka = new ProduktWPrzepisie(maka, 0.5);
        ProduktWPrzepisie pMaslo = new ProduktWPrzepisie(maslo, 1);
        ProduktWPrzepisie pMiod = new ProduktWPrzepisie(miod, 0.25);

        //Teraz przepis
        Przepis przepisMiodownik = new Przepis(1, "Ciasto Miodownik");

        //Dodajemy poszczególne składniki
        przepisMiodownik.addToList(pMaka);
        przepisMiodownik.addToList(pMaslo);
        przepisMiodownik.addToList(pMiod);

        //zapis do pliku
        klasaMain.WriteObjectToFile(przepisMiodownik, pMiodownikNR1);

        //załadowanie przepisu z pliku
        Przepis przepisNaMiodownik = (Przepis) klasaMain.ReadObjectFromFile(pMiodownikNR1);

        //wypisanie zawartości przepisu
        przepisNaMiodownik.printOut();
    }

}
