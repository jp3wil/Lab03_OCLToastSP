package com.wiluszjp.lab03_ocltoastsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView boxTL, boxTR, boxBL, boxBR;
    SeekBar fontChange;
    int current;
    String outstring;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Resources res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        res = getResources();
        editor = sharedPreferences.edit();
        boxTL = findViewById(R.id.topLeftBox);
        boxTR = findViewById(R.id.topRightBox);
        boxBL = findViewById(R.id.bottomLeftBox);
        boxBR = findViewById(R.id.bottomRightBox);
        fontChange = findViewById(R.id.fontSlider);
        fontChange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                boxTL.setTextSize(progress);
                boxTR.setTextSize(progress);
                boxBL.setTextSize(progress);
                boxBR.setTextSize(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView clicked = (TextView) v;
                current = sharedPreferences.getInt("" + clicked.getId() + "count", 1);
                if(clicked.getId() == boxTL.getId())
                    outstring = res.getQuantityString(R.plurals.outString, current, "top left box", current);
                else if(clicked.getId() == boxTR.getId())
                    outstring = res.getQuantityString(R.plurals.outString, current, "top right box", current);
                else if(clicked.getId() == boxBL.getId())
                    outstring = res.getQuantityString(R.plurals.outString, current, "bottom left box", current);
                else if(clicked.getId() == boxBR.getId())
                    outstring = res.getQuantityString(R.plurals.outString, current, "bottom right box", current);
                Toast.makeText(getApplicationContext(), outstring, Toast.LENGTH_SHORT).show();
                editor.putInt(clicked.getId() + "count", current + 1);
                editor.apply();
            }
        };

        boxTL.setOnClickListener(l);
        boxTR.setOnClickListener(l);
        boxBL.setOnClickListener(l);
        boxBR.setOnClickListener(l);
    }
}