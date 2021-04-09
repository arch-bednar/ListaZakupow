package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapp.logic.AppLogic;
import com.example.myapp.logic.ListaZakupow;

import java.io.File;

public class EkranDodawaniaProduktu extends AppCompatActivity {
    public AppLogic logic;
    private File directory;

    private int shoppingListIndex;
    private ListaZakupow listaZakupow;

    private Button back;
    private TextView naglowek;
    private RecyclerView recycle;
    private RecyclerView.LayoutManager layoutManager;
    private CustomAdapterAP adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_dodawania_produktu);

        Intent intent = getIntent();
        shoppingListIndex = (int) intent.getIntExtra(CustomAdapterShoppingListsScreen.recipeID, 0);

        //load the base
        directory  = getFilesDir();
        logic = new AppLogic(directory);
        listaZakupow = logic.getShoppingListBase().getItem(shoppingListIndex);

        //get references
        back = (Button) findViewById(R.id.buttonBackToShoppingListAP);
        naglowek = (TextView) findViewById(R.id.textViewAP);
        recycle = (RecyclerView) findViewById(R.id.recyclerAP);


        //add listeners
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToShoppingList();
            }
        });

        //set title
        naglowek.setText("Dodaj produkt do listy: " + listaZakupow.getDescription());

        //handle the recycler view
        recycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        adapter = new CustomAdapterAP(shoppingListIndex,this);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(adapter);


    }

    private void backToShoppingList() {
        Intent intent = new Intent(this, EkranListy.class);
        intent.putExtra(CustomAdapterShoppingListsScreen.recipeID, shoppingListIndex);
        startActivity(intent);
    }
}