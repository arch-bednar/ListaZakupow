package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class EkranGlowny extends AppCompatActivity {

    private Button buttonProdukty, buttonPrzepisy, buttonListyZakupow;
    //ZMIENNA

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                openEkranListProduktow();
            }
        } );
        //TU ŁADUJĘ BAZĘ
    }

    private void openEkranListProduktow() {
        Intent intent = new Intent(this, EkranListZakupow.class);

        startActivity(intent);
    }

    private void openEkranPrzepisow() {
        Intent intent = new Intent(this, EkranPrzepisow.class);
        startActivity(intent);
    }

    private void openEkranProduktow() {
        Intent intent = new Intent(this, EkranProduktow.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}