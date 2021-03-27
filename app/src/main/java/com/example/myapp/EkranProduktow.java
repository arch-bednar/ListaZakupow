package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class EkranProduktow extends AppCompatActivity {
    private Button back, add;
    private RecyclerView recyclexd;
    private LinearLayoutManager layoutManager;
    private CustomAdapter adapter;
    //int a;


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


        recyclexd = (RecyclerView) findViewById(R.id.recycleXD);
        ArrayList<String> data = new ArrayList<>();
        data.add("raz");
        data.add("dwa");
        data.add("trzy");
        data.add("cztery");
        data.add("piec");
        data.add("szesc");


        recyclexd.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CustomAdapter(data,this);
        recyclexd.setLayoutManager(layoutManager);
        recyclexd.setAdapter(adapter);
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