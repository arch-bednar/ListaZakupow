package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.myapp.logic.AppLogic;
import com.example.myapp.logic.ListaZakupow;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

public class EkranProduktuNaLiscie extends AppCompatActivity {
    private TextView productName;
    private EditText productAmount;
    private int productOnListID;
    private int shoppingListID;
    private ListaZakupow listaZakupow;
    private AppLogic appLogic;
    private Button back;
    private File directory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_produktu_na_liscie);

        Intent intent = getIntent();
        productOnListID = (int) intent.getIntExtra(CustomAdapterListScreen.productID, 0);
        shoppingListID = (int) intent.getIntExtra(CustomAdapterListScreen.listID, 0);

        //start the base
        directory  = getFilesDir();
        appLogic = new AppLogic(directory);
        listaZakupow = appLogic.getShoppingListBase().getItem(shoppingListID);


        //first get all references
        productName = (TextView) findViewById(R.id.textViewProductOnListName);
        productAmount = (EditText) findViewById(R.id.editDecimalAmount);
        back = (Button) findViewById(R.id.buttonBackToList);

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
                double d = 0;

                try{
                    d =  Double.parseDouble(productAmount.getText().toString());
                }catch (Exception e){

                }

                listaZakupow.getItem(productOnListID).setAmount(d);
                System.out.println("NOWA WARTOŚĆ: "+listaZakupow.getItem(productOnListID).getAmount()+", "+listaZakupow.getItem(productOnListID).getDescription());
                appLogic.save();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToShoppingList();
            }
        });





    }

    private void backToShoppingList() {
        Intent intent = new Intent(this, EkranListy.class);
        startActivity(intent);
    }
}