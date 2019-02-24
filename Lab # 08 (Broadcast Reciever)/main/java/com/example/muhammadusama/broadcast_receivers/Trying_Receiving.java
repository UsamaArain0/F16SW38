package com.example.muhammadusama.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.*;
import android.widget.Toast;

public class Trying_Receiving extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


            Bundle pudsBundle = intent.getExtras();
            Object[] pdus = (Object[]) pudsBundle.get("pdus");
            SmsMessage messages =SmsMessage.createFromPdu((byte[]) pdus[0]);

            // Start Application's  MainActivty activity

            Intent smsIntent=new Intent(context,MainActivity.class);

            smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            smsIntent.putExtra("MessageNumber", messages.getOriginatingAddress());

            smsIntent.putExtra("Message", messages.getMessageBody());

            context.startActivity(smsIntent);

            // Get the Sender Message : messages.getMessageBody()
            // Get the SenderNumber : messages.getOriginatingAddress()
        String messageRecieved= messages.getMessageBody();

        String search  = "Hello";

        if ( messageRecieved.toLowerCase().indexOf(search.toLowerCase()) != -1 ) {

            Toast.makeText(context, "I found the keyword "+search, Toast.LENGTH_LONG).show();


        } else {

            Toast.makeText(context, "Word "+search+" not found", Toast.LENGTH_LONG).show();


        }

            Toast.makeText(context, "SMS Received From :"+messages.getOriginatingAddress()+"\n"+ messages.getMessageBody(), Toast.LENGTH_LONG).show();


    }
}
