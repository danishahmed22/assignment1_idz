package com.example.login_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    TextInputLayout fullname_var, dob_var, email_var, username_var2, password_var2;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        fullname_var = findViewById(R.id.Name_register);
        dob_var = findViewById(R.id.dob_register);
        email_var = findViewById(R.id.email_register);
        username_var2 = findViewById(R.id.userame_register);
        password_var2 = findViewById(R.id.password_register);
    }

    public void loginbuttonclick(View view) {
        Intent intent = new Intent(getApplicationContext(), login.class);
        startActivity(intent);
        finish();
    }

    public void registerbuttonclick(View view) {

        String fullname_ = fullname_var.getEditText().getText().toString();
        String dob_ = dob_var.getEditText().getText().toString();
        String email_ = email_var.getEditText().getText().toString();
        String username_ = username_var2.getEditText().getText().toString();
        String password_ = password_var2.getEditText().getText().toString();

        if (!fullname_.isEmpty()) {
            fullname_var.setError(null);
            fullname_var.setErrorEnabled(false);
            if (!dob_.isEmpty()) {
                dob_var.setError(null);
                dob_var.setErrorEnabled(false);
                if (!email_.isEmpty()) {
                    email_var.setError(null);
                    email_var.setErrorEnabled(false);
                    if (!username_.isEmpty()) {
                        username_var2.setError(null);
                        username_var2.setErrorEnabled(false);
                        if (!password_.isEmpty()) {
                            password_var2.setError(null);
                            password_var2.setErrorEnabled(false);
                            if (email_.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {

                                firebaseDatabase = FirebaseDatabase.getInstance();
                                reference = firebaseDatabase.getReference("datauser");

                                String fullname_s = fullname_var.getEditText().getText().toString();
                                String dob_s = dob_var.getEditText().getText().toString();
                                String email_s = email_var.getEditText().getText().toString();
                                String username_s = username_var2.getEditText().getText().toString();
                                String password_s = password_var2.getEditText().getText().toString();

                                storingdata storingdatass = new storingdata(fullname_s,dob_s,email_s,username_s,password_s);

                                reference.child(username_s).setValue(storingdatass);

                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(),dashboard.class);
                                startActivity(intent);
                                finish();
                            } else {
                                email_var.setError("Invalid Email");
                            }
                        } else {
                            password_var2.setError("This field is required");
                        }
                    } else {
                        username_var2.setError("This field is required");
                    }
                } else {
                    email_var.setError("This field is required");
                }
            } else {
                dob_var.setError("This field is required");
            }
        } else {
            fullname_var.setError("This field is required");
        }
    }
}
