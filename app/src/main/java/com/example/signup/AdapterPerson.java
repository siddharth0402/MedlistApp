package com.example.signup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterPerson extends RecyclerView.Adapter<AdapterPerson.ViewHolder> {

    private final Context context;
    private final List<ModelPerson> modelPersonList;
    private List<ModelPerson> doctorsListAll;

    public AdapterPerson(List<ModelPerson> modelPersonList, Context context) {
        this.modelPersonList = modelPersonList;
        this.context = context;
        doctorsListAll = new ArrayList<>(modelPersonList);
    }

    @NonNull
    @Override
    public AdapterPerson.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_state, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPerson.ViewHolder holder, int position) {

        ModelPerson modelPerson = modelPersonList.get(position);
        holder.tvName.setText(modelPerson.getPersonName());
      //  holder.tvEmail.setText(modelPerson.getPersonEmail());

    }

    @Override
    public int getItemCount() {
        return modelPersonList.size();
    }
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {

        // runs on background thread

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<ModelPerson> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(doctorsListAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ModelPerson item : doctorsListAll) {
                    if (item.getPersonName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            modelPersonList.clear();
            modelPersonList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvName;
       // private final TextView tvEmail;
       // private final ImageView ivPersonImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
          //  tvEmail = itemView.findViewById(R.id.tvEmail);
         //   ivPersonImage = itemView.findViewById(R.id.ivPersonImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ModelPerson modelPerson = modelPersonList.get(getAdapterPosition());
            Toast.makeText(context, modelPerson.getPersonName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,StateOn.class);
            intent.putExtra("Name",modelPerson.getPersonName());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
    }
}