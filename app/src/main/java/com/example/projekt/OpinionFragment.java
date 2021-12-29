package com.example.projekt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekt.model.Opinion;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Date;


public class OpinionFragment extends Fragment implements OnItemSelectedListener {

    RecyclerView rv;
    FirebaseFirestore firestore;
    FirebaseStorage storage;
    OpinionRecyclerViewAdapter adapter;
    Spinner spinner;
    Button buttonAdd;
    EditText editTextOpinion;
    String salonSelected;

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


        spinner = view.findViewById(R.id.spinnerOpinion);
        ArrayAdapter<CharSequence> adapterSalons = ArrayAdapter.createFromResource(getContext(),R.array.salons, android.R.layout.simple_spinner_item);
        adapterSalons.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSalons);
        spinner.setOnItemSelectedListener(this);

        editTextOpinion = view.findViewById(R.id.editTextOpinion);


        buttonAdd = view.findViewById(R.id.buttonOpinion);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pobranie danych
                String opinionBD = editTextOpinion.getText().toString();
                String salonDB = salonSelected;
                String userDB = "anonim";

                Date date = new Date();
                //long timeMilli = date.getTime();

                Timestamp timestampDB = new Timestamp(date);
                //Photo

                //dodanie do bazy
                Opinion opinion = new Opinion(opinionBD, salonDB, timestampDB, userDB);


                firestore.collection("Opinions").add(opinion);

            }
        });






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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice = parent.getItemAtPosition(position).toString();
        Toast.makeText(getContext(), choice, Toast.LENGTH_LONG).show();
        salonSelected = choice;


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}