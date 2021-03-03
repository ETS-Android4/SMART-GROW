package com.vasagan.mo_land;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ImageView img_signup;
    private TextView txt1_signup, txt2_signup;
    private EditText Username_snUp, Email_snUp, Password_snUp, address_snUp, postalcode_snUp, contactnum_snUp;
    private Button Register_snUp, bcktoMA_snUp;
    private ProgressBar progressbar_snUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        img_signup = (ImageView) findViewById(R.id.img_signup);
        txt1_signup = (TextView) findViewById(R.id.txt1_signup);
        txt2_signup = (TextView) findViewById(R.id.txt2_signup);
        Username_snUp = (EditText) findViewById(R.id.Username_snUp);
        Email_snUp = (EditText) findViewById(R.id.Email_snUp);
        Password_snUp = (EditText) findViewById(R.id.Password_snUp);
        address_snUp = (EditText) findViewById(R.id.address_snUp);
        postalcode_snUp = (EditText) findViewById(R.id.postalcode_snUp);
        contactnum_snUp = (EditText) findViewById(R.id.contactnum_snUp);

        Register_snUp = (Button) findViewById(R.id.Register_snUp);
        bcktoMA_snUp = (Button) findViewById(R.id.bcktoMA_snUp);

        progressbar_snUp = (ProgressBar) findViewById(R.id.progressbar_snUp);
        if(mAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        bcktoMA_snUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Register_snUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= Username_snUp.getText().toString().trim();
                String Checkspace = "\\A\\w{4,20}\\z";
                String email = Email_snUp.getText().toString().trim();
                String password = Password_snUp.getText().toString().trim();
                String checkemail = "[a-zA-Z0-9'_']+@[a-z]+\\.+[a-z]+";
                String checkpassword =  "^"+
                                        "(?=.*[0-9])"+      //atleast one digit
                                        "(?=.*[a-z])"+      //atleast one Lowercase letter
                                        "(?=.*[A-Z])"+      //atleast one Lowerrcase letter
                                        "(?=.*[a-zA-Z])"+   //any Letter
                                        "(?=\\S+$)"+        //no WhiteSpaces
                                        ".{4,}"+            //atleast 4 characters
                                         "$";
                String address_snup = address_snUp.getText().toString();
                String postal_code = postalcode_snUp.getText().toString();
                String contactNumber = contactnum_snUp.getText().toString();

                //user name validation
                if(TextUtils.isEmpty(username)){
                    Username_snUp.setError("UserName is Required!");
                    return;
                }else if(username.length() > 20){
                    Username_snUp.setError("UserName is too large!");
                    return;
                }

                //email validation
                if (TextUtils.isEmpty(email)){
                    Email_snUp.setError("Email is Required!");
                    return;
                }else if(!email.matches(checkemail)){
                    Email_snUp.setError("Invalid Email!");
                    return;
                }
                else if(email.matches(Checkspace)){
                    Email_snUp.setError("No White Spaces are allowed!");
                }

                //password validation
                if(TextUtils.isEmpty(password)){
                    Password_snUp.setError("Password is Required!");
                    return;
                }else if(password.matches(checkpassword)){
                    Password_snUp.setError("atleast one Number,Upper and Lowercase Required!");

                }else if (password.length() < 6) {
                    Password_snUp.setError("Password must be minimum 6 characters");
                    return;
                }

                //address validation
                if(TextUtils.isEmpty(address_snup)){
                    address_snUp.setError("Address Required!");
                    return;
                }

                //postalcode validation
                if(TextUtils.isEmpty(postal_code)){
                    postalcode_snUp.setError("PostalCode Required!");
                    return;
                }

                //contactNumber validation
                if(TextUtils.isEmpty(contactNumber)){
                    contactnum_snUp.setError("ContactNumber Required!");
                    return;
                }else if(!(contactNumber.length() == 10)){
                    contactnum_snUp.setError("10 digit contact number is Required!");
                    return;
                }


                //enabling visibility of progress bar

                progressbar_snUp.setVisibility(View.VISIBLE);

                //registering user in firebase

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(signup.this, "user Succesfully Registered",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), userland.class));
                        }else{
                            Toast.makeText(signup.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressbar_snUp.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


    }


}

