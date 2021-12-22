package com.example.projekt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projekt.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegisterFragment extends Fragment {

    private FirebaseAuth mAuth;
    EditText loginEditText, passwordEditText, nameEditText, surnameEditText;
    Button registerButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        loginEditText = (EditText) view.findViewById(R.id.editTextLogin);
        passwordEditText = (EditText) view.findViewById(R.id.editTextPassword);
        nameEditText = (EditText) view.findViewById(R.id.editTextName);
        surnameEditText = (EditText) view.findViewById(R.id.editTextSurname);
        registerButton = (Button) view.findViewById(R.id.registerButton);

        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        return view;
    }

    private void register(){
        String login = loginEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String name = nameEditText.getText().toString().trim();
        String surname = surnameEditText.getText().toString().trim();

        if(login.isEmpty()){
            loginEditText.setError("Email jest wymagany!");
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(login).matches()){
            loginEditText.setError("Wpisz prawidłowy email!");
            return;
        }

        if(password.isEmpty()){
            passwordEditText.setError("Hasło jest wymagane!");
            return;
        }

        if(password.length()<6){
            passwordEditText.setError("Hasło musi mieć minimum 6 znaków!");
            return;
        }

        if(name.isEmpty()){
            nameEditText.setError("Podaj imie!");
            return;
        }

        if(surname.isEmpty()){
            surnameEditText.setError("Podaj nazwisko!");
            return;
        }

        mAuth.createUserWithEmailAndPassword(login, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(name, surname);
                    db.collection("Users").document(mAuth.getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getView().getContext(), "Konto utworzone pomyślnie!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}