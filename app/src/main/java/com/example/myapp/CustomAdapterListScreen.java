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

import java.io.File;

//package com.example.ryclerview;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.logic.AppLogic;
import com.example.myapp.logic.ListaZakupow;

public class CustomAdapterListScreen extends RecyclerView.Adapter<CustomAdapterListScreen.CustomAdapterViewHolder> {
    //ArrayList<String> data;

    ListaZakupow data;
    AppLogic logic;
    private int shoppingListIndex;
    public static final String productID = "XD";
    public static final String listID = "COO";
    public static final String logicID = "logic";

//TODO: DOPASOWAĆ!

    Context context;
    public CustomAdapterListScreen(int shoppingListIndex, Context context){
        this.shoppingListIndex = shoppingListIndex;
        File directory  = context.getFilesDir();
        this.logic = new AppLogic(directory);
        ListaZakupow listaZakupow = this.logic.getShoppingListBase().getItem(shoppingListIndex);
        this.data = listaZakupow;
        this.context = context;
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
                openListActivity();
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

    private void openListActivity() {
        Intent intent =new Intent(context, EkranListy.class);
        intent.putExtra(CustomAdapterShoppingListsScreen.recipeID, shoppingListIndex);
        context.startActivity(intent);
    }

    private void openProductOnListActivity(int position) {
        Intent intent = new Intent(context, EkranProduktuNaLiscie.class);
        intent.putExtra(productID, position);
        intent.putExtra(listID, shoppingListIndex);
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
            textView = itemView.findViewById(R.id.tvAddRecipeName);
            unit = itemView.findViewById(R.id.textViewItemUnit);
            amount = itemView.findViewById(R.id.textViewItemCount);
            checkBox = itemView.findViewById(R.id.checkBoxItem);
            //switchSelected = itemView.findViewById(R.id.swChecked);
        }
    }
}
