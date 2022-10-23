package com.example.login_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.FieldPosition;

public class login extends AppCompatActivity {

    Button registerButton, loginButton;
    TextInputLayout username_var, password_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        registerButton = findViewById(R.id.registerbutton_login);
        loginButton = findViewById(R.id.loginbutton_login);

        username_var = findViewById(R.id.username_login);
        password_var = findViewById(R.id.password_login);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);


            }
        });
    }


    public void registerbuttonclick(View view) {
        String username_ = username_var.getEditText().getText().toString();
        String password_ = password_var.getEditText().getText().toString();

        if (!username_.isEmpty()) {
            username_var.setError(null);
            username_var.setErrorEnabled(false);

            if (!password_.isEmpty()) {
                password_var.setError(null);
                password_var.setErrorEnabled(false);

                final String username_data = username_var.getEditText().getText().toString();
                final String password_data = password_var.getEditText().getText().toString();

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");

                Query check_username = databaseReference.orderByChild("username").equalTo(username_data);
                check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists()){
                            username_var.setError(null);
                            username_var.setErrorEnabled(false);
                            String passwordcheck = snapshot.child(username_data).child("password").getValue(String.class);
                            if(passwordcheck.equals(password_data)){
                                password_var.setError(null);
                                password_var.setErrorEnabled(false);
                                Toast.makeText(getApplicationContext(), "Welcome back home", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(),dashboard.class);
                                startActivity(intent);
                                finish();
                            }else{
                                password_var.setError("Please enter valid password");
                            }
                        }else{
                            username_var.setError("Username does not exist please register yourself");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                Query check_password = databaseReference.orderByChild("password").equalTo(password_data);

            } else {
                password_var.setError("This field is required");
            }
        } else {
            username_var.setError("This field is required");
        }
    }
}