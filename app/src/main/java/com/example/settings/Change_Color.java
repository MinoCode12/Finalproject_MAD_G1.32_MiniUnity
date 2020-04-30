package com.example.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Random;

public class Change_Color extends AppCompatActivity {
    String colorArray[] = {"#9dc4c3", "#a4b6c0", "#566777", "1c7097", "#e01449", "#8591ca", "#384936"};

    Button btn_ch;
    RelativeLayout bck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__color);

        btn_ch = (Button)findViewById(R.id.button);
        bck = (RelativeLayout) findViewById(R.id.activity_change_color);

        btn_ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random i = new Random();
                int c = i.nextInt(7-1) + 1;

                bck.setBackgroundColor(Color.parseColor(colorArray[c]));
            }
        });
    }
    }
