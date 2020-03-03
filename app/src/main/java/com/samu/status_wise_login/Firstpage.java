package com.samu.status_wise_login;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Firstpage extends AppCompatActivity {

    Button b_post_reg,b_get_reg,b_loging_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);


        b_post_reg=(Button)findViewById(R.id.b_reg_post);
        b_get_reg=(Button)findViewById(R.id.b_reg_get);
        b_loging_post=(Button)findViewById(R.id.b_loging);




        //-----------------------
        b_post_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent bpost = new Intent(Firstpage.this,MainActivity_post_method.class);
                startActivity(bpost);

            }
        });


        //---------------------------


        b_get_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent bget = new Intent(Firstpage.this,MainActivity_get_method.class);
                startActivity(bget);

            }
        });



//--------------------------loging---



        b_loging_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent b_post_loging = new Intent(Firstpage.this,ActivityLogin.class);
                startActivity(b_post_loging);

            }
        });






    } //smb
}
