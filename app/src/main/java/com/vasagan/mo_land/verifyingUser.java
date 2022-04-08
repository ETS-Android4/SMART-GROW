package com.vasagan.mo_land;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.style.IconMarginSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.PasswordAuthentication;

public class verifyingUser extends AppCompatActivity {
    TextView usernameFrmSgnUpPg, mailidFrmSgnUpPg, passFrmSgnUpPg, contactnumFrmSgnPg;

    Button cnfrmRegisterBtn;


    FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String username, email, password, contactNumber, address_snup, postal_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifying_user);

        mAuth = FirebaseAuth.getInstance();

        usernameFrmSgnUpPg = (TextView) findViewById(R.id.usernameFrmSgnUpPg);
        mailidFrmSgnUpPg = (TextView) findViewById(R.id.mailidFrmSgnUpPg);
        passFrmSgnUpPg = (TextView) findViewById(R.id.passFrmSgnUpPg);
        contactnumFrmSgnPg = (TextView) findViewById(R.id.contactnumFrmSgnPg);


        cnfrmRegisterBtn = (Button) findViewById(R.id.cnfrmRegisterBtn);





        //getting info from signUpPage
        username = getIntent().getStringExtra("uname");
        email = getIntent().getStringExtra("mailid");
        password = getIntent().getStringExtra("pass");
        contactNumber = getIntent().getStringExtra("contactnum");
        address_snup = getIntent().getStringExtra("address");
        postal_code = getIntent().getStringExtra("postal");

        //setting recieved info to desired txtFields
        usernameFrmSgnUpPg.setText(username);
        mailidFrmSgnUpPg.setText(email);
        passFrmSgnUpPg.setText(password);
        contactnumFrmSgnPg.setText(contactNumber);


    }


    public void storeData(View view) {



        //saving values in realtimeDatabase

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");


        UserHelperClass helperClass = new UserHelperClass(username, email, password, contactNumber, address_snup, postal_code);
        Toast.makeText(this, "User Registered successfully!", Toast.LENGTH_SHORT).show();


        reference.child(username).setValue(helperClass);

        //sending Email to User
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(verifyingUser.this, "Verification link has sent to Your Gmail!", Toast.LENGTH_LONG);
                                mailidFrmSgnUpPg.setText("");
                                passFrmSgnUpPg.setText("");




                            } else {
                                Toast.makeText(verifyingUser.this, "Error Occured, Try Different Account!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {

                    Toast.makeText(verifyingUser.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(verifyingUser.this, MainActivity.class);
                startActivity(intent);
                finish();



            }
        });


    }
}




