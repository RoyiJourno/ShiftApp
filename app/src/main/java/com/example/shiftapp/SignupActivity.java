package com.example.shiftapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSignup;
    EditText userEmail,userPass,userRepass,userPhone,userFullName;
    private FirebaseAuth mAuth;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        initViews();
        initListener();
    }

    private void initListener() {
        btnSignup.setOnClickListener(this);
    }

    private void initViews() {
        btnSignup = findViewById(R.id.btnSignup);
        userEmail = findViewById(R.id.userEmail);
        userPass = findViewById(R.id.userPassword);
        userRepass = findViewById(R.id.userRepassword);
        userPhone = findViewById(R.id.userPhone);
        userFullName = findViewById(R.id.userFullName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignup: userSignup();break;
        }
    }

    private void userSignup() {
        String email = userEmail.getText().toString();
        String pass = userPass.getText().toString();
        String repass = userRepass.getText().toString();
        String phone = userPhone.getText().toString();
        String fullName = userFullName.getText().toString();

        if(pass.equals(repass)) {
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                User user = new User(fullName,phone,false);
                                saveInfoInDB(user);
                                startActivity(new Intent(SignupActivity.this,MainActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }else{
            Toast.makeText(getApplicationContext(), "Passwords not match!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void saveInfoInDB(User user) {
        myRef.child(mAuth.getUid()).setValue(user);
    }
}