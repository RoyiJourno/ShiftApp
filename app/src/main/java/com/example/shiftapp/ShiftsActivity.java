package com.example.shiftapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    Button addShift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shifts);
        mAuth = FirebaseAuth.getInstance();
        shiftArrayList = new ArrayList<Shift>();

        String userWorkPlace = getIntent().getStringExtra("work_place");


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Shifts").child(mAuth.getUid()).child(userWorkPlace);


        listView = findViewById(R.id.shiftListView);
        addShift = findViewById(R.id.btnAdd);



        addShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShiftsActivity.this);
                View view =getLayoutInflater().inflate(R.layout.addshiftdialog,null);
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                EditText insertDate = view.findViewById(R.id.insertDate);
                EditText insertStartTime = view.findViewById(R.id.insertStartTime);
                EditText insertExitTime = view.findViewById(R.id.insertExitTime);
                EditText insertTotalTime = view.findViewById(R.id.insertTotalTime);
                view.findViewById(R.id.btnAddShiftToDB).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Shift temp = new Shift(insertStartTime.getText().toString(),
                                insertExitTime.getText().toString(),
                                insertTotalTime.getText().toString(),
                                insertDate.getText().toString());
                        myRef.push().setValue(temp);
                        alertDialog.dismiss();
                    }
                });

            }
        });

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