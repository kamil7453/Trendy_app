package com.example.projekt;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.Executor;


public class LoginFragment extends Fragment {

    EditText loginEditText, passwordEditText;
    Button loginButton;

    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginEditText = (EditText) view.findViewById(R.id.editTextLogin);
        passwordEditText = (EditText) view.findViewById(R.id.editTextPassword);
        loginButton = (Button) view.findViewById(R.id.buttonLogin);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        return view;
    }

    public void login() {
        String login = loginEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if(login.isEmpty()){
            loginEditText.setError("Podaj email!");
            return;
        }


        if(password.isEmpty()){
            passwordEditText.setError("Podaj hasło!");
            return;
        }

        mAuth.signInWithEmailAndPassword(login, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getView().getContext(), "Logowanie nie powiodło się!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

