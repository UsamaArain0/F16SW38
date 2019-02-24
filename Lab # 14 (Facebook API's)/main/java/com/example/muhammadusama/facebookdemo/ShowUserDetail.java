package com.example.muhammadusama.facebookdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ShowUserDetail extends AppCompatActivity {

    TextView user_name;
    ImageView user_photo;
    JSONObject response,profile_pic_url,profile_pic_data ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        user_name = findViewById(R.id.facebook_user_name);
        user_photo= findViewById(R.id.facebook_user_photo);
//        user_email= findViewById(R.id.facebook_user_email);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String jsondata = intent.getStringExtra("userProfile");
//        Log.w("Jsondata", jsondata);

        //user_photo.setImageResource("Set image resource here");

        try {
            response = new JSONObject(jsondata);
//            user_email.setText(response.get("name").toString());
            user_name.setText(response.get("name").toString());
            profile_pic_data  = new JSONObject(response.get("picture").toString());
            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
            Picasso.get().load(profile_pic_url.getString("url"))
                    .into(user_photo);


        } catch(Exception e){
            e.printStackTrace();
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Share something with your friends", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        onButtonClick();
            }
        });
    }

    public void onButtonClick(){

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        Uri screenshotUri = Uri.parse("android.resource://com.example.muhammadusama.facebookdemo/*");

        try {
            InputStream stream = getContentResolver().openInputStream(screenshotUri);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        sharingIntent.setType("image/jpeg");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
        startActivity(Intent.createChooser(sharingIntent, "Share image using"));
    }

}


