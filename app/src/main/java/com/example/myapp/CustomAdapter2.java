package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.logic.BazaProduktow;


public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.CustomAdapterViewHolder> {
    //ArrayList<String> data;

    BazaProduktow data;

    Context context;
    public CustomAdapter2(BazaProduktow data, Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public CustomAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_2,parent,false);
        return new CustomAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterViewHolder holder, final int position) {
        //holder.textView.setText(data.get(position));
        holder.textView.setText(data.getItemName(position));

        /*
        holder.switchSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                context = compoundButton.getContext();
                if (bChecked) {
                    //Toast.makeText(context, data.get(position)+", ID: "+position, Toast.LENGTH_LONG).show(); //position to index RecycleView a nie samej listy
                    Toast.makeText(context, data.getItemName(position)+", ID: "+position, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, data.getItemName(position)+"Unchecked", Toast.LENGTH_LONG).show();
                }
            }
        }); */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,data.get(position),Toast.LENGTH_SHORT).show();
                Toast.makeText(context,data.getItemName(position),Toast.LENGTH_SHORT).show();
            }
        });
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
            textView = itemView.findViewById(R.id.textViewAPRName);
            //switchSelected = itemView.findViewById(R.id.swChecked);
        }
    }
}
