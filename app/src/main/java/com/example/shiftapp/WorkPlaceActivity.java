package com.example.shiftapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WorkPlaceActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference mRef;
    DatabaseReference mRefUserWork;
    FirebaseAuth mAuth;
    ArrayList<WorkPlace> workPlaces;
    ArrayList<String> userWorkPlaces;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_place);
        listView = findViewById(R.id.workPlaceListView);
        workPlaces = new ArrayList<>();
        userWorkPlaces = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference("WorkPlaces");
        mRefUserWork = firebaseDatabase.getReference("Users").child(mAuth.getUid()).child("WorkPlaces");

        readWorkPlaceFromDB();

        readUserWorkPlaceFromDB();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String workPlaceName = workPlaces.get(position).getName();
                if(userWorkPlaces.contains(workPlaceName)){
                    Toast.makeText(getApplicationContext(),"You are already assign to this work place!!",Toast.LENGTH_LONG).show();
                }else{
                    mRefUserWork.push().setValue(workPlaceName);
                }
            }
        });
    }

    private void readWorkPlaceFromDB() {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dp: snapshot.getChildren()) {
                    WorkPlace temp = dp.getValue(WorkPlace.class);
                    workPlaces.add(temp);
                }
                WorkPlaceListAdapter costumListAdapter = new WorkPlaceListAdapter(WorkPlaceActivity.this,workPlaces);
                listView.setAdapter(costumListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void readUserWorkPlaceFromDB() {
        mRefUserWork.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dp: snapshot.getChildren()) {
                    String temp = dp.getValue(String.class);
                    userWorkPlaces.add(temp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}