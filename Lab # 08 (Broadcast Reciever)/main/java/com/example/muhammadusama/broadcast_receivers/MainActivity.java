package com.example.muhammadusama.broadcast_receivers;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    userdefine_Broadcast userdefine_broadcast;
    TextView textView;
    Button msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // textView=findViewById(R.id.area);
        userdefine_broadcast= new userdefine_Broadcast();
        setContentView(R.layout.activity_main);
        msg=findViewById(R.id.btn_msg);
        IntentFilter intentFilter= new IntentFilter("com.example.muhammadusama.broadcast_receivers.custominent");
        registerReceiver(userdefine_broadcast,intentFilter);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String address = extras.getString("MessageNumber");
            String message = extras.getString("Message");
            TextView addressField = (TextView) findViewById(R.id.address);
            TextView messageField = (TextView) findViewById(R.id.message);
            addressField.setText("Message From : " +address);
            messageField.setText("Messsage : "+message);
        }
        msg = (Button)findViewById(R.id.btn_msg);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });




    }
    public void sndbtn(View view){
        Intent intent = new Intent("com.example.muhammadusama.broadcast_receivers.custominent");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(userdefine_broadcast);
        super.onDestroy();
    }
}
