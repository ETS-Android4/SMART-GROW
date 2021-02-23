package com.vasagan.mo_land;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    private View topView1_p1;
    private View topView2_p1;
    private ImageView contact_logo_p1;
    private ImageView siLogo_p1;
    private TextView subLogo_p1;
    private TextView email_p1;
    private TextView password_p1;
    private Button loginButton_p1;
    private TextView forgetPass_p1;
    private Button registerBtn_p1;
    private TextView accountHavingTxt_p1;
    private TextView subofsub_UI2p1;
    private View divider_UI;
    private LottieAnimationView lottieAnimationView2;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topView1_p1 = (View)    findViewById(R.id.topView1_p1);
        topView2_p1 = (View)    findViewById(R.id.topView2_p1);
        contact_logo_p1 = (ImageView) findViewById(R.id.contact_logo_p1);
        subLogo_p1 = (TextView) findViewById(R.id.subLogo_p1);
        email_p1 = (TextView) findViewById(R.id.email_p1);
        password_p1 = (TextView) findViewById(R.id.pass_p1);
        loginButton_p1 = (Button)   findViewById(R.id.loginButton_p1);
        forgetPass_p1 = (TextView)  findViewById(R.id.forgetPass_p1);
        registerBtn_p1 = (Button)   findViewById(R.id.registerBtn_p1);
        accountHavingTxt_p1 = (TextView)    findViewById(R.id.accountHavingTxt_p1);
        subofsub_UI2p1 = (TextView) findViewById(R.id.subofsub_UI2p1);
        divider_UI = (View) findViewById(R.id.divider_UI2);
        lottieAnimationView2 = findViewById(R.id.lottieAnimationView2);




        loginButton_p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email_p1.getText().toString().equals("19bcs047@mcet.in")&&
                    password_p1.getText().toString().equals("1234")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setIcon(R.drawable.ic_baseline_lock_open_24);
                    builder.setTitle("Login Successfull !");
                    builder.setMessage("Your Soil Moisture level is being Monitored.");

                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"invalid Username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lottieAnimationView2.animate().setDuration(15000).setStartDelay(8000);
    }
}