package com.vasagan.mo_land;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class irrigation_status extends AppCompatActivity {

    EditText moistureET, temperatureET, cropET;
    TextView underIrrigatedMSG;
    String wheat, paddy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irrigation_status);

        moistureET = findViewById(R.id.moistureET);
        temperatureET = findViewById(R.id.temperatureET);
        underIrrigatedMSG = findViewById(R.id.underIrrigatedMSG);
        cropET = findViewById(R.id.cropET);



    }

    public void checkBTNLogic(View view) {


        String moisture1 = moistureET.getText().toString();
        String temperature1 = temperatureET.getText().toString();
        String crop = cropET.getText().toString();


        float moisture = Float.parseFloat(moisture1);
        float temperature = Float.parseFloat(temperature1);

            if (moisture1.isEmpty() || temperature1.isEmpty()) {
                Toast.makeText(irrigation_status.this, "Please enter soil moisture and temperature !", Toast.LENGTH_SHORT).show();
                return;

            } else if (moisture == 0 && temperature == 0) {                                        //if no values given
                underIrrigatedMSG.setText(getString(R.string.noValues));
                return;

            } else if (moisture < 10 && temperature > 35 && temperature < 100) {                                  //underIrrigated condition
                underIrrigatedMSG.setText(getString(R.string.underIrrigated));
                Toast.makeText(irrigation_status.this, "Irrigation Needed", Toast.LENGTH_SHORT).show();
                return;


            } else if ((moisture >= 10 && moisture <= 18) && (temperature >= 32 && temperature <= 35)) {        //Properly Irrigated condition

                underIrrigatedMSG.setText(getString(R.string.properlyIrrigated));
                Toast.makeText(irrigation_status.this, "All Good", Toast.LENGTH_SHORT).show();
                return;


            } else if (moisture > 18 && temperature > 1 && temperature < 32) {                                     //overIrrigated condition
                underIrrigatedMSG.setText(getString(R.string.overIrrigated));
                Toast.makeText(irrigation_status.this, "Irrigation Not Required for Now", Toast.LENGTH_SHORT).show();
                return;


            }
        }


        public void backtoDashBoard (View view){

            Intent intent = new Intent(irrigation_status.this, Dashboard.class);
            startActivity(intent);
            finish();
        }
    }