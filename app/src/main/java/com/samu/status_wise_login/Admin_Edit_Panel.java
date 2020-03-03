package com.samu.status_wise_login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Admin_Edit_Panel extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextNamead;
    private EditText editTextUsernamead;
    private EditText editTextPasswordad;
    private EditText editTextEmailad;
    private EditText statusad;
    private Button buttonRegisterAdd;
    private EditText editTextId;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__edit__panel);
        Intent intent = getIntent();
        id = intent.getStringExtra(Config.EMP_ID);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextNamead = (EditText) findViewById(R.id.editTextNameAD);
        editTextUsernamead = (EditText) findViewById(R.id.editTextUserNameAD);
        editTextPasswordad = (EditText) findViewById(R.id.editTextPasswordAD);
        editTextEmailad = (EditText) findViewById(R.id.editTextEmailAD);
        statusad = (EditText) findViewById(R.id.statusAd);


        editTextId.setText(id);

        buttonRegisterAdd = (Button) findViewById(R.id.buttonRegisterad);

        buttonRegisterAdd.setOnClickListener(this);
        getEmployee();

    }
    //--------------1---data show----
    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Admin_Edit_Panel.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Config.TAG_NAME);
            String username = c.getString(Config.TAG_USERNAME);
            String password = c.getString(Config.TAG_PASSWORD);
            String email = c.getString(Config.TAG_EMAIL);
            String status = c.getString(Config.TAG_STATUS);

            editTextNamead.setText(name);
            editTextUsernamead.setText(username);
            editTextPasswordad.setText(password);
            editTextEmailad.setText(email);
            statusad.setText(status);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//------------1-----data show----

    /*private void registerUser() {
        String name = editTextNamead.getText().toString().trim().toLowerCase();
        String username = editTextUsernamead.getText().toString().trim().toLowerCase();
        String password = editTextPasswordad.getText().toString().trim().toLowerCase();
        String email = editTextEmailad.getText().toString().trim().toLowerCase();
        String status = statusad.getText().toString().trim().toLowerCase();

        register(name,username,password,email,status);
    }

    private void register(String name, String username, String password, String email,String status) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass_for_post_method ruc = new RegisterUserClass_for_post_method();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Admin_Edit_Panel.this, "Please Wait",null, true, true);
            }


            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("name",params[0]);
                data.put("username",params[1]);
                data.put("password",params[2]);
                data.put("email",params[3]);
                data.put("status",params[4]);

                String result = ruc.sendPostRequest(Config.REGISTER_URL,data);

                return  result;
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name,username,password,email,status);
    }*/

    private void updateEmployee(){
        final String name = editTextNamead.getText().toString().trim();
        final String username = editTextUsernamead.getText().toString().trim().toLowerCase();
       final String password = editTextPasswordad.getText().toString().trim().toLowerCase();
       final String email = editTextEmailad.getText().toString().trim().toLowerCase();
      final  String status = statusad.getText().toString().trim().toLowerCase();


        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Admin_Edit_Panel.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Admin_Edit_Panel.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_EMP_ID,id);
                hashMap.put(Config.KEY_EMP_NAME,name);
                hashMap.put(Config.KEY_EMP_USERNAME,username);
                hashMap.put(Config.KEY_EMP_PASSWORD,password);
                hashMap.put(Config.KEY_EMP_EMAIL,email);
                hashMap.put(Config.KEY_EMP_STATUS,status);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    @Override
    public void onClick(View v) {
        if (v==buttonRegisterAdd){
            updateEmployee();
        }

    }
}
