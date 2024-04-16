package com.example.rucafe_androidapp;



import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DonutActivity extends AppCompatActivity{

    private ArrayList<Donut> donuts = new ArrayList<>();

    private int[] yeastImages = {R.drawable.glazed_yeast,R.drawable.chocolate_yeast,
            R.drawable.strawberry_yeast,R.drawable.vanilla_yeast,R.drawable.maple_yeast,
            R.drawable.cinnamon_yeast};
    private int[] cakeImages = {R.drawable.plain_cake,R.drawable.chocolate_cake,
            R.drawable.blueberry_cake};

    private int[] holeImages = {R.drawable.cinnamonsugar_donutholes,
            R.drawable.powderedsugar_donutholes,R.drawable.jelly_donutholes};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_activity);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setUpDonutItems();
        DonutOptionsAdapter adapter = new DonutOptionsAdapter(this,donuts);
        rcview.setAdapter(adapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));

    }
    private void setUpDonutItems() {
        String [] yeastFlavors = getResources().getStringArray(R.array.yeastFlavors);
        String [] cakeFlavors = getResources().getStringArray(R.array.cakeFlavors);
        String [] holeFlavors = getResources().getStringArray(R.array.donutHoleFlavors);
        String [] prices = getResources().getStringArray(R.array.donut_prices);
        String [] donutTypes = getResources().getStringArray(R.array.donutTypeNames);
        String [][] donutFlavors = {yeastFlavors,cakeFlavors,holeFlavors};
        int [][] donutImages = {yeastImages,cakeImages,holeImages};

        for (int i = 0; i<donutTypes.length; i++) {
            for (int j = 0; j<donutFlavors[i].length; j++) {
                donuts.add(new Donut(donutTypes[i],donutFlavors[i][j],donutImages[i][j],Double.parseDouble(prices[i])));
            }
        }


    }
}
