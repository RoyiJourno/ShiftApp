package com.example.shiftapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomListViewActivity extends AppCompatActivity {

    ListView listView;

    String[] country;
    String[] countryDesc;
    Integer[] countryImg;

    ArrayList<String> country1;
    ArrayList<String> countryDesc1;
    ArrayList<Integer> countryImg1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        listView = findViewById(R.id.costumListView);

        country = new String[]{"Israel","India","USA","France","Brazil","India","USA","France","Brazil","India","USA","France","Brazil","India","USA","France","Brazil"};
        countryDesc = new String[]{"שלום","Nameste","Hello","Bonjure","Hola","Nameste","Hello","Bonjure","Hola","Nameste","Hello","Bonjure","Hola","Nameste","Hello","Bonjure","Hola"};
        countryImg = new Integer[]{R.drawable.israel,R.drawable.india,R.drawable.usa,R.drawable.france,R.drawable.brazil,R.drawable.india,R.drawable.usa,R.drawable.france,R.drawable.brazil,R.drawable.india,R.drawable.usa,R.drawable.france,R.drawable.brazil,R.drawable.india,R.drawable.usa,R.drawable.france,R.drawable.brazil};

        country1 = new ArrayList<>();
        countryDesc1 = new ArrayList<>();
        countryImg1= new ArrayList<>();

        country1.addAll(Arrays.asList(country));
        countryDesc1.addAll(Arrays.asList(countryDesc));
        countryImg1.addAll(Arrays.asList(countryImg));

        CostumListAdapter costumListAdapter = new CostumListAdapter(this,country1,countryDesc1,countryImg1);

        listView.setAdapter(costumListAdapter);

    }
}