package com.example.shiftapp;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WorkPlaceListAdapter extends ArrayAdapter<WorkPlace> {

    Activity context;
    ArrayList<WorkPlace> arrayList;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference mRef;
    ArrayList<String> userWorkPlaces;

    public WorkPlaceListAdapter(Activity context,ArrayList<WorkPlace> arrayList){
        super(context,R.layout.costumlistview,arrayList);
        this.context = context;
        this.arrayList = arrayList;
        userWorkPlaces = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Users").child(mAuth.getUid()).child("WorkPlaces");
    }

    // override other abstract methods here
    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.costumlistview, null, true);

        TextView bigText = view.findViewById(R.id.bigText);
        TextView smallText = view.findViewById(R.id.smallText);
        ImageView img = view.findViewById(R.id.img);

        bigText.setText(arrayList.get(position).getName());
        smallText.setText(arrayList.get(position).getPhone());

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dp: snapshot.getChildren()) {
                    String temp = dp.getValue(String.class);
                    userWorkPlaces.add(temp);
                }
                if (userWorkPlaces.contains(arrayList.get(position).getName())) {
                    view.setBackgroundColor(Color.RED);
                } else {
                    view.setBackgroundColor(Color.GREEN);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}