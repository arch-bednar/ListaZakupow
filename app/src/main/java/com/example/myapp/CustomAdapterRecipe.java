package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

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
    public static final String recipeItemID = "recipeItemID";
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

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Context contextLocal = buttonView.getContext();
                if (isChecked) {

                    logic.getRecipesBase().getItem(recipeIndex).removeElement(position);

                }

                System.out.println("Usunieto element w przepisie");
                logic.save();
                openRecipeActivity();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editRecipeItem(position);
            }
        });

    }

    private void editRecipeItem(int itemIndex) {
        String przepis = logic.getRecipesBase().getItem(recipeIndex).getDescription();
        String produkt = logic.getRecipesBase().getItem(recipeIndex).getItem(itemIndex).getName();
        Toast.makeText(context, przepis+": "+produkt, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(context, EkranPrzepisuEdycjaProduktu.class);
        intent.putExtra(CustomAdapterRecipesList.recipeID, recipeIndex);
        intent.putExtra(CustomAdapterRecipe.recipeItemID, itemIndex);
        context.startActivity(intent);
    }

    private void openRecipeActivity() {
        Intent intent =new Intent(context, EkranPrzepisu.class);
        intent.putExtra(CustomAdapterRecipesList.recipeID, recipeIndex);
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
            textView = itemView.findViewById(R.id.textViewAPRName);
            unit = itemView.findViewById(R.id.textViewItemUnit2);
            amount = itemView.findViewById(R.id.textViewItemCount2);
            checkBox = itemView.findViewById(R.id.RecProdDel);
        }
    }
}
