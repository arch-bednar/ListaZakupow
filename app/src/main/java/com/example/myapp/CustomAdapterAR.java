package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;

//package com.example.ryclerview;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.logic.AppLogic;
import com.example.myapp.logic.BazaPrzepisow;

public class CustomAdapterAR extends RecyclerView.Adapter<CustomAdapterAR.CustomAdapterViewHolder> {
    BazaPrzepisow data;
    AppLogic logic;
    private int shoppingListIndex;
    public static final String productID = "XD";
    public static final String listID = "COO";
    public static final String logicID = "logic";

    Context context;

    public CustomAdapterAR(int shoppingListIndex, Context context){
        this.shoppingListIndex = shoppingListIndex;

        File directory  = context.getFilesDir();
        this.logic = new AppLogic(directory);

        this.data = this.logic.getRecipesBase();
        this.context = context;
    }

    @Override
    public CustomAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_ar,parent,false);
        return new CustomAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterViewHolder holder, final int position) {
        holder.recipeNameTV.setText(data.getItemName(position));
        holder.addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecipe(position);
            }
        });

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


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"Pokaż zawartość przepisu: "+data.getItemName(position),Toast.LENGTH_SHORT).show();

                //openProductOnListActivity(position);
            }
        });
    }

    private void addRecipe(int position) {
        Toast.makeText(context,"Dodaj ten przepis do listy: "+ data.getItemName(position)+", lista: "+logic.getShoppingListBase().getItem(shoppingListIndex).getDescription(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return data.getItemCount();
    }

    public static class CustomAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView recipeNameTV;
        public Button addRecipeButton;

        public CustomAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeNameTV = itemView.findViewById(R.id.tvAddRecipeName);
            addRecipeButton = itemView.findViewById(R.id.buttonAddAR);

        }
    }
}
