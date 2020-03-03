package com.samu.status_wise_login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewALL extends AppCompatActivity implements ListView.OnItemClickListener{
    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewALL.this,"Fetching Data","Wait...",false,false);
            }


            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }



            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }



    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String name = jo.getString(Config.TAG_NAME);
                String username = jo.getString(Config.TAG_USERNAME);
                String email = jo.getString(Config.TAG_EMAIL);
                String password = jo.getString(Config.TAG_PASSWORD);
                String status = jo.getString(Config.TAG_STATUS);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_ID,id);
                employees.put(Config.TAG_NAME,name);
                employees.put(Config.TAG_USERNAME,username);
                employees.put(Config.TAG_EMAIL,email);
                employees.put(Config.TAG_PASSWORD,password);
                employees.put(Config.TAG_STATUS,status);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewALL.this, list, R.layout.list_item,
                new String[]{Config.TAG_ID,Config.TAG_NAME,Config.TAG_USERNAME,Config.TAG_EMAIL,Config.TAG_PASSWORD,Config.TAG_STATUS},
                new int[]{R.id.id, R.id.name, R.id.username,R.id.email,R.id.password,R.id.status});

        listView.setAdapter(adapter);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Admin_Edit_Panel.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.EMP_ID,empId);
        startActivity(intent);
    }
}
