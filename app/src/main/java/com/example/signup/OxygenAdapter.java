package com.example.signup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OxygenAdapter extends RecyclerView.Adapter<OxygenAdapter.MyViewHolder> {
    Context context;
    ArrayList<OXYUSER> list;

    public OxygenAdapter(Context context, ArrayList<OXYUSER> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OxygenAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.oxyitem,parent,false);
        return new OxygenAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OxygenAdapter.MyViewHolder holder, int position) {
        OXYUSER user = list.get(position);
        holder.date.setText(user.getDate());
        holder.oxygen.setText(user.getOxygen());
        holder.notes.setText(user.getNotes());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date, oxygen, notes;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.DATE);
            oxygen = itemView.findViewById(R.id.OxyLvl);
            notes = itemView.findViewById( R.id.NOTES);
        }
    }
}