package com.example.myapp;
import com.example.myapp.logic.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

public class EkranProduktow extends AppCompatActivity {
    private Button back, add;
    private RecyclerView recyclexd;
    private LinearLayoutManager layoutManager;
    private CustomAdapter2 adapter;
    public AppLogic logic;
    private File directory;

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



        directory  = getFilesDir();
        logic = new AppLogic(directory);
        System.out.println("Ekran Produktow");
        logic.getProductBase().printOut();

        BazaProduktow data = logic.getProductBase();



        recyclexd.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CustomAdapter2(data,this);
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