package com.example.signup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signup.ModelH.StractFinal;

import java.util.List;

public class FinalAdapter extends RecyclerView.Adapter<com.example.signup.FinalAdapter.ViewHolder> {

    private final Context context;
    private final List<StractFinal> modelPersonList;


    public FinalAdapter(List<StractFinal> modelPersonList, Context context) {
        this.modelPersonList = modelPersonList;
        this.context = context;

    }

    @NonNull
    @Override
    public com.example.signup.FinalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.findetails, parent, false);
        return new com.example.signup.FinalAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.signup.FinalAdapter.ViewHolder holder, int position) {

        StractFinal modelPerson = modelPersonList.get(position);
        holder.HospitalName.setText(modelPerson.getHospitalName());
        holder.Address.setText(modelPerson.getAddress());
        //holder.Telephone.setText(modelPerson.getTelephone());
        holder.City.setText(modelPerson.getCity());
        holder.Pincode.setText(modelPerson.getPincode());
        holder.States.setText(modelPerson.getStates());
        holder.Website.setText(modelPerson.getWebsite());
        holder.Mobile.setText(modelPerson.getMobile());
        holder.Email.setText(modelPerson.getEmail());
        holder.Speciality.setText(modelPerson.getSpeciality());
      //  holder.HospitalType.setText(modelPerson.getHospitalType());
        holder.Longitude.setText(modelPerson.getLongitude());
        holder.Latitude.setText(modelPerson.getLatitude());
        //   holder.IMGTEXT.setText(modelPerson.getHospitalName());
    }

    @Override
    public int getItemCount() {
        return modelPersonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView HospitalName, Mobile, Address, City, Pincode, States,  Website, Email, Speciality,  Longitude, Latitude;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            IMGTEXT = itemView.findViewById(R.id.IMGTEXT);
            HospitalName = itemView.findViewById(R.id.HOSPITALNAME);
            Address = itemView.findViewById(R.id.ADDRESS);
            City = itemView.findViewById(R.id.CITY);
            Pincode = itemView.findViewById(R.id.PINCODE);
            States = itemView.findViewById(R.id.STATE);
          //  Telephone = itemView.findViewById(R.id.TELEPHONE);
            Website = itemView.findViewById(R.id.WEBSITE);
            Email = itemView.findViewById(R.id.EMAIL);
            Speciality = itemView.findViewById(R.id.SPECIALITY);
          //  HospitalType = itemView.findViewById(R.id.TYPE);
            Longitude = itemView.findViewById(R.id.LONGITUDE);
            Latitude = itemView.findViewById(R.id.LATITUDE);
            Mobile = itemView.findViewById(R.id.MOBILENUMBER);


            Button button = itemView.findViewById(R.id.BUTS);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String location = "geo:0, 0?q=" + HospitalName.getText().toString() + "," + Address.getText().toString() + "," + City.getText().toString() + "," + Pincode.getText().toString();
                    Toast.makeText(context, location, Toast.LENGTH_SHORT).show();
                    Log.d("MAPS", location);
                    Uri uri = Uri.parse(location);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");
                    context.startActivity(intent);
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Aur bhai", Toast.LENGTH_SHORT).show();
            //StractFinal modelPerson = modelPersonList.get(getAdapterPosition());
//        Intent intent = new Intent(context,FinalHospital.class);
//        intent.putExtra("Hospital",modelPerson.getHosc());
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
            // Toast.makeText(context, modelPerson.getHosc(), Toast.LENGTH_SHORT).show();
        }
    }
}