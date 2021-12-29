package com.example.projekt;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class OpinionHolder extends RecyclerView.ViewHolder {
    TextView tvOpinion;
    TextView tvDate;
    TextView tvSalon;
    ImageView ivIcon;

    public OpinionHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        ivIcon = itemView.findViewById(R.id.ivIcon);
        tvOpinion = itemView.findViewById(R.id.tvOpinion);
        tvDate = itemView.findViewById(R.id.tvDate);
        tvSalon = itemView.findViewById(R.id.tvSalon);
    }
}
