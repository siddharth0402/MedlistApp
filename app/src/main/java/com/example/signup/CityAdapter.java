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

import com.example.signup.ModelH.StractCity;

import java.util.ArrayList;
import java.util.List;


public class CityAdapter extends RecyclerView.Adapter<com.example.signup.CityAdapter.ViewHolder> implements Filterable {

    private final Context context;
    private final List<StractCity> modelPersonList;
    private List<StractCity> doctorsListAll;

    public CityAdapter(List<StractCity> modelPersonList, Context context) {
        this.modelPersonList = modelPersonList;
        this.context = context;
        doctorsListAll = new ArrayList<>(modelPersonList);
    }

    @NonNull
    @Override
    public com.example.signup.CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cityitem, parent, false);
        return new com.example.signup.CityAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.signup.CityAdapter.ViewHolder holder, int position) {

        StractCity modelPerson = modelPersonList.get(position);
        holder.tvName.setText(modelPerson.getCity());
        //holder.tvEmail.setText(modelPerson.getPersonEmail());
        //Picasso.get().load(modelPerson.getPersonProfile()).into(holder.ivPersonImage);

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

//            List<StractHospital> filteredList = new ArrayList<>();
//            if ( charSequence.toString().isEmpty()){
//                filteredList.addAll(doctorsListAll);
//            }else {
//                for (StractHospital modelPersonList: doctorsListAll){
//                    if(modelPersonList.toString().toLowerCase().contains(charSequence.toString().toLowerCase())){
//                        filteredList.add(modelPersonList);
//                    }
//                }
//            }
//
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = filteredList;

            List<StractCity> filteredList = new ArrayList<>();

            if ( constraint == null || constraint.length() == 0){
                filteredList.addAll(doctorsListAll);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (StractCity item : doctorsListAll){
                    if ( item.getCity().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return  results;
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

            tvName = itemView.findViewById(R.id.aspCity);
            //tvEmail = itemView.findViewById(R.id.tvEmail);
            //ivPersonImage = itemView.findViewById(R.id.ivPersonImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            StractCity modelPerson = modelPersonList.get(getAdapterPosition());
            Intent intent = new Intent(context,CityToHospital.class);
            intent.putExtra("City",modelPerson.getCity());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Toast.makeText(context, modelPerson.getCity(), Toast.LENGTH_SHORT).show();
        }
    }
}

