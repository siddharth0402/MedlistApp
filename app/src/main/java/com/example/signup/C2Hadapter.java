package com.example.signup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signup.ModelH.C2HM;

import java.util.ArrayList;
import java.util.List;

public class C2Hadapter extends RecyclerView.Adapter<com.example.signup.C2Hadapter.ViewHolder> implements Filterable {

    private final Context context;
    private final List<C2HM> modelPersonList;
    private List<C2HM> doctorsListAll;

    public C2Hadapter(List<C2HM> modelPersonList, Context context) {
        this.modelPersonList = modelPersonList;
        this.context = context;
        doctorsListAll = new ArrayList<>(modelPersonList);
    }

    @NonNull
    @Override
    public com.example.signup.C2Hadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recorditem, parent, false);
        return new com.example.signup.C2Hadapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.signup.C2Hadapter.ViewHolder holder, int position) {

        C2HM modelPerson = modelPersonList.get(position);
        holder.tvName.setText(modelPerson.getHosc());
    }

    @Override
    public int getItemCount() {
        return modelPersonList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {

// runs on background thread

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {


            List<C2HM> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(doctorsListAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (C2HM item : doctorsListAll) {
                    if (item.getHosc().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        // runs on ui thread
        @Override
        protected void publishResults(CharSequence constraints, FilterResults results) {
            modelPersonList.clear();
            modelPersonList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.aspName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            C2HM modelPerson = modelPersonList.get(getAdapterPosition());
        Intent intent = new Intent(context,FinalHospital.class);
        intent.putExtra("Hospital",modelPerson.getId());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
            Toast.makeText(context, modelPerson.getHosc(), Toast.LENGTH_SHORT).show();
        }
    }
}

