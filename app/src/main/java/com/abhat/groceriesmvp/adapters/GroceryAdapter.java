package com.abhat.groceriesmvp.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhat.groceriesmvp.R;

import java.util.List;

/**
 * Created by cumulations on 12/4/17.
 */

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.ViewHolder> {

    private Context mContext;
    private List mGroceryList;
    private List mGroceryPriceList;

    public GroceryAdapter(Context context, List groceryList, List groceryPriceList) {
        this.mContext = context;
        this.mGroceryList = groceryList;
        this.mGroceryPriceList = groceryPriceList;
    }

    public void setLists(List groceries, List groceryPrices) {
        this.mGroceryList = groceries;
        this.mGroceryPriceList = groceryPrices;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View deviceView = inflater.inflate(R.layout.recyclerview_single_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(deviceView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView groceryName = holder.mGroceryName;
        TextView groceryPrice = holder.mGroceryPrice;
        CardView cardView = holder.mCardView;
        groceryName.setText(mGroceryList.get(position).toString());
        groceryPrice.setText(mGroceryPriceList.get(position).toString());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mGroceryList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mGroceryName;
        public TextView mGroceryPrice;
        public CardView mCardView;
        public ViewHolder(View itemView) {
            super(itemView);
            mGroceryName = (TextView)itemView.findViewById(R.id.groceryName);
            mGroceryPrice = (TextView)itemView.findViewById(R.id.groceryPrice);
            mCardView = (CardView)itemView.findViewById(R.id.cardView);
        }
    }




}
