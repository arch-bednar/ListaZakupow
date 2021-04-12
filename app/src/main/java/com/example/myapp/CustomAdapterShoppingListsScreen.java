package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;

//package com.example.ryclerview;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.logic.BazaListZakupow;

public class CustomAdapterShoppingListsScreen extends RecyclerView.Adapter<CustomAdapterShoppingListsScreen.CustomAdapterViewHolder> {
    //ArrayList<String> data;

    BazaListZakupow data;
    public static final String recipeID = "XD";


    Context context;
    public CustomAdapterShoppingListsScreen(BazaListZakupow data, Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public CustomAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_shopping_lists,parent,false);
        return new CustomAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterViewHolder holder, final int position) {

        holder.textView.setText(data.getItem(position).getDescription());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openShoppingListActivity(position);
            }
        });
    }


    private void openShoppingListActivity(int index){
        Intent intent = new Intent(context, EkranListy.class);
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
            textView = itemView.findViewById(R.id.textViewAPRName);

        }
    }
}
