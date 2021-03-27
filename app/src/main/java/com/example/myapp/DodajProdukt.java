package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class DodajProdukt extends AppCompatActivity {
    Button back, save;
    Spinner dropdown;
    String[] items;
    EditText text;

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

        dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        items = new String[]{"KG", "SZT", "L"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        text = (EditText) findViewById(R.id.nazwaProduktu);

    }

    private void saveNewProduct() {
        System.out.println("#####################################################\n              DODANO PRODUKT!\n     ");
        System.out.println(text.getText()+"  "+items[(int) dropdown.getSelectedItemId()] );

        backToProducts();
    }

    private void backToProducts() {
        Intent intent = new Intent(this, EkranProduktow.class);
        startActivity(intent);
    }
}