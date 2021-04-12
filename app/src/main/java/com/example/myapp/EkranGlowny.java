package com.example.myapp;
import com.example.myapp.logic.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


import java.io.File;

public class EkranGlowny extends AppCompatActivity {

    private Button buttonProdukty, buttonPrzepisy, buttonListyZakupow;
    public static final String LOGIKA = "XD";
    public AppLogic logic;
    private File directory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        directory  = getFilesDir();
        logic = new AppLogic(directory);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonProdukty = (Button) findViewById(R.id.produkty);
        buttonProdukty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEkranProduktow();
            }
        });

        buttonPrzepisy = (Button) findViewById(R.id.przepisy);
        buttonPrzepisy.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openEkranPrzepisow();
            }
        });

        buttonListyZakupow = (Button) findViewById(R.id.listy);
        buttonListyZakupow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openEkranListZakupow();
            }
        } );
    }

    private void startBase() {
        BazaProduktow bP = logic.getProductBase();
        BazaListZakupow bLZ = logic.getShoppingListBase();
        BazaPrzepisow bPZ = logic.getRecipesBase();

        Produkt garmin = new Produkt("Garmin Edge 830", Produkt.UNIT_SZT, false);
        Produkt makaTortowa = new Produkt("Mąka Tortowa", Produkt.UNIT_KG, false);
        Produkt makaBrzeska = new Produkt("Mąka Brzeska", Produkt.UNIT_KG, false);
        Produkt makaZiemniaczana = new Produkt("Mąka Ziemniaczana", Produkt.UNIT_KG, false);

        bP.addToList(garmin);
        bP.addToList(makaTortowa);
        bP.addToList(makaBrzeska);
        bP.addToList(makaZiemniaczana);

        ListaZakupow rowerowe = new ListaZakupow(1, "Zakupy rowerowe.");
        ListaZakupow makaron = new ListaZakupow(1, "Makaron");

        makaron.addToList(new ProduktNaLiscie(makaTortowa, 1));
        makaron.addToList(new ProduktNaLiscie(makaZiemniaczana, 1));

        bLZ.addToList(makaron);
        bLZ.addToList(rowerowe);


        ProduktNaLiscie garm = new ProduktNaLiscie(bP.getItem(0), 1);
        bLZ.getItem(0).addToList(garm);
        bLZ.getItem(0).printOut();

        Przepis udanyTrail = new Przepis(1,"Udany trail", "Ohh Fox!");
        Produkt opona = new Produkt("Continental Race King", Produkt.UNIT_SZT, false);
        ProduktWPrzepisie opony = new ProduktWPrzepisie(opona, 2);

        Produkt pozycyjneT = new Produkt("Lezyne Strip Drive Pro 300", Produkt.UNIT_SZT, false);
        ProduktWPrzepisie lezyneTyl = new ProduktWPrzepisie(pozycyjneT, 1, false);

        bP.addToList(opona);
        bP.addToList(pozycyjneT);

        udanyTrail.addToList(opony);
        udanyTrail.addToList(lezyneTyl);

        bPZ.addToList(udanyTrail);


        //bPZ.getItem(0).printOut();


        bLZ.getItem(0).addPrzepis(bPZ.getItem(0));

        bLZ.getItem(0).printOut();


        //bPZ.printOut();
        //bLZ.printOut();
        //bP.printOut();
        logic.save();
    }

    private void openEkranListZakupow() {
        Intent intent = new Intent(this, EkranListZakupow.class);
        intent.putExtra(LOGIKA, logic);
        startActivity(intent);
    }

    private void openEkranPrzepisow() {
        Intent intent = new Intent(this, EkranPrzepisow.class);
        intent.putExtra(LOGIKA, logic);
        startActivity(intent);
    }

    private void openEkranProduktow() {
        Intent intent = new Intent(this, EkranProduktow.class);
        intent.putExtra(LOGIKA, logic);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}