package com.example.projekt;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekt.model.Opinion;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;


public class OpinionFragment extends Fragment {

    RecyclerView rv;
    FirebaseFirestore firestore;
    FirebaseStorage storage;
    OpinionRecyclerViewAdapter adapter;

    public OpinionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_opinion_frament, container, false);



        rv = view.findViewById(R.id.rvOpinions);
        firestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();


//query
        Query query = firestore.collection("Opinions").orderBy("date").limit(20);
        //options
        FirestoreRecyclerOptions<Opinion> options =
                new FirestoreRecyclerOptions.Builder<Opinion>( ).setQuery(query, Opinion.class).build();
        //adapter
        //adapter = new UserRecyclerVewAdapter(options);
        adapter = new OpinionRecyclerViewAdapter(options, storage, getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        rv.setAdapter(adapter);

        adapter.startListening();




        return view;
    }

}