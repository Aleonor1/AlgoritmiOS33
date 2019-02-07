package com.example.algoritmios;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class pagini extends AppCompatActivity {
    Button confirmare2;
    static TextView pages1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagini);
        confirmare2 = (Button) findViewById(R.id.confirmare2);
        pages1 = (TextView) findViewById(R.id.numeric);

        confirmare2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pagini.this, raspunsuri.class));
            }
        });
    }
}

