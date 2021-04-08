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

import com.example.myapp.logic.AppLogic;
import com.example.myapp.logic.BazaListZakupow;
import com.example.myapp.logic.BazaProduktow;
import com.example.myapp.logic.BazaPrzepisow;
import com.example.myapp.logic.ListaZakupow;
import com.example.myapp.logic.Produkt;

import java.util.ArrayList;

public class CustomAdapterListScreen extends RecyclerView.Adapter<CustomAdapterListScreen.CustomAdapterViewHolder> {
    //ArrayList<String> data;

    ListaZakupow data;
    AppLogic logic;
    public static final String productID = "XD";
    public static final String listID = "COO";

//TODO: DOPASOWAĆ!

    Context context;
    public CustomAdapterListScreen(ListaZakupow data, Context context, AppLogic logic){
        this.data = data;
        this.context = context;
        this.logic = logic;
    }

    @Override
    public CustomAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_shopping_list,parent,false);
        return new CustomAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterViewHolder holder, final int position) {
        //holder.textView.setText(data.get(position));
        holder.textView.setText(data.getItem(position).getDescription());
        holder.amount.setText(""+ data.getItem(position).getAmount());
        holder.unit.setText(data.getItem(position).getUnit());
        holder.checkBox.setChecked(data.getItem(position).isActivated());

        /*
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
        */

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Context contextLocal = buttonView.getContext();
                if (isChecked) {
                    //Toast.makeText(contextLocal, data.get(position)+", ID: "+position, Toast.LENGTH_LONG).show(); //position to index RecycleView a nie samej listy
                    //Toast.makeText(contextLocal, data.getItem(position).getDescription()+", ID: "+position, Toast.LENGTH_LONG).show();
                    data.getItem(position).setChecked(true);

                } else {
                    //Toast.makeText(contextLocal, data.getItem(position).getDescription()+"Unchecked", Toast.LENGTH_LONG).show();
                    data.getItem(position).setChecked(false);
                }

                System.out.println("\n\n\n "+data.getItem(position).isActivated());
                logic.save();
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,data.get(position),Toast.LENGTH_SHORT).show();
                //Toast.makeText(context,"XDDDD: "+data.getItemName(position),Toast.LENGTH_SHORT).show();

                openProductOnListActivity(position);
            }
        });
    }

    private void openProductOnListActivity(int position) {
        //TODO: EDYCJA ELEMENTÓW Z LISTY ZAKUPÓW
        Intent intent = new Intent(context, EkranProduktuNaLiscie.class);
        intent.putExtra(productID, position);
        intent.putExtra(listID, data);
        context.startActivity(intent);
    }

    @Override

    public int getItemCount() {
        //return data.size();
        return data.getItemCount();
    }

    public static class CustomAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView textView, unit, amount;
        public CheckBox checkBox;


        public CustomAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemtextview);
            unit = itemView.findViewById(R.id.textViewItemUnit);
            amount = itemView.findViewById(R.id.textViewItemCount);
            checkBox = itemView.findViewById(R.id.checkBoxItem);
            //switchSelected = itemView.findViewById(R.id.swChecked);
        }
    }
}
