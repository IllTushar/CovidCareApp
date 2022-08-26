package com.example.covidcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONObject;

public class DashBoardActivity extends AppCompatActivity {
    TextView tvCases,tvCritical,tvTotalDeaths,tvTodayDeaths,tvRecovered,tvTotalCases,tvActiveCases,tvAffectedCountries,tvTodayCases;
    PieChart pieChart;
    Button btnNearestHospital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board2);
        tvCases = findViewById(R.id.tvCases);
        tvCritical = findViewById(R.id.tvCritical);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvActiveCases = findViewById(R.id.tvActive);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvTotalCases = findViewById(R.id.tvTotalCase);
        tvAffectedCountries = findViewById(R.id.tvAffectedCountries);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        btnNearestHospital = findViewById(R.id.btnHospital);
        btnNearestHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MapActivity.class));
            }
        });
        pieChart = findViewById(R.id.piechart);
        featchData();
    }
    private void featchData() {
        String url ="https://disease.sh/v3/covid-19/all/";
        StringRequest request = new StringRequest(Request.Method.GET, url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response.toString());
                    tvCases.setText(jsonObject.getString("cases"));
                    tvTotalCases.setText(jsonObject.getString("cases"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvRecovered.setText(jsonObject.getString("recovered"));
                    tvActiveCases.setText(jsonObject.getString("active"));
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                    tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));
                    tvTodayCases.setText(jsonObject.getString("todayCases"));

                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#F87147")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActiveCases.getText().toString()), Color.parseColor("#2196F3")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#F44336")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#4CAF50")));

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}