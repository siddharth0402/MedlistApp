package com.example.signup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class bedsAdapter extends RecyclerView.Adapter<bedsAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<bedsItem> mExampleList;
    private List<bedsItem> doctorsListAll;

    public bedsAdapter(Context context, ArrayList<bedsItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
        doctorsListAll = new ArrayList<>(exampleList);
    }

    @NonNull
    @androidx.annotation.NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull @androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.beds_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @androidx.annotation.NonNull ExampleViewHolder holder, int position) {
        bedsItem currentItem = mExampleList.get(position);

        // Log.d("EXAMPLE", currentItem.getState());

        String state = currentItem.getState();
        int ruralHospital = currentItem.getRuralHospital();
        int ruralBed = currentItem.getRuralBed();
        String urbanHospital = currentItem.getUrbanHospital();
        int urbanBed = currentItem.getUrbanBed();
        int totalHospital = currentItem.getTotalHospital();

        holder.mTextViewStates.setText("Hospital name : " + state);

        holder.mTextViewRuralHospital.setText("Number of beds : " + ruralHospital);
        holder.mTextViewRuralBed.setText("BEds available : " + ruralBed);
        holder.mTextViewUrbanHospital.setText("Rating  : " + urbanHospital);
        holder.mTextViewUrbanBed.setText("hospital price  : " + urbanBed);
        holder.mTextViewTotalHospital.setText("Government hospital price : " + totalHospital);


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {

        // runs on background thread

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<bedsItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(doctorsListAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (bedsItem item : doctorsListAll) {
                    if (item.getState().toLowerCase().contains(filterPattern)) {
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
            mExampleList.clear();
            mExampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewStates;
        public TextView mTextViewUrbanHospital;
        public TextView mTextViewUrbanBed;
        public TextView mTextViewRuralHospital;
        public TextView mTextViewRuralBed;
        public TextView mTextViewTotalHospital;
        public TextView mTextViewTotalBed;

        public ExampleViewHolder(@NonNull @androidx.annotation.NonNull View itemView) {
            super(itemView);
            mTextViewStates = itemView.findViewById(R.id.State_name);
            mTextViewRuralHospital = itemView.findViewById(R.id.rural_hospital_name);
            mTextViewRuralBed = itemView.findViewById(R.id.rural_bed_name);
            mTextViewUrbanHospital = itemView.findViewById(R.id.urabn_hospital_name);
            mTextViewUrbanBed = itemView.findViewById(R.id.urban_bed_name);
            mTextViewTotalHospital = itemView.findViewById(R.id.total_hospital_name);
//            mTextViewTotalBed = itemView.findViewById(R.id.total_bed_name);
        }
    }
}
