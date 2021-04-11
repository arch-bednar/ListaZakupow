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
import com.example.myapp.logic.BazaPrzepisow;

import java.io.File;

public class CustomAdapterRecipesList extends RecyclerView.Adapter<CustomAdapterRecipesList.CustomAdapterViewHolder> {
    //ArrayList<String> data;

    BazaPrzepisow data;
    public static final String recipeID = "XD";
    AppLogic logic;

    Context context;
    public CustomAdapterRecipesList(BazaPrzepisow data, Context context){
        this.data = data;
        this.context = context;
        File directory  = context.getFilesDir();
        this.logic = new AppLogic(directory);

    }

    @Override
    public CustomAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_recipe_list,parent,false);
        return new CustomAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterViewHolder holder, final int position) {
        //holder.textView.setText(data.get(position));
        holder.textView.setText(data.getItemName(position));





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,data.get(position),Toast.LENGTH_SHORT).show();
                //Toast.makeText(context,"XDDDD: "+data.getItemName(position),Toast.LENGTH_SHORT).show();
                openRecipeActivity(position);
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
                    logic.getRecipesBase().removeElement(position);
                    System.out.println("Usunieto element");
                }


                logic.save();
                restartScreen();
            }
        });
    }
    private  void restartScreen(){
        Intent intent = new Intent(context , EkranPrzepisow.class);
        context.startActivity(intent);
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
        public CheckBox checkBox;
        public CustomAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView10);
            checkBox = itemView.findViewById(R.id.checkBoxRecDel);

        }
    }
}
