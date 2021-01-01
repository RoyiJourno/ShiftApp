package com.example.shiftapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShiftListAdapter extends ArrayAdapter<Shift> {

    Activity context;
    ArrayList<Shift> shiftArrayList;

    public ShiftListAdapter(Activity context,ArrayList<Shift> shiftArrayList){
        super(context,R.layout.shiftlistview,shiftArrayList);
        this.context = context;
        this.shiftArrayList = shiftArrayList;
    }

    // override other abstract methods here
    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.shiftlistview, null, true);

        TextView insertTime = view.findViewById(R.id.insertTime);
        TextView exitTime = view.findViewById(R.id.exitTime);
        TextView totalTime = view.findViewById(R.id.totalTime);
        TextView date = view.findViewById(R.id.date);

        insertTime.setText(shiftArrayList.get(position).getInsertTime());
        exitTime.setText(shiftArrayList.get(position).getExitTime());
        totalTime.setText(shiftArrayList.get(position).getTotalTime());
        date.setText(shiftArrayList.get(position).getDate());

        return view;
    }
}