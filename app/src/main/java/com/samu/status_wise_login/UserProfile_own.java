package com.samu.status_wise_login;

import android.content.Intent;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfile_own extends AppCompatActivity {

    private TextView textView ,tvstatus;

    //---add extra
    private EditText editText_username_v;

    private EditText editText_name_v;

    private EditText editText_email_v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_for_own);

        textView = (TextView) findViewById(R.id.textViewUserName);

        //---add extra
        editText_username_v= (EditText)findViewById(R.id.editText_username);

        tvstatus = (TextView) findViewById(R.id.textViewStatus);


        editText_name_v= (EditText)findViewById(R.id.editText_username);
        editText_email_v= (EditText)findViewById(R.id.editText_email);



        Intent intent = getIntent();

        String username = intent.getStringExtra(ActivityLogin_for_own_profile.USER_NAME);
        textView.setText("Welcome User "+username);

            //---add extra
        String password = intent.getStringExtra(ActivityLogin_for_own_profile.PASSWORD);


        editText_username_v.setText(""+password);


        //-----STATUS
        String mstatus = intent.getStringExtra(ActivityLogin_for_own_profile.STATUS);


        tvstatus.setText("Member Status "+mstatus);

        //---add name
        String name = intent.getStringExtra(ActivityLogin_for_own_profile.NAME);


        editText_name_v.setText(""+name);


        //---add EMAIL
        String email = intent.getStringExtra(ActivityLogin_for_own_profile.EMAIL);


        editText_email_v.setText(""+email);


    }

}