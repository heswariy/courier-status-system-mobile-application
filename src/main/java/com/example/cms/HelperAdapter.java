package com.example.cms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter<HelperAdapter.ViewHolder> {

    ArrayList<String> trackingNum;
    Context context;

    public HelperAdapter(ArrayList<String> trackingNum, Context context) {
        this.trackingNum = trackingNum;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.trackingNum.setText(trackingNum.get(position));
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item Clicked..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return trackingNum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView trackingNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            trackingNum = (TextView) itemView.findViewById(R.id.trackingNum);
        }
    }
}
