package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EkranProduktow extends AppCompatActivity {
    private Button back, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_produktow);
        back = (Button) findViewById(R.id.button2);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                backToMain();
            }
        });

        add = (Button) findViewById(R.id.addProduct);

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNewProduct();
            }
        });
    }

    private void addNewProduct() {
        Intent intent = new Intent(this, DodajProdukt.class);
        startActivity(intent);
    }

    private void backToMain() {
        Intent intent = new Intent(this, EkranGlowny.class);
        startActivity(intent);
    }
}