package com.example.signup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ComplainAdapter extends RecyclerView.Adapter<ComplainAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserComplain> list;

    public ComplainAdapter(Context context, ArrayList<UserComplain> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ComplainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.complainitem, parent, false);
        return new ComplainAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplainAdapter.MyViewHolder holder, int position) {
        UserComplain user = list.get(position);
        holder.date.setText(user.getDate());
        holder.doc_name.setText(user.getDocname());
        holder.hos_name.setText(user.getHosname());
        holder.issue.setText(user.getIssue());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, doc_name, hos_name, issue;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.DATE);
            doc_name = itemView.findViewById(R.id.DOCTORNAME);
            hos_name = itemView.findViewById(R.id.HOSNAME);
            issue = itemView.findViewById(R.id.ISSUE);

        }
    }
}