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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class signup extends AppCompatActivity {

    FirebaseAuth mAuth;
    ImageView img_signup;
    TextView txt1_signup, txt2_signup;
    EditText Username_snUpET, Email_snUpET, Password_snUpET, address_snUpET, postalcode_snUpET, contactnum_snUpET;
    Button Register_snUp, bcktoMA_snUp;
    ProgressBar progressbar_snUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        progressbar_snUp = (ProgressBar) findViewById(R.id.progressbar_snUp);


        img_signup = (ImageView) findViewById(R.id.img_signup);
        txt1_signup = (TextView) findViewById(R.id.txt1_signup);
        txt2_signup = (TextView) findViewById(R.id.txt2_signup);

        Username_snUpET = (EditText) findViewById(R.id.Username_snUp);
        Email_snUpET = (EditText) findViewById(R.id.Email_snUp);
        Password_snUpET = (EditText) findViewById(R.id.Password_snUp);
        address_snUpET = (EditText) findViewById(R.id.address_snUp);
        postalcode_snUpET = (EditText) findViewById(R.id.postalcode_snUp);
        contactnum_snUpET = (EditText) findViewById(R.id.contactnum_snUp);

        Register_snUp = (Button) findViewById(R.id.Register_snUp);
        bcktoMA_snUp = (Button) findViewById(R.id.bcktoMA_snUp);

        bcktoMA_snUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void registerBtnSgnUpPg(View view) {
        String checkemail = "[a-zA-Z0-9'_']+@[a-z]+\\.+[a-z]+";
        String checkpassword = "^" +
                "(?=.*[0-9])" +      //atleast one digit
                "(?=.*[a-z])" +      //atleast one Lowercase letter
                "(?=.*[A-Z])" +      //atleast one Uppercase letter
                "(?=.*[a-zA-Z])" +   //any Letter
                "(?=\\S+$)" +        //no WhiteSpaces
                ".{4,}" +            //atleast 4 characters
                "$";
        String checkspaces = "\\A\\w{4,20}\\z";
        String phoneNumValidation = "\\d{10}";

        String username = Username_snUpET.getText().toString().trim();
        String email = Email_snUpET.getText().toString().trim();
        String password = Password_snUpET.getText().toString().trim();
        String address_snup = address_snUpET.getText().toString().trim();
        String postal_code = postalcode_snUpET.getText().toString().trim();
        String contactNumber = contactnum_snUpET.getText().toString();

        //VALIDATION
        //username validation
        if (TextUtils.isEmpty(username)) {
            Username_snUpET.setError("UserName is Required!");
            return;
        } else if (username.length() > 20) {
            Username_snUpET.setError("UserName is Too large!");
            return;
        }
        //address validation
        if (TextUtils.isEmpty(address_snup)) {
            address_snUpET.setError("Address is Required");
            return;
        }
        //email validation
        if (!email.matches(checkemail)) {
            Email_snUpET.setError("Enter a valid email!");
            return;
        } else if (email.matches(checkspaces)) {
            Email_snUpET.setError("No white spaces are allowed!");
            return;
        }
        //password validation
        if (TextUtils.isEmpty(password)) {
            Password_snUpET.setError("Password is Required");
            return;
        } else if (!password.matches(checkpassword)) {
            Password_snUpET.setError("Atleast one Digit, UpperCase and LowerCase is Required!");
            return;
        } else if (password.length() < 8) {
            Password_snUpET.setError("Password must be minimum 6 characters");
            return;
        }
        if (TextUtils.isEmpty(postal_code)) {
            postalcode_snUpET.setError("Provide your Postal Address");
            return;
        }
        //contactNumber validation
        if (TextUtils.isEmpty(contactNumber)) {
            contactnum_snUpET.setError("contact number is Required!");
            return;
        } else if (!contactNumber.matches(phoneNumValidation)) {
            contactnum_snUpET.setError("Enter a Valid Contact Number!");
        }


        //Passing all Edittext values to emailverify Page
        Intent i = new Intent(signup.this, verifyingUser.class);
        i.putExtra("uname", username);
        i.putExtra("mailid", email);
        i.putExtra("pass", password);
        i.putExtra("contactnum", contactNumber);
        i.putExtra("postal", postal_code);
        i.putExtra("address", address_snup);
        startActivity(i);
        finish();


    }


}


