package com.example.muhammadusama.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class userdefine_Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction()=="com.example.muhammadusama.broadcast_receivers.custominent")
        {
            Toast.makeText(context, "custom intent received", Toast.LENGTH_LONG).show();
        }

    }
}
