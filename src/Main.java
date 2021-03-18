import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        Main klasaMain = new Main();

        Produkt maslo = new Produkt("maslo", "kg", true);
        Produkt maslo1 = new Produkt("maslo1", "kg", true);
        Produkt maslo2 = new Produkt("maslo2", "kg", true);

        Produkt[] listaProduktow = new Produkt[5];
        listaProduktow[0] = maslo;
        listaProduktow[1] = maslo1;
        listaProduktow[2] = maslo2;

        String nazwaPliku = "produkt.save";

        klasaMain.WriteObjectToFile(maslo, nazwaPliku);
        Produkt ogorek = (Produkt) klasaMain.ReadObjectFromFile(nazwaPliku);
        System.out.println("ogórek: " + ogorek.getName());

        //teraz cala lista
        klasaMain.WriteObjectToFile(listaProduktow, nazwaPliku);
        Produkt[] nowaLista = (Produkt[]) klasaMain.ReadObjectFromFile(nazwaPliku);
        System.out.println(listaProduktow[0].getName()+", "+listaProduktow[1].getName()+", "+listaProduktow[2].getName());


        //druga wersja
        ListaProduktow listaProduktow2 = new ListaProduktow(1);
        listaProduktow2.addToList(maslo);
        System.out.println("one: "+listaProduktow2.getItem(0).getName());
        listaProduktow2.addToList(maslo1);
        listaProduktow2.addToList(maslo2);
        System.out.println("one: "+listaProduktow2.getItem(0).getName()+", two: "+listaProduktow2.getItem(1).getName()+", three: "+listaProduktow2.getItem(2).getName());

        klasaMain.WriteObjectToFile(listaProduktow2, nazwaPliku);
        ListaProduktow nowaLista2 = (ListaProduktow) klasaMain.ReadObjectFromFile(nazwaPliku);
        nowaLista2.printOut();
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
