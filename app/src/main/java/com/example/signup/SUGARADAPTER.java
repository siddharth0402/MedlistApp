package com.example.signup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SUGARADAPTER extends RecyclerView.Adapter<SUGARADAPTER.MyViewHolder> {
    Context context;
    ArrayList<SUGARUSER> list;

    public SUGARADAPTER(Context context, ArrayList<SUGARUSER> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SUGARADAPTER.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sugaritem,parent,false);
        return new SUGARADAPTER.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SUGARADAPTER.MyViewHolder holder, int position) {
        SUGARUSER user = list.get(position);
        holder.date.setText(user.getDate());
        holder.bp.setText(user.getSugar());
        holder.notes.setText(user.getNotes());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date, bp, notes;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.DATE);
            bp = itemView.findViewById(R.id.BLOODPR);
            notes = itemView.findViewById( R.id.NOTES);
        }
    }
}
