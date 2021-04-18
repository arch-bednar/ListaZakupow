package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.logic.AppLogic;
import com.example.myapp.logic.BazaListZakupow;
import com.example.myapp.logic.BazaProduktow;
import com.example.myapp.logic.BazaPrzepisow;
import com.example.myapp.logic.ListaZakupow;

import java.io.File;


public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.CustomAdapterViewHolder> {
    //ArrayList<String> data;

    BazaProduktow data;
    AppLogic logic;
    Context context;
    public CustomAdapter2(BazaProduktow data, Context context){
        this.data = data;
        this.context = context;
        File directory  = context.getFilesDir();
        this.logic = new AppLogic(directory);
        this.context = context;
    }

    @Override
    public CustomAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_2,parent,false);
        return new CustomAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterViewHolder holder, final int position) {

        holder.textView.setText(data.getItemName(position));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,data.get(position),Toast.LENGTH_SHORT).show();
                Toast.makeText(context,data.getItemName(position),Toast.LENGTH_SHORT).show();
            }
        });

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                remove(position);
            }
        });
    }

    @Override
    public int getItemCount() {

        return data.getItemCount();
    }

    public static class CustomAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public CheckBox checkBox;


        public CustomAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewAPRName);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
    public void remove(int indeks){

        data.remove(logic,indeks);
        logic.save();
        openListActivity();
    }
    private void openListActivity() {
        Intent intent =new Intent(context, EkranProduktow.class);
        context.startActivity(intent);
    }
}
