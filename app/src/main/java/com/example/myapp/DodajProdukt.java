package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DodajProdukt extends AppCompatActivity {
    Button back, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodaj_produkt);
        back = (Button) findViewById(R.id.backToProducts);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToProducts();
            }
        });

        save = (Button) findViewById(R.id.saveNewProduct);
        save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                saveNewProduct();
            }
        });
    }

    private void saveNewProduct() {
        System.out.println("Zapisano nowy produkt!");
        backToProducts();
    }

    private void backToProducts() {
        Intent intent = new Intent(this, EkranProduktow.class);
        startActivity(intent);
    }
}