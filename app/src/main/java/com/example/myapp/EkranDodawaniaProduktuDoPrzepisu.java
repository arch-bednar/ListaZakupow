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

import java.io.File;

public class EkranDodawaniaProduktuDoPrzepisu extends AppCompatActivity {
    private int recipeIndex;
    public AppLogic logic;
    private File directory;

    //elementy interfejsu
    private TextView tytul;
    private Button back;

    //obsługa recyclerView
    private RecyclerView recycle;
    private RecyclerView.LayoutManager layoutManager;
    private CustomAdapterAPToRecipe adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_dodawania_produktu_do_przepisu);

        //otwieranie bazy danych
        directory  = getFilesDir();
        logic = new AppLogic(directory);

        //odbieranie numeru indeksu przepisu z listy
        Intent intent = getIntent();
        recipeIndex = (int) intent.getIntExtra(CustomAdapterRecipesList.recipeID, 0);

        //komunikacja z interfejsem: tworzenie instancji klas
        tytul = (TextView) findViewById(R.id.textViewAPRecipe);
        back = (Button) findViewById(R.id.buttonBackToRecipe);
        recycle = (RecyclerView) findViewById(R.id.recyclerAPRecipe);


        //wypisywanie tytulu
        tytul.setText("HALLO, dodaj produkt do przepisu: "+logic.getRecipesBase().getItem(recipeIndex).getDescription());

        //powrót do danego przepisu
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToRecipe();
            }
        });


        //W tym RecyclerView
        recycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CustomAdapterAPToRecipe(recipeIndex, this);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(adapter);
    }


    private void backToRecipe() {
        Intent intent = new Intent(this, EkranPrzepisu.class);
        intent.putExtra(CustomAdapterRecipesList.recipeID, recipeIndex);
        startActivity(intent);
    }
}