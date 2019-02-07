package com.example.algoritmios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button confirmare;
    TextView frames,pages;
    static TextView frames_no,pages_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        confirmare  = (Button) findViewById(R.id.confirmare);
        frames = (TextView) findViewById(R.id.frames);
        frames_no = (TextView) findViewById(R.id.frames_no);
        pages = (TextView) findViewById(R.id.pages);
        pages_no = (TextView) findViewById(R.id.pages_no);
        System.out.println(frames_no.getText());
        confirmare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, pagini.class));
            }
        });
    }
}
