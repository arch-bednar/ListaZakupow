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
import com.example.myapp.logic.Przepis;

import java.io.File;

public class EkranPrzepisu extends AppCompatActivity {
    private int recipeIndex;
    public AppLogic logic;
    private File directory;
    private Przepis przepis;
    private TextView textViewNazwaPrzepisu;
    private Button backToRecipesButton, addProductToRecipeButton;
    private RecyclerView recycle;
    private RecyclerView.LayoutManager layoutManager;
    private CustomAdapterRecipe adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_przepisu);

        //odbieranie numeru indeksu przepisu z listy
        Intent intent = getIntent();
        recipeIndex = (int) intent.getIntExtra(CustomAdapterRecipesList.recipeID, 0);

        //otwieranie bazy danych
        directory  = getFilesDir();
        logic = new AppLogic(directory);

        //przepis
        przepis = logic.getRecipesBase().getItem(recipeIndex);

        //otwieranie komunikacji z interfacem
        textViewNazwaPrzepisu = findViewById(R.id.nazwaPrzepisu);
        backToRecipesButton = findViewById(R.id.backToRecipesList);
        addProductToRecipeButton = findViewById(R.id.addRecipeProduct);
        recycle = findViewById(R.id.recyclerOneRecipe);

        //obsługa interfejsu
        textViewNazwaPrzepisu.setText(przepis.getName());
        backToRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecipesListActivity();
            }
        });




        //W tym RecyclerView
        recycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CustomAdapterRecipe(przepis,this, recipeIndex);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(adapter);


    }

    private void openRecipesListActivity() {
        Intent intent = new Intent(this, EkranPrzepisow.class);
        startActivity(intent);
    }
}