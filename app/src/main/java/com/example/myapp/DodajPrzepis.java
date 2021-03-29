package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapp.logic.AppLogic;
import com.example.myapp.logic.BazaPrzepisow;
import com.example.myapp.logic.Przepis;

import java.io.File;

public class DodajPrzepis extends AppCompatActivity {
    Button back, save;
    EditText nazwaPrzepisu1,opisPrzepisu1,numer;
    public AppLogic logic;
    private File directory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodaj_przepis);
        back = (Button) findViewById(R.id.backPrzepis);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToPrzepis();
            }
        });

        save = (Button) findViewById(R.id.saveNewPrzepis);
        save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                saveNewPrzepis();
            }
        });



        directory  = getFilesDir();
        logic = new AppLogic(directory);
        nazwaPrzepisu1 = (EditText) findViewById(R.id.nazwaPrzepisu);
        opisPrzepisu1 = (EditText) findViewById(R.id.opisPrzepisu);
        numer = (EditText) findViewById(R.id.editTextNumber);
    }

    private void saveNewPrzepis() {
        BazaPrzepisow bP = logic.getRecipesBase();
        System.out.println("#####################################################\n              DODANO PRZEPIS!\n     ");
        System.out.println(nazwaPrzepisu1.getText()+"  "+opisPrzepisu1.getText() );

        bP.addToList(new Przepis(1, nazwaPrzepisu1.getText().toString(), opisPrzepisu1.getText().toString()));



        logic.save();
        backToPrzepis();
    }

    private void backToPrzepis() {
        Intent intent = new Intent(this, EkranPrzepisow.class);
        startActivity(intent);
    }
}