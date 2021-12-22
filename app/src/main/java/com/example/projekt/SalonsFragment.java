package com.example.projekt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.projekt.model.Salon;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class SalonsFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ListView SalonsList;
    List<Salon> salonlist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db.collection("Salons")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Salon sal = document.toObject(Salon.class);
                                salonlist.add(sal);
                            }

                            SalonsList = (ListView) getView().findViewById(R.id.salons_list_view);
                            SalonListAdapter adapter = new SalonListAdapter(getContext(), salonlist);
                            adapter.notifyDataSetChanged();
                            SalonsList.setAdapter(adapter);

                            SalonsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("salonInfo", salonlist.get(position));
                                    Fragment fragment = new SalonInformationFragment();
                                    fragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.flContent, fragment).commit();

                                }
                            });
                        }
                    }
                });
        return inflater.inflate(R.layout.fragment_salons, container, false);
    }
}