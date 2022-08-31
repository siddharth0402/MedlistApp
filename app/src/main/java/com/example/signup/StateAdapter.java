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

import com.example.signup.ModelH.StractState;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<com.example.signup.StateAdapter.ViewHolder> implements Filterable {

    private final Context context;
    private final List<StractState> modelPersonList;
    private List<StractState> doctorsListAll;

    public StateAdapter(List<StractState> modelPersonList, Context context) {
        this.modelPersonList = modelPersonList;
        this.context = context;
        doctorsListAll = new ArrayList<>(modelPersonList);
    }

    @NonNull
    @Override
    public com.example.signup.StateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_state, parent, false);
        return new com.example.signup.StateAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.signup.StateAdapter.ViewHolder holder, int position) {

        StractState modelPerson = modelPersonList.get(position);
        holder.tvName.setText(modelPerson.getState());
        //holder.tvEmail.setText(modelPerson.getPersonEmail());
        //Picasso.get().load(modelPerson.getPersonProfile()).into(holder.ivPersonImage);

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


            List<StractState> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(doctorsListAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (StractState item : doctorsListAll) {
                    if (item.getState().toLowerCase().contains(filterPattern)) {
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
        //  private final TextView tvEmail;
        // private final ImageView ivPersonImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            //tvEmail = itemView.findViewById(R.id.tvEmail);
            //ivPersonImage = itemView.findViewById(R.id.ivPersonImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            StractState modelPerson = modelPersonList.get(getAdapterPosition());
            Intent intent = new Intent(context, StateOn.class);
            intent.putExtra("City", modelPerson.getState());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Toast.makeText(context, modelPerson.getState(), Toast.LENGTH_SHORT).show();
        }
    }
}

