package com.erenalparslan.apijava;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RowHolder> {

    private ArrayList<CyriptoModel> cyriptoList;
    private String[] colors = {"#003f5c", "#2f4b7c", "#665191", "#a05195", "#d45087", "#f95d6a", "#ff7c43", "#ffa600", "#003f5c"};

    public RecyclerAdapter(ArrayList<CyriptoModel> cyriptoList) {
        this.cyriptoList = cyriptoList;

    }


    @NonNull
    @Override
    public RecyclerAdapter.RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view, parent, false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RowHolder holder, int position) {
        holder.bind(cyriptoList.get(position), colors, position);
    }

    @Override
    public int getItemCount() {
        return cyriptoList.size();
    }


    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textPrice;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(CyriptoModel cyriptoModel, String[] colors, Integer position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position % 9]));
            textName = itemView.findViewById(R.id.currency);
            textPrice = itemView.findViewById(R.id.price);
            textName.setText(cyriptoModel.currency);
            textPrice.setText(cyriptoModel.price);
        }


    }


}
