package com.example.projekt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.projekt.model.Salon;
import com.example.projekt.model.Service;
import com.example.projekt.model.Visit;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class VisitsFragment extends Fragment {

    ListView visitList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    List<Visit> visitsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DocumentReference userReference = db.collection("Users").document(user.getUid());
        db.collection("Visits")
                .whereEqualTo("user", userReference)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                db.document(document.getDocumentReference("salon").getPath())
                                         .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                     @Override
                                     public void onSuccess(DocumentSnapshot documentSnapshot) {
                                         db.document(document.getDocumentReference("service").getPath())
                                                 .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                             @Override
                                             public void onSuccess(DocumentSnapshot documentSnapshot1) {
                                                 Salon salon = documentSnapshot.toObject(Salon.class);
                                                 Service service = new Service(documentSnapshot1.getData().get("name").toString(), documentSnapshot1.getData().get("duration").toString(), documentSnapshot1.getData().get("price").toString(),
                                                         documentSnapshot1.getData().get("description").toString(), salon, (List<String>) documentSnapshot1.getData().get("hashtags"));
                                                 Visit visit = new Visit(salon, service, user.getUid(), document.getString("date"), document.getString("hour"));
                                                 visitsList.add(visit);
                                                 visitList = (ListView) getView().findViewById(R.id.visits_list_view);
                                                 VisitListAdapter adapter = new VisitListAdapter(getContext(), visitsList);
                                                 adapter.notifyDataSetChanged();
                                                 visitList.setAdapter(adapter);
                                             }
                                         });
                                     }
                                 });
                            }
                        }
                    }
                });



        return inflater.inflate(R.layout.fragment_visits, container, false);
    }
}