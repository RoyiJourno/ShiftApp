package com.example.shiftapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListViewUserWorkPlace extends AppCompatActivity {

    ListView listView;
    Button btnAdd;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_user_work_place);

        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getUid()).child("WorkPlaces");

        listView = findViewById(R.id.userWorkPLaceListView);
        btnAdd = findViewById(R.id.btnAdd);

        arrayList = new ArrayList<>();

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dp : snapshot.getChildren()){
                    String temp = dp.getValue(String.class);
                    arrayList.add(temp);
                }
                arrayAdapter = new ArrayAdapter<String>(ListViewUserWorkPlace.this,R.layout.simplerow,arrayList);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListViewUserWorkPlace.this,ShiftsActivity.class);
                intent.putExtra("work_place",arrayList.get(position));
                startActivity(intent);
            }
        });

    }
}