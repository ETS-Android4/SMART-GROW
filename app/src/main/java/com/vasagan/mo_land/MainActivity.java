package com.vasagan.mo_land;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    EditText email_p1;
    EditText password_p1;
    Button loginButton_p1;
    Button SignupBtn_p1;
    LottieAnimationView lottieAnimationView2;
    ProgressBar progressBar_lgpg;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        lottieAnimationView2 = findViewById(R.id.lottieAnimationView2);
        lottieAnimationView2.animate().setDuration(15000).setStartDelay(8000);


        email_p1 = (EditText) findViewById(R.id.email_p1);
        password_p1 = (EditText) findViewById(R.id.pass_p1);
        progressBar_lgpg = (ProgressBar) findViewById(R.id.progressbar_lgpg);
        loginButton_p1 = (Button) findViewById(R.id.loginButton_p1);
        SignupBtn_p1 = (Button) findViewById(R.id.SignupBtn_p1);


        //onclicklistener for SignupButton
        SignupBtn_p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void loginBtn(View v) {

        String email_MA = email_p1.getText().toString().trim();
        String password_MA = password_p1.getText().toString().trim();
        email_p1.setText("");
        password_p1.setText("");

        mAuth.signInWithEmailAndPassword(email_MA,password_MA).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if (mAuth.getCurrentUser().isEmailVerified()) {
                        Toast.makeText(MainActivity.this, "Login Successsfull!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, Dashboard.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this,"Check Your Gmail for Verification Link!",Toast.LENGTH_LONG).show();


                    }
                }else{
                    Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}





