package com.example.signup.adapter;

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

import com.example.signup.FinalHospital;
import com.example.signup.ModelH.StractHospital;
import com.example.signup.R;

import java.util.ArrayList;
import java.util.List;

//public class HospitalAdapter extends ArrayAdapter<StractHospital>
//
//public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {
//    public HospitalAdapter(ArrayList<StractHospital> array) {
//        super(G.context, R.layout.recorditem, array);
//    }
//
//    public static class ViewHolder {
//        public TextView txthname;
//
//
//        public ViewHolder(View view) {
//            txthname = view.findViewById(R.id.aspName);
//
//        }
//
//        public void fill(final ArrayAdapter<StractHospital> adapter, final StractHospital item, final int position) {
//            txthname.setText(item.getHospitalName());
//
//
//        }
//    }
//
//    @Override
//    public View getView(int position, View convertview, ViewGroup parent) {
//        ViewHolder holder;
//        StractHospital item = getItem(position);
//        if (convertview == null) {
//            convertview = G.inflater.inflate(R.layout.recorditem, parent, false);
//            holder = new ViewHolder(convertview);
//            convertview.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertview.getTag();
//        }
//        holder.fill(this, item, position);
//        return convertview;
//
//    }
//}
public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> implements Filterable {

    private final Context context;
    private final List<StractHospital> modelPersonList;
    private List<StractHospital> doctorsListAll;

    public HospitalAdapter(List<StractHospital> modelPersonList, Context context) {
        this.modelPersonList = modelPersonList;
        this.context = context;
        doctorsListAll = new ArrayList<>(modelPersonList);
    }

    @NonNull
    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recorditem, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.ViewHolder holder, int position) {

        StractHospital modelPerson = modelPersonList.get(position);
        holder.tvName.setText(modelPerson.getHospitalName());
       // holder.id.setText(modelPerson.getState());
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

            List<StractHospital> filteredList = new ArrayList<>();

            if ( constraint == null || constraint.length() == 0){
                filteredList.addAll(doctorsListAll);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (StractHospital item : doctorsListAll){
                    if ( item.getHospitalName().toLowerCase().contains(filterPattern)){
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

        private final TextView tvName; //,id;
        //  private final TextView tvEmail;
        // private final ImageView ivPersonImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.aspName);
          //  id = itemView.findViewById(R.id.ID);
            //tvEmail = itemView.findViewById(R.id.tvEmail);
            //ivPersonImage = itemView.findViewById(R.id.ivPersonImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            StractHospital modelPerson = modelPersonList.get(getAdapterPosition());
            Intent intent = new Intent(context, FinalHospital.class);
            intent.putExtra("Hospital",modelPerson.getState());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Toast.makeText(context, modelPerson.getHospitalName(), Toast.LENGTH_SHORT).show();
        }
    }
}

