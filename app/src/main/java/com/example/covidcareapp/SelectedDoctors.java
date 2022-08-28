package com.example.covidcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SelectedDoctors extends AppCompatActivity {
    EditText id;
    Button join;
    private ImageSlider imageSlider;
    Button btnlocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_doctors);
        id = findViewById(R.id.ID);
        join = findViewById(R.id.join);

        imageSlider = findViewById(R.id.image_slider);
        btnlocation = findViewById(R.id.btnHospital);
        ArrayList<SlideModel> slider = new ArrayList<>();
        slider.add(new SlideModel(R.drawable.login, ScaleTypes.FIT));
        slider.add(new SlideModel(R.drawable.login1,ScaleTypes.FIT));
        slider.add(new SlideModel(R.drawable.login3,ScaleTypes.FIT));
        slider.add(new SlideModel(R.drawable.login4,ScaleTypes.FIT));
        imageSlider.setImageList(slider);

        URL serverURL;
        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURL)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(id.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();
                JitsiMeetActivity.launch(SelectedDoctors.this,options);
            }
        });
        btnlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MapActivity.class));
            }
        });
    }
}