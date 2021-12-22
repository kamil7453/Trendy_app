package com.example.projekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.projekt.model.Opinion;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.Timestamp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

public class OpinionRecyclerViewAdapter extends FirestoreRecyclerAdapter<Opinion, OpinionHolder> {

    FirebaseStorage storage;
    Context context;


    public OpinionRecyclerViewAdapter(@NonNull @NotNull FirestoreRecyclerOptions<Opinion> options,
                                      FirebaseStorage storage, Context context) {
        super(options);
        this.storage = storage;
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull OpinionHolder holder, int position, @NonNull Opinion model) {

        holder.tvOpinion.setText(model.getOpinion());
        holder.tvDate.setText(model.getDate().toDate().toString());
        //long id = model.getId();
        //holder.tvID.setText( String.valueOf(id) );

        StorageReference storageReference = storage.getReference(model.getPicture());
        GlideApp.with(context)
                .load(storageReference)
                .into(holder.ivIcon);
        //holder.ivIcon
    }

    @NonNull
    @Override
    public OpinionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.opinion_rv, parent, false);

        return new OpinionHolder(view);
    }
}
