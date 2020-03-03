package com.samu.status_wise_login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class ActivityLogin_for_own_profile extends AppCompatActivity implements View.OnClickListener{

    public static final String USER_NAME = "USER_NAME";


    //-----------extra--
    public static final String PASSWORD = "PASSWORD";

    public static final String STATUS = "STATUS";

    public static final String NAME = "NAME";

    public static final String EMAIL = "EMAIL";

    //--------------

  // private static final String LOGIN_URL = "http://stamasoft.com/android/regloginway2/login.php";

  //  private static final String LOGIN_URL = "http://192.168.56.1/android/android_professional/Reg_login_post_get/regloginway2/login.php";
    //private static final String LOGIN_URL = "http://127.0.0.1/android/android_professional/Reg_login_post_get/regloginway2/login.php";
    private static final String LOGIN_URL = "http://10.0.2.2/LifePro/Reg_login_post_get_with_status/personal_profile_alldata_rnd.php";






    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextStatus;
    private Button buttonLogin;


    String name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_login_for_own);

        editTextUserName = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextStatus = (EditText) findViewById(R.id.status);
        buttonLogin = (Button) findViewById(R.id.buttonUserLogin);



        buttonLogin.setOnClickListener(this);
    }


    private void login(){
        String username = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String status = editTextStatus.getText().toString().trim();
        userLogin(username,password,status,name,email);
    }

    private void userLogin(final String username, final String password,final String status,final String name,final String email){
        class UserLoginClass extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ActivityLogin_for_own_profile.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("success")){
                    Intent intent = new Intent(ActivityLogin_for_own_profile.this,UserProfile_own.class);
                    intent.putExtra(USER_NAME,username);

                    //----------extra add

                    intent.putExtra(PASSWORD,password);
                    intent.putExtra(STATUS,status);
                    intent.putExtra(NAME,name);
                    intent.putExtra(EMAIL,email);

                    //-----extra add-----------
                    startActivity(intent);
                }else{
                    Toast.makeText(ActivityLogin_for_own_profile.this,s,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("username",params[0]);
                data.put("password",params[1]);
                data.put("status",params[2]);
                data.put("name",params[3]);
               data.put("email",params[4]);

                RegisterUserClass_for_post_method ruc = new RegisterUserClass_for_post_method();

                String result = ruc.sendPostRequest(LOGIN_URL,data);

                return result;
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username,password,status,name,email);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogin){
            login();
        }
    }
}