package com.example.rucafe_androidapp;

import androidx.appcompat.app.AppCompatActivity;
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

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
public class CoffeeActivity extends AppCompatActivity {

    private CheckBox sweet_cream;
    private CheckBox french_vanilla;
    private CheckBox irish_cream;
    private CheckBox caramel;
    private CheckBox mocha;

    private RadioGroup sizes_radioGroup;
    private RadioButton short_radio;
    private RadioButton tall_radio;
    private RadioButton grande_radio;
    private RadioButton venti_radio;
    private Button add_btn;

    private TextView subtotal;
    private Spinner coffee_quantity;

    private double currentSubtotal;

    private ArrayList<String> currentAddons;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_activity);
        sweet_cream = findViewById(R.id.sweet_cream);
        french_vanilla = findViewById(R.id.french_vanilla);
        irish_cream = findViewById(R.id.irish_cream);
        caramel = findViewById(R.id.caramel);
        mocha = findViewById(R.id.mocha);
        short_radio = findViewById(R.id.short_radio);
        tall_radio = findViewById(R.id.tall_radio);
        grande_radio = findViewById(R.id.grande_radio);
        venti_radio = findViewById(R.id.venti_radio);
        add_btn = findViewById(R.id.add_coffee_btn);
        coffee_quantity = findViewById(R.id.coffee_quantity);
        sizes_radioGroup = findViewById(R.id.sizes_radioGroup);
        setAddButtonOnClick();
        setRadioGroupOnSelect();
        setCheckBoxOnSelect();
        setCoffeeQuantityOnSelect();
        currentAddons = new ArrayList<>();
        /*
        subtotal = findViewById(R.id.coffee_price);
        String initialPrice = "$"+getString(R.string.coffee_size_short_price);
        subtotal.setText(initialPrice);

         */
    }


    public void setAddButtonOnClick() {
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioButtonId = sizes_radioGroup.getCheckedRadioButtonId();
                RadioButton selected = findViewById(radioButtonId);
                String size = selected.getText().toString();

                Toast.makeText(view.getContext(),"Added to cart",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setCoffeeQuantityOnSelect() {
        coffee_quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void setRadioGroupOnSelect() {
        sizes_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.short_radio) {
                    Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                }
                else if(checkedId == R.id.tall_radio) {

                }
                else if(checkedId == R.id.grande_radio) {

                }
                else {

                }
            }
        });
    }

    public void setCheckBoxOnSelect() {
        sweet_cream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(),"sweet cream checked",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(),"sweet cream unchecked",Toast.LENGTH_SHORT).show();
            }
        });
        french_vanilla.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(),"sweet cream checked",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(),"sweet cream unchecked",Toast.LENGTH_SHORT).show();
            }
        });
        irish_cream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(),"sweet cream checked",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(),"sweet cream unchecked",Toast.LENGTH_SHORT).show();
            }
        });
        caramel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(),"sweet cream checked",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(),"sweet cream unchecked",Toast.LENGTH_SHORT).show();
            }
        });
        mocha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(),"sweet cream checked",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(),"mo unchecked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updatePrice() {

    }
}
