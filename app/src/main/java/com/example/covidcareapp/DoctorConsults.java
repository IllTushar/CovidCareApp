package com.example.covidcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DoctorConsults extends AppCompatActivity {
Button doctorConsualt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_consults);
        doctorConsualt = findViewById(R.id.doctorconsult);
        doctorConsualt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorConsults.this,NearByHospital.class));
            }
        });
    }
}