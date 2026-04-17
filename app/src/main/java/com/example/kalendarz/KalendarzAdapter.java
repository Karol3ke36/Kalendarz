package com.example.kalendarz;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class KalendarzAdapter extends RecyclerView.Adapter<KalendarzAdapter.ViewHolder> {

    private ArrayList<LocalDate> dni;

    public KalendarzAdapter(ArrayList<LocalDate> dni) {
        this.dni = dni;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.komorka_dnia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LocalDate date = dni.get(position);
        LocalDate today = LocalDate.now();

        if (date == null) {
            holder.komorkaDnia.setText("");
        }
        else {
            holder.komorkaDnia.setText(String.valueOf(date.getDayOfMonth()));
            if (date.equals(today)) {
                holder.komorkaDnia.setTextColor(Color.RED);
            } else {
                holder.komorkaDnia.setTextColor(Color.BLACK);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dni.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView komorkaDnia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            komorkaDnia = itemView.findViewById(R.id.komorkaDnia);
        }
    }
}
