package com.example.utilityapp;


import androidx.appcompat.app.AppCompatActivity;


class Water extends AppCompatActivity {
    public int percent = 0;
    public int large = 600;
    public int small = 250;
    public int max = 2000;
    public int vSmall = 0;
    public int rSmall = 0;
    public int vLarge = 0;
    public int rLarge = 0;

    public int calculateSmallLeft() {
        if (percent > max) {
            percent = max;
        }

        vSmall = (max - percent) / small;
        rSmall = (max - percent) % small;

        if (rSmall == 0) {
            return vSmall;
        } else {
            return vSmall + 1;
        }
    }

    public int calculateLargeLeft() {
        if (percent > max) {
            percent = max;
        }

        vLarge = (max - percent) / large;
        rLarge = (max - percent) % large;

        if (rLarge == 0) {
            return vLarge;
        } else {
            return vLarge + 1;
        }
    }


}
