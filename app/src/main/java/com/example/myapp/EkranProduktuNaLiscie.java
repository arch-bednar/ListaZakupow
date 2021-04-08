package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapp.logic.ListaZakupow;

public class EkranProduktuNaLiscie extends AppCompatActivity {
    private TextView productName;
    private int productOnListID;
    private ListaZakupow listaZakupow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_produktu_na_liscie);

        Intent intent = getIntent();
        productOnListID = (int) intent.getIntExtra(CustomAdapterListScreen.productID, 0);
        listaZakupow = (ListaZakupow) intent.getSerializableExtra(CustomAdapterListScreen.listID);

        //first get all references
        productName = (TextView) findViewById(R.id.textViewProductOnListName);


        //then set the proper values
        productName.setText(listaZakupow.getItem(productOnListID).getDescription());



    }
}