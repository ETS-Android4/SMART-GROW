package com.vasagan.mo_land;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;

public class userland extends AppCompatActivity {
    private Button logout_ul;
    private TextView wlcmtodshbrd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userland);

        wlcmtodshbrd = (TextView)findViewById(R.id.welcomtodshbrd);





        logout_ul = (Button) findViewById(R.id.logout_ul);
        logout_ul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userland.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        });


    }



}