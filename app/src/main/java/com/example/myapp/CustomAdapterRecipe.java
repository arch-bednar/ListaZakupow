package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

//package com.example.ryclerview;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.logic.AppLogic;
import com.example.myapp.logic.Przepis;

import java.io.File;

public class CustomAdapterRecipe extends RecyclerView.Adapter<CustomAdapterRecipe.CustomAdapterViewHolder> {
    //ArrayList<String> data;
    Przepis data;
    AppLogic logic;
    public static final String recipeID = "XD";
    private int recipeIndex;

    Context context;
    public CustomAdapterRecipe(Przepis data, Context context, int recipeIndex){
        this.data = data;
        this.context = context;
        this.recipeIndex = recipeIndex;

        File directory  = context.getFilesDir();
        this.logic = new AppLogic(directory);
    }

    @Override
    public CustomAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_recipe,parent,false);
        return new CustomAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterViewHolder holder, final int position) {
        //holder.textView.setText(data.get(position));
        holder.textView.setText(data.getItemName(position));
        holder.amount.setText(""+ data.getItem(position).getAmount());
        holder.unit.setText(data.getItem(position).getUnit());
        holder.checkBox.setChecked(data.getItem(position).isActivated());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,data.get(position),Toast.LENGTH_SHORT).show();
                //Toast.makeText(context,"XDDDD: "+data.getItemName(position),Toast.LENGTH_SHORT).show();
                openRecipeProduct(position);
            }
        });

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Context contextLocal = buttonView.getContext();
                if (isChecked) {
                    //Toast.makeText(contextLocal, data.get(position)+", ID: "+position, Toast.LENGTH_LONG).show(); //position to index RecycleView a nie samej listy
                    //Toast.makeText(contextLocal, data.getItem(position).getDescription()+", ID: "+position, Toast.LENGTH_LONG).show();
                    //data.removeElement(position);
                    logic.getRecipesBase().getItem(recipeIndex).removeElement(position);

                }

                System.out.println("Usunieto element w przepisie");
                logic.save();
                openRecipeActivity();
            }
        });

    }
    private void openRecipeActivity() {
        Intent intent =new Intent(context, EkranListy.class);
        intent.putExtra(CustomAdapterShoppingListsScreen.recipeID, recipeIndex);
        context.startActivity(intent);
    }
    private void openRecipeProduct(int index) {
        //Intent intent = new Intent(context ,EkranPrzepisu.class);
        //intent.putExtra(recipeID, index);
        //context.startActivity(intent);
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
            textView = itemView.findViewById(R.id.textViewAPRName);
            unit = itemView.findViewById(R.id.textViewItemUnit2);
            amount = itemView.findViewById(R.id.textViewItemCount2);
            checkBox = itemView.findViewById(R.id.RecProdDel);
        }
    }
}
