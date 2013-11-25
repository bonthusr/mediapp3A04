package com.example.mediapp;
 
import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
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
 
public class EditPatientActivity extends Activity {
 
    EditText txtFirst;
    EditText txtLast;
    EditText txtGender;
    EditText txtDOB;
    EditText txtAge;
    EditText txtHealthNum;
    EditText txtSIN;
    EditText txtHomeNum;
    EditText txtCellNum;
    EditText txtAddress;
    EditText txtCity;
    EditText txtZip;
    EditText txtDoctor;
    EditText txtNurse;

    Button btnSave;
    Button btnDelete;
 
    String ID;
 
    // Progress Dialog
    private ProgressDialog pDialog;
 
    JSONParser jsonParser = new JSONParser();
    private static final String url_patient_details = "http://10.0.2.2/view_patient.php";
    private static final String url_update_patient = "http://10.0.2.2/edit_patient.php";
    private static final String url_delete_patient = "http://10.0.2.2/delete_patient.php";
 
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ID = "ID";
    private static final String TAG_PATIENT = "patient";
    private static final String TAG_First = "First";
    private static final String TAG_Last = "Last";
    private static final String TAG_Gender = "Gender";
    private static final String TAG_DOB = "DOB";
    private static final String TAG_Age = "Age";
    private static final String TAG_HealthNum = "HealthNum";
    private static final String TAG_SIN = "SIN";
    private static final String TAG_HomeNum = "HomeNum";
    private static final String TAG_CellNum = "CellNum";
    private static final String TAG_Address = "Address";
    private static final String TAG_City = "City";
    private static final String TAG_Zip = "Zip";
    private static final String TAG_Doctor = "Doctor";
    private static final String TAG_Nurse = "Nurse";
    
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_patient);
 
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
 
        Intent i = getIntent();
        ID = i.getStringExtra(TAG_ID);
        new GetPatientDetails().execute();
 
        btnSave.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                new SavePatientDetails().execute();
            }
        });
 
        btnDelete.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                new DeletePatient().execute();
            }
        });
 
    }
 
    /**
     * Background Async Task to Get complete patient details
     * */
    class GetPatientDetails extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(EditPatientActivity.this);
            pDialog.setMessage("Loading patient details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        /**
         * Getting patient details in background thread
         * */
        protected String doInBackground(String... params) {
 
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    int success;
                    try {
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("ID", ID));
                        JSONObject json = jsonParser.makeHttpRequest(
                                url_patient_details, "GET", params);
                        Log.d("Single Patient Details", json.toString());
 
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            JSONArray patientObj = json
                            		.getJSONArray(TAG_PATIENT); 
                            JSONObject patient = patientObj.getJSONObject(0);
 
                            txtFirst = (EditText) findViewById(R.id.inputFirst);
                            txtLast = (EditText) findViewById(R.id.inputLast);
                            txtGender = (EditText) findViewById(R.id.inputGender);
                            txtDOB = (EditText) findViewById(R.id.inputDOB);
                            txtAge = (EditText) findViewById(R.id.inputAge);
                            txtHealthNum = (EditText) findViewById(R.id.inputHealthNum);
                            txtSIN = (EditText) findViewById(R.id.inputSIN);
                            txtHomeNum = (EditText) findViewById(R.id.inputHomeNum);
                            txtCellNum = (EditText) findViewById(R.id.inputCellNum);
                            txtAddress = (EditText) findViewById(R.id.inputAddress);
                            txtCity = (EditText) findViewById(R.id.inputCity);
                            txtZip = (EditText) findViewById(R.id.inputZip);
                            txtDoctor = (EditText) findViewById(R.id.inputDoctor);
                            txtNurse = (EditText) findViewById(R.id.inputNurse);
                            
                            txtFirst.setText(patient.getString(TAG_First));
                            txtLast.setText(patient.getString(TAG_Last));
                            txtGender.setText(patient.getString(TAG_Gender));
                            txtDOB.setText(patient.getString(TAG_DOB));
                            txtAge.setText(patient.getString(TAG_Age));
                            txtHealthNum.setText(patient.getString(TAG_HealthNum));
                            txtSIN.setText(patient.getString(TAG_SIN));
                            txtHomeNum.setText(patient.getString(TAG_HomeNum));
                            txtCellNum.setText(patient.getString(TAG_CellNum));
                            txtAddress.setText(patient.getString(TAG_Address));
                            txtCity.setText(patient.getString(TAG_City));
                            txtZip.setText(patient.getString(TAG_Zip));
                            txtDoctor.setText(patient.getString(TAG_Doctor));
                            txtNurse.setText(patient.getString(TAG_Nurse));
 
                        }else{
                            // product with Last not found
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
 
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }
 
    /**
     * Background Async Task to  Save patient Details
     * */
    class SavePatientDetails extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(EditPatientActivity.this);
            pDialog.setMessage("Saving patient ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        /**
         * Saving patient
         * */
        protected String doInBackground(String... args) {
 
            String First = txtFirst.getText().toString();
            String Last = txtLast.getText().toString();
            String Gender = txtGender.getText().toString();
            String DOB = txtDOB.getText().toString();
            String Age = txtAge.getText().toString();
            String HealthNum = txtHealthNum.getText().toString();
            String SIN = txtSIN.getText().toString();
            String HomeNum = txtHomeNum.getText().toString();
            String CellNum = txtCellNum.getText().toString();
            String Address = txtAddress.getText().toString();
            String City = txtCity.getText().toString();
            String Zip = txtZip.getText().toString();
            String Doctor = txtDoctor.getText().toString();
            String Nurse = txtNurse.getText().toString();
            
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_ID, ID));
            params.add(new BasicNameValuePair(TAG_First, First));
            params.add(new BasicNameValuePair(TAG_Last, Last));
            params.add(new BasicNameValuePair(TAG_Gender, Gender));
            params.add(new BasicNameValuePair(TAG_DOB, DOB));
            params.add(new BasicNameValuePair(TAG_Age, Age));
            params.add(new BasicNameValuePair(TAG_HealthNum, HealthNum));
            params.add(new BasicNameValuePair(TAG_SIN, SIN));
            params.add(new BasicNameValuePair(TAG_HomeNum, HomeNum));
            params.add(new BasicNameValuePair(TAG_CellNum, CellNum));
            params.add(new BasicNameValuePair(TAG_Address, Address));
            params.add(new BasicNameValuePair(TAG_City, City));
            params.add(new BasicNameValuePair(TAG_Zip, Zip));
            params.add(new BasicNameValuePair(TAG_Doctor, Doctor));
            params.add(new BasicNameValuePair(TAG_Nurse, Nurse));
 
            JSONObject json = jsonParser.makeHttpRequest(url_update_patient,
                    "POST", params);
            System.out.println(params);
            System.out.println(json);

            try {
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    Intent i = getIntent();
                    setResult(100, i);
                    finish();
                } else {
                    // failed to update patient
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
 
    /*****************************************************************
     * Background Async Task to Delete Product
     * */
    class DeletePatient extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(EditPatientActivity.this);
            pDialog.setMessage("Deleting Patient...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        /**
         * Deleting patient
         * */
        protected String doInBackground(String... args) {
 
            int success;
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("ID", ID));
 
                JSONObject json = jsonParser.makeHttpRequest(
                        url_delete_patient, "POST", params);
 
                Log.d("Delete Patient", json.toString());
 
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Intent i = getIntent();
                    setResult(100, i);
                    finish();
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