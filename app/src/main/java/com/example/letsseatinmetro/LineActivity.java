package com.example.letsseatinmetro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class LineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(),intent.getStringExtra("lineName") , Toast.LENGTH_LONG).show();

    }
}
