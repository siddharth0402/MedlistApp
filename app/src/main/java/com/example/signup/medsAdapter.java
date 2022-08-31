package com.example.signup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class medsAdapter extends RecyclerView.Adapter<medsAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<medsItem> mExampleList;
    private List<medsItem> doctorsListAll;

    public medsAdapter(Context context, ArrayList<medsItem> exampleList) {
       this.mContext = context;
        this.mExampleList = exampleList;
        doctorsListAll = new ArrayList<>(exampleList);
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meds_item, parent, false);
        return new ExampleViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull ExampleViewHolder holder, int position) {
        medsItem currentItem = mExampleList.get(position);

        // Log.d("EXAMPLE", currentItem.getState());

        String medicine = currentItem.getMedicine();
        String price = currentItem.getPrice();
        String use = currentItem.getUse();
        String sideEffects = currentItem.getSideEffects();


        holder.mTextViewMedicine.setText("Medicine : " + medicine);

        holder.mTextViewPrice.setText("Price : " + String.valueOf(price));
        holder.mTextViewUse.setText("Use : " + String.valueOf(use));
        holder.mTextViewSide_effects.setText("Side Effects : " + String.valueOf(sideEffects));


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

            List<medsItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(doctorsListAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (medsItem item : doctorsListAll) {
                    if (item.getMedicine().toLowerCase().contains(filterPattern)) {
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

        public TextView mTextViewMedicine;
        public TextView mTextViewPrice;
        public TextView mTextViewUse;
        public TextView mTextViewSide_effects;


        public ExampleViewHolder(@androidx.annotation.NonNull View itemView) {
            super(itemView);
            mTextViewMedicine = itemView.findViewById(R.id.Medicine_name);
            mTextViewPrice = itemView.findViewById(R.id.price_tag);
            mTextViewUse = itemView.findViewById(R.id.use_case);
            mTextViewSide_effects = itemView.findViewById(R.id.side__effects_of);

        }
    }
}





