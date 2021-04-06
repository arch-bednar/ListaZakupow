package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapp.logic.AppLogic;
import com.example.myapp.logic.BazaListZakupow;
import com.example.myapp.logic.BazaPrzepisow;
import com.example.myapp.logic.ListaZakupow;
import com.example.myapp.logic.Przepis;

import java.io.File;

public class DodajLista extends AppCompatActivity {
    Button back, save;
    EditText opisListy1;
    public AppLogic logic;
    private File directory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodaj_lista);
        back = (Button) findViewById(R.id.backLista);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLista();
            }
        });

        save = (Button) findViewById(R.id.saveNewLista);
        save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                saveNewLista();
            }
        });



        directory  = getFilesDir();
        logic = new AppLogic(directory);
        opisListy1 = (EditText) findViewById(R.id.opisListy);

    }

    private void saveNewLista() {
        BazaListZakupow bP = logic.getShoppingListBase();
        System.out.println("#####################################################\n              DODANO LISTE!\n     ");
        System.out.println(opisListy1.getText() );

        bP.addToList(new ListaZakupow(1, opisListy1.getText().toString()));



        logic.save();
        backToLista();
    }

    private void backToLista() {
        Intent intent = new Intent(this, EkranListZakupow.class);
        startActivity(intent);
    }
}