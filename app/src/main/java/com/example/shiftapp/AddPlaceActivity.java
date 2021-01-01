package com.example.shiftapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPlaceActivity extends AppCompatActivity implements View.OnClickListener {
    EditText workName,workEmail,workPassword,workRepassword,workPhone,workAddress;
    Button btnAddPlace;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        mAuth = FirebaseAuth.getInstance();
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("WorkPlaces").child(mAuth.getUid());
        initViews();
        initLisetener();


    }

    private void initLisetener() {
        btnAddPlace.setOnClickListener(this);
    }

    private void initViews() {
        workName = findViewById(R.id.workName);
        workEmail = findViewById(R.id.workEmail);
        workPassword = findViewById(R.id.workPassword);
        workRepassword = findViewById(R.id.workRepassword);
        workPhone = findViewById(R.id.workPhone);
        workAddress = findViewById(R.id.workAddress);
        btnAddPlace = findViewById(R.id.btnAddPlace);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAddPlace:addWorkPlace(); break;
        }
    }

    private void addWorkPlace() {
        WorkPlace workPlace = new WorkPlace(workName.getText().toString(),workPhone.getText().toString()
                ,workAddress.getText().toString(),workEmail.getText().toString());
        myRef.setValue(workPlace);
    }
}