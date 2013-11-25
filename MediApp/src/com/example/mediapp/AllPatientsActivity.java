package com.example.mediapp;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
 
public class AllPatientsActivity extends ListActivity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
    
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> patientsList;
    private static String url_all_patients = "http://10.0.2.2/view_patients.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ID = "ID";
    private static final String TAG_PATIENTS = "patients";
    private static final String TAG_First = "First";
    private static final String TAG_Last = "Last";
    JSONArray patients = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_patients);

        patientsList = new ArrayList<HashMap<String, String>>();
        new LoadAllPatients().execute();
 
        ListView lv = getListView();
        lv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
                String ID = ((TextView) view.findViewById(R.id.ID)).getText()
                        .toString();

                Intent in = new Intent(getApplicationContext(),
                        EditPatientActivity.class);
                in.putExtra(TAG_ID, ID);
 
                startActivityForResult(in, 100);
            }
        });
 
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
 
    }
 
    /**
     * Background Async Task to Load all patients by making HTTP Request
     * */
    class LoadAllPatients extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AllPatientsActivity.this);
            pDialog.setMessage("Loading patients. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        /**
         * getting All patients from url
         * */
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = jParser.makeHttpRequest(url_all_patients, "GET", params);
            Log.d("All Patients: ", json.toString());
 
            try {
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    patients = json.getJSONArray(TAG_PATIENTS);
 

                    for (int i = 0; i < patients.length(); i++) {
                        JSONObject c = patients.getJSONObject(i);
                        String ID = c.getString(TAG_ID);
                        String Last = c.getString(TAG_Last);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(TAG_ID, ID);
                        map.put(TAG_Last, Last);
                        patientsList.add(map);
                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                    Intent i = new Intent(getApplicationContext(),
                            NewPatientActivity.class);
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
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
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    ListAdapter adapter = new SimpleAdapter(
                            AllPatientsActivity.this, patientsList,
                            R.layout.list_patient, new String[] { TAG_ID,
                                    TAG_Last},
                            new int[] { R.id.ID, R.id.Last });
                    setListAdapter(adapter);
                }
            });
 
        }
 
    }
}