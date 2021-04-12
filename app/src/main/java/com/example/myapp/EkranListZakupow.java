package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapp.logic.AppLogic;
import com.example.myapp.logic.BazaListZakupow;

import java.io.File;

public class EkranListZakupow extends AppCompatActivity {
    private Button back,add;
    private RecyclerView recycle;
    private RecyclerView.LayoutManager layoutManager;
    private CustomAdapterShoppingListsScreen adapter;
    public AppLogic logic;
    private File directory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_list_zakupow);
        back = (Button) findViewById(R.id.backToMain);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                backToMain();
            }
        });

        add = (Button) findViewById(R.id.addLista);

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNewLista();
            }
        });


        directory  = getFilesDir();
        logic = new AppLogic(directory);
        System.out.println("Ekran List");
        logic.getShoppingListBase().printOut();

        BazaListZakupow data = logic.getShoppingListBase();

        recycle = (RecyclerView) findViewById(R.id.recycleList);
        recycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CustomAdapterShoppingListsScreen(data,this);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(adapter);
    }

    private void backToMain() {
        Intent intent = new Intent(this, EkranGlowny.class);
        startActivity(intent);
    }
    private void addNewLista() {
        Intent intent = new Intent(this, DodajLista.class);
        startActivity(intent);
    }
}