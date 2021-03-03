package com.vasagan.mo_land;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private View topView1_p1;
    private View topView2_p1;
    private ImageView contact_logo_p1;
    private ImageView siLogo_p1;
    private TextView subLogo_p1;
   //
    private TextView email_p1;
    private TextView password_p1;
   //
    private Button loginButton_p1;
    private TextView forgetPass_p1;
    private Button SignupBtn_p1;
    private TextView accountHavingTxt_p1;
    private TextView subofsub_UI2p1;
    private View divider_UI;
    private LottieAnimationView lottieAnimationView2;
    private View divider2;
   private ProgressBar progressBar_lgpg;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();


        topView1_p1 = (View)    findViewById(R.id.topView1_p1);
        topView2_p1 = (View)    findViewById(R.id.topView2_p1);
        contact_logo_p1 = (ImageView) findViewById(R.id.contact_logo_p1);
        subLogo_p1 = (TextView) findViewById(R.id.subLogo_p1);
        forgetPass_p1 = (TextView)  findViewById(R.id.forgetPass_p1);
        accountHavingTxt_p1 = (TextView)    findViewById(R.id.accountHavingTxt_p1);
        subofsub_UI2p1 = (TextView) findViewById(R.id.subofsub_UI2p1);
        divider_UI = (View) findViewById(R.id.divider_UI2);
        lottieAnimationView2 = findViewById(R.id.lottieAnimationView2);
        divider2 = (View) findViewById(R.id.divider2_loginPage);
        lottieAnimationView2.animate().setDuration(15000).setStartDelay(8000);

        email_p1 = (TextView) findViewById(R.id.email_p1);
        password_p1 = (TextView) findViewById(R.id.pass_p1);
        progressBar_lgpg = (ProgressBar)findViewById(R.id.progressbar_lgpg);
        loginButton_p1 = (Button)   findViewById(R.id.loginButton_p1);
        SignupBtn_p1 = (Button)   findViewById(R.id.SignupBtn_p1);

        SignupBtn_p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
            }
        });



loginButton_p1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String email = email_p1.getText().toString().trim();
        String password = password_p1.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            email_p1.setError("email is required");
            return;
        }
        if(TextUtils.isEmpty(password)){
            password_p1.setError("Password is Required");
            return;
        }
        if(password.length() < 6){
            password_p1.setError("Password must be minimum 6 characters");
            return;
        }

        progressBar_lgpg.setVisibility(View.VISIBLE);
        //authentication

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Logged in Succesfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), userland.class));

                } else {
                    Toast.makeText(MainActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar_lgpg.setVisibility(View.GONE);
                }
            }});

}
    });
    }
}

