package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;

//package com.example.ryclerview;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.logic.AppLogic;
import com.example.myapp.logic.BazaProduktow;
import com.example.myapp.logic.ProduktNaLiscie;

public class CustomAdapterAP extends RecyclerView.Adapter<CustomAdapterAP.CustomAdapterViewHolder> {
    BazaProduktow data;
    AppLogic logic;
    private int shoppingListIndex;
    public static final String productID = "XD";
    public static final String listID = "COO";
    public static final String logicID = "logic";

    Context context;

    public CustomAdapterAP(int shoppingListIndex, Context context){
        this.shoppingListIndex = shoppingListIndex;

        File directory  = context.getFilesDir();
        this.logic = new AppLogic(directory);

        this.data = this.logic.getProductBase();
        this.context = context;
    }

    @Override
    public CustomAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_ap,parent,false);
        return new CustomAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapterViewHolder holder, final int position) {
        
        holder.productNameTV.setText(data.getItemName(position));
        holder.productAmountTV.setText(data.getItem(position).getUnit());


        holder.addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct(position, holder);
            }
        });





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Produkt: "+data.getItemName(position),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void addProduct(int position, @NonNull final CustomAdapterViewHolder holder) {
        double d=0;

        try{
            d = Double.parseDouble(holder.productAmountET.getText().toString());
        }catch (Exception e){
            System.out.println("To tu też damy zero skoro takie obyczaje!");
        }

        if(d>0){
            Toast.makeText(context,data.getItemName(position)+" "+d,Toast.LENGTH_SHORT).show();
            ProduktNaLiscie produktNaLiscie = new ProduktNaLiscie(data.getItem(position), d);
            logic.getShoppingListBase().getItem(shoppingListIndex).addToList(produktNaLiscie);
            logic.save();

            Intent intent = new Intent(context, EkranListy.class);
            intent.putExtra(CustomAdapterRecipesList.recipeID ,shoppingListIndex);
            context.startActivity(intent);

        }


    }


    @Override
    public int getItemCount() {
        return data.getItemCount();
    }

    public static class CustomAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView productNameTV;
        public TextView productAmountTV;
        public Button addProductButton;
        public EditText productAmountET;


        public CustomAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTV = itemView.findViewById(R.id.textViewAPRName);
            addProductButton = itemView.findViewById(R.id.buttonAddAPR);
            productAmountTV = itemView.findViewById(R.id.textViewAPRUnit);
            productAmountET = itemView.findViewById(R.id.editTExtAPRAmount);

        }
    }
}
