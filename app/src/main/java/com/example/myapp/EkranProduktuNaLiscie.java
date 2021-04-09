package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;


import com.example.myapp.logic.ListaZakupow;
import com.google.android.material.textfield.TextInputEditText;

public class EkranProduktuNaLiscie extends AppCompatActivity {
    private TextView productName;
    private EditText productAmount;
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
        productAmount = (EditText) findViewById(R.id.editDecimalAmount);

        //then set the proper values
        productName.setText(listaZakupow.getItem(productOnListID).getDescription());
        productAmount.setText(""+listaZakupow.getItem(productOnListID).getAmount(), TextView.BufferType.EDITABLE);
        productAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //System.out.println("\n\n\n\nPRZED EDYCJĄ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //System.out.println("\n\n\n\nW TRAKCIE EDYCJI");
            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("\n\n\n\nSKOŃCZONO EDYCJĘ TEKSTU");
            }
        });





    }
}