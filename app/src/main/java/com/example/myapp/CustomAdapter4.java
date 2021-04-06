package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

//package com.example.ryclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.logic.BazaListZakupow;
import com.example.myapp.logic.BazaProduktow;
import com.example.myapp.logic.BazaPrzepisow;
import com.example.myapp.logic.Produkt;

import java.util.ArrayList;

public class CustomAdapter4 extends RecyclerView.Adapter<CustomAdapter4.CustomAdapterViewHolder> {
    //ArrayList<String> data;

    BazaListZakupow data;
    public static final String recipeID = "XD";


    Context context;
    public CustomAdapter4(BazaListZakupow data, Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public CustomAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new CustomAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterViewHolder holder, final int position) {
        //holder.textView.setText(data.get(position));
        holder.textView.setText(data.getItem(position).getDescription());

        holder.switchSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                context = compoundButton.getContext();
                if (bChecked) {
                    //Toast.makeText(context, data.get(position)+", ID: "+position, Toast.LENGTH_LONG).show(); //position to index RecycleView a nie samej listy
                    Toast.makeText(context, data.getItem(position).getDescription()+", ID: "+position, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, data.getItem(position).getDescription()+"Unchecked", Toast.LENGTH_LONG).show();
                }
            }
        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,data.get(position),Toast.LENGTH_SHORT).show();
                //Toast.makeText(context,"XDDDD: "+data.getItemName(position),Toast.LENGTH_SHORT).show();
                openRecipeActivity(position);
            }
        });
    }

    private void openRecipeActivity(int index) {
        Intent intent = new Intent(context ,EkranPrzepisu.class);
        intent.putExtra(recipeID, index);
        context.startActivity(intent);
    }

    @Override

    public int getItemCount() {
        //return data.size();
        return data.getItemCount();
    }

    public static class CustomAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public Switch switchSelected;

        public CustomAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemtextview);
            switchSelected = itemView.findViewById(R.id.swChecked);
        }
    }
}
