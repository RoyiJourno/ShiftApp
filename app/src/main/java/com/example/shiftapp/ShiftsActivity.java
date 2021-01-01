package com.example.shiftapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShiftsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Shift> shiftArrayList;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shifts);
        mAuth = FirebaseAuth.getInstance();
        shiftArrayList = new ArrayList<Shift>();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Shifts").child(mAuth.getUid());


        listView = findViewById(R.id.shiftListView);

        readFromDB();

    }

    private void readFromDB() {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                shiftArrayList.clear();
                for (DataSnapshot dp: dataSnapshot.getChildren()) {
                    Shift shift = dp.getValue(Shift.class);
                    shiftArrayList.add(shift);
                }
                ShiftListAdapter shiftListAdapter = new ShiftListAdapter(ShiftsActivity.this,shiftArrayList);
                listView.setAdapter(shiftListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}