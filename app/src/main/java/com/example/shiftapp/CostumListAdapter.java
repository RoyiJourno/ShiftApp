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

public class CostumListAdapter extends ArrayAdapter<String> {

    Activity context;
    ArrayList<String> country;
    ArrayList<String> countryDesc;
    ArrayList<Integer> countryImg;

    public CostumListAdapter(Activity context,ArrayList<String> country,ArrayList<String> countryDesc,ArrayList<Integer> countryImg){
        super(context,R.layout.costumlistview,country);
        this.context = context;
        this.country = country;
        this.countryDesc = countryDesc;
        this.countryImg = countryImg;
    }

    // override other abstract methods here
    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.costumlistview, null, true);

        TextView bigText = view.findViewById(R.id.bigText);
        TextView smallText = view.findViewById(R.id.smallText);
        ImageView img = view.findViewById(R.id.img);

        bigText.setText(country.get(position));
        smallText.setText(countryDesc.get(position));
        img.setImageResource(countryImg.get(position));

        return view;
    }
}