package com.example.covidcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NearByHospital extends AppCompatActivity {
Button nearbyhospital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_hospital);
        nearbyhospital = findViewById(R.id.next);
        nearbyhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NearByHospital.this,DashBoardActivity.class));
            }
        });

    }
}