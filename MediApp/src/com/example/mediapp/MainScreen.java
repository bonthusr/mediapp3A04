
package com.example.mediapp;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 
public class MainScreen extends Activity{
 
    Button btnViewPatients;
    Button btnAddPatient;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
 
        btnViewPatients = (Button) findViewById(R.id.btnViewPatients);
        btnAddPatient = (Button) findViewById(R.id.btnAddPatient);
 
        btnViewPatients.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AllPatientsActivity.class);
                startActivity(i);
 
            }
        });
 
        btnAddPatient.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NewPatientActivity.class);
                startActivity(i);
 
            }
        });
    }
}