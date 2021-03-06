package com.example.utilityapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    public static final int SETTINGS_REQUEST = 1;
    private int water;
    private int cup;
    private int bottle;
    private final int defaultMax = 2000;
    private final int defaultLarge = 600;
    private final int defaultSmall = 250;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



    }

    public void doneClicked(View view) {
        EditText et;
        int emptyCounter = 0;

        et = findViewById(R.id.waterAmount);

        // If one value is entered and the others are not, the others are set to DEFAULT values
        if (et.length() != 0)
        {water = getInt(et);}
        else {
            water = defaultMax;
            emptyCounter++;
        }

        et = findViewById(R.id.cupAmount);
        if (et.length() != 0)
        {cup = getInt(et);}
        else {
            cup = defaultSmall;
            emptyCounter++;
        }

        et = findViewById(R.id.bottleAmount);
        if (et.length() != 0)
        {bottle = getInt(et);}
        else {
            bottle = defaultLarge;
            emptyCounter++;
        }
        System.out.println(bottle);

        // If no values are entered, settings are unchanged
        if (emptyCounter == 3) {
            emptyIntent();
        }
        sendIntent();


    }

    public void clearText(View view) {
        EditText text = findViewById(R.id.waterAmount);
        text.setText("");
        text = findViewById(R.id.cupAmount);
        text.setText("");
        text = findViewById(R.id.bottleAmount);
        text.setText("");
    }

    private void sendIntent() {
        Intent intent = new Intent();
        intent.putExtra("water", water);
        intent.putExtra("cup", cup);
        intent.putExtra("bottle", bottle);
        setResult(RESULT_OK, intent);
        finish();
    }

    private int getInt(EditText et) {
        String etString = et.getText().toString();
        return Integer.parseInt(etString);
    }

    public void defaultClicked(View view) {
        water = defaultMax;
        cup = defaultSmall;
        bottle = defaultLarge;
        sendIntent();
    }

    private void emptyIntent() {
        finish();
    }
}
