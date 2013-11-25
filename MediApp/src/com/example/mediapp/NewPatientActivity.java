package com.example.mediapp;
 
import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
 
public class NewPatientActivity extends Activity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
 
    JSONParser jsonParser = new JSONParser();
    EditText inputFirst;
    EditText inputLast;
    EditText inputGender;
    EditText inputDOB;
    EditText inputAge;
    EditText inputHealthNum;
    EditText inputSIN;
    EditText inputHomeNum;
    EditText inputCellNum;
    EditText inputAddress;
    EditText inputCity;
    EditText inputZip;
    EditText inputDoctor;
    EditText inputNurse;
 
    private static String url_create_patient = "http://10.0.2.2/add_patient.php";
    private static final String TAG_SUCCESS = "success";
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient);
 
        inputFirst = (EditText) findViewById(R.id.inputFirst);
        inputLast = (EditText) findViewById(R.id.inputLast);
        inputGender = (EditText) findViewById(R.id.inputGender);
        inputDOB = (EditText) findViewById(R.id.inputDOB);
        inputAge = (EditText) findViewById(R.id.inputAge);
        inputHealthNum = (EditText) findViewById(R.id.inputHealthNum);
        inputSIN = (EditText) findViewById(R.id.inputSIN);
        inputHomeNum = (EditText) findViewById(R.id.inputHomeNum);
        inputCellNum = (EditText) findViewById(R.id.inputCellNum);
        inputAddress = (EditText) findViewById(R.id.inputAddress);
        inputCity = (EditText) findViewById(R.id.inputCity);
        inputZip = (EditText) findViewById(R.id.inputZip);
        inputDoctor = (EditText) findViewById(R.id.inputDoctor);
        inputNurse = (EditText) findViewById(R.id.inputNurse);
 
        
        Button btnAddPatient2 = (Button) findViewById(R.id.btnAddPatient2);
        btnAddPatient2.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // creating new product in background thread
                new CreateNewPatient().execute();
            }
        });
    }
 
    
    /**
     * Background Async Task to Create new patient
     * */
    class CreateNewPatient extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(NewPatientActivity.this);
            pDialog.setMessage("Creating Patient..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        /**
         * Creating patient
         * */
        protected String doInBackground(String... args) {
            String First= inputFirst.getText().toString();
            String Last= inputLast.getText().toString();
            String Gender= inputGender.getText().toString();
            String DOB= inputDOB.getText().toString();
            String Age= inputAge.getText().toString();
            String HealthNum= inputHealthNum.getText().toString();
            String SIN= inputSIN.getText().toString();
            String HomeNum= inputHomeNum.getText().toString();
            String CellNum= inputCellNum.getText().toString();
            String Address= inputAddress.getText().toString();
            String City= inputCity.getText().toString();
            String Zip= inputZip.getText().toString();
            String Doctor= inputDoctor.getText().toString();
            String Nurse= inputNurse.getText().toString();
 
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("First", First));
            params.add(new BasicNameValuePair("Last", Last));
            params.add(new BasicNameValuePair("Gender", Gender));
            params.add(new BasicNameValuePair("DOB", DOB));
            params.add(new BasicNameValuePair("Age", Age));
            params.add(new BasicNameValuePair("HealthNum", HealthNum));
            params.add(new BasicNameValuePair("SIN", SIN));
            params.add(new BasicNameValuePair("HomeNum", HomeNum));
            params.add(new BasicNameValuePair("CellNum", CellNum));
            params.add(new BasicNameValuePair("Address", Address));
            params.add(new BasicNameValuePair("City", City));
            params.add(new BasicNameValuePair("Zip", Zip));
            params.add(new BasicNameValuePair("Doctor", Doctor));
            params.add(new BasicNameValuePair("Nurse", Nurse));
            
            JSONObject json = jsonParser.makeHttpRequest(url_create_patient,
                    "POST", params);
            Log.d("Create Response", json.toString());
 
            try {
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    Intent i = new Intent(getApplicationContext(), AllPatientsActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
        }
 
    }
}