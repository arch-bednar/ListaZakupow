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

import java.io.File;

public class EkranPrzepisuEdycjaProduktu extends AppCompatActivity {
    private int recipeID, recipeItemID;
    private File directory;
    private AppLogic appLogic;

    private TextView tytul, tvIlosc;
    private EditText etIlosc;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_przepisu_edycja_produktu);

        //get ID
        Intent intent = getIntent();
        recipeID = (int) intent.getIntExtra(CustomAdapterRecipesList.recipeID, 0);
        recipeItemID = (int) intent.getIntExtra(CustomAdapterRecipe.recipeItemID, 0);

        //start the base
        directory  = getFilesDir();
        appLogic = new AppLogic(directory);

        //get references
        tytul = (TextView) findViewById(R.id.tvEPEP);
        back = (Button) findViewById(R.id.buttonBackToRecipeEP);
        tvIlosc = (TextView) findViewById(R.id.textViewREPAmount);
        etIlosc = (EditText) findViewById(R.id.editTextREPAmount);

        //set the title
        String przepis = appLogic.getRecipesBase().getItem(recipeID).getDescription();
        String produkt = appLogic.getRecipesBase().getItem(recipeID).getItem(recipeItemID).getName();
        tytul.setText(przepis+": "+produkt);

        tvIlosc.setText("Ilość:");
        etIlosc.setText(""+appLogic.getRecipesBase().getItem(recipeID).getItem(recipeItemID).getAmount(), TextView.BufferType.EDITABLE);

        etIlosc.addTextChangedListener(new TextWatcher() {
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
                    d =  Double.parseDouble(etIlosc.getText().toString());
                }catch (Exception e){

                }

                appLogic.getRecipesBase().getItem(recipeID).getItem(recipeItemID).setAmount(d);
                appLogic.save();
            }
        });

        //bind the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToRecipe();
            }
        });


    }

    private void backToRecipe() {
        Intent intent = new Intent(this, EkranPrzepisu.class);
        intent.putExtra(CustomAdapterRecipesList.recipeID,recipeID);
        startActivity(intent);
    }
}