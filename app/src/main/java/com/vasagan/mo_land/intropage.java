package com.vasagan.mo_land;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class intropage extends AppCompatActivity {

    private ImageView bg_intro,smartgrow_intro,logo_intro,subintro;
    LottieAnimationView lottieAnimationView;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intropage);

        //Timer for UI_1

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(intropage.this, MainActivity.class);
                startActivity(intent);
            }
        },12000);

        //wiring components of UI_1

        bg_intro = (ImageView) findViewById(R.id.bg_intro);
        smartgrow_intro = (ImageView) findViewById(R.id.smartgrow_intro);
        lottieAnimationView = findViewById(R.id.lottieAnimationView2);
        logo_intro = (ImageView) findViewById(R.id.logo_intro);
        subintro = (ImageView) findViewById(R.id.subintro);


        //animation for UI_1

        bg_intro.animate().translationY(-2300).setDuration(1000).setStartDelay(4000);
        logo_intro.animate().translationX(1700).setDuration(2000).setStartDelay(4000);
        smartgrow_intro.animate().translationX(-1600).setDuration(2100).setStartDelay(4000);
        subintro.animate().translationX(1600).setDuration(2100).setStartDelay(4000);
        lottieAnimationView.animate().translationY(-1300).setDuration(3000).setStartDelay(4000);


    }
}