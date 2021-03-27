package com.example.utilityapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WaterActivity extends AppCompatActivity {
    public Water water;
    private int oldWaterPercent = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int orientation = getResources().getConfiguration().orientation;


        if (savedInstanceState == null) {
            water = new Water();

        } else {
            water = new Water();
            water.percent = savedInstanceState.getInt("waterPercent", 0);
            water.small = savedInstanceState.getInt("waterSmall", 250);
            water.large = savedInstanceState.getInt("waterLarge", 600);
            water.max = savedInstanceState.getInt("waterMax", 2000);
            updateWaterPercent();
            checkOrientation(orientation);


        }


    }

    private void checkOrientation(int orientation) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            findViewById(R.id.waterAnimation).setVisibility(View.GONE);
            findViewById(R.id.clear).setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("waterPercent", water.percent);
        outState.putInt("waterSmall", water.small);
        outState.putInt("waterLarge", water.large);
        outState.putInt("waterMax", water.max);
    }


    public void addWaterLarge(View view) {
        water.percent += water.large;
        updateWaterPercent();

    }

    public void addWaterSmall(View view) {
        water.percent += water.small;
        updateWaterPercent();

    }

    public void waterClear(View view) {
        water.percent = 0;
        updateWaterPercent();

    }

    private void updateWaterPercent() {
        ProgressBar waterProgressBar = findViewById(R.id.waterProgressBar);
        waterProgressBar.setMax(water.max);


        // Progress bar animator

        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(waterProgressBar, "progress", oldWaterPercent, water.percent);
        progressAnimator.setDuration(500);
        progressAnimator.start();

        oldWaterPercent = water.percent;

        updateText();


    }

    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    int waterAmount = data.getIntExtra("water", 2000);
                    int cupAmount = data.getIntExtra("cup", 250);
                    int bottleAmount = data.getIntExtra("bottle", 600);
                    updateSettings(waterAmount, cupAmount, bottleAmount);
                }
            }
        }
    }


    private void updateSettings(int waterAmount, int cupAmount, int bottleAmount) {
        water.max = waterAmount;
        water.small = cupAmount;
        water.large = bottleAmount;
    }

    private void updateText() {
        TextView text = findViewById(R.id.text_box);
        String string;
        if (water.percent >= water.max) {
            text.setText(R.string.congratulations);
        } else {
            // Set text on screen using strings.xml
            string = String.format(getApplicationContext().getResources().getString(R.string.cups_bottle_left), water.calculateSmallLeft(), water.calculateLargeLeft());
            text.setText(string);
        }


    }


}