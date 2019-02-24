package com.example.muhammadusama.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class ConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String messageBody = "";
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            Toast.makeText(context, "Network is connected", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Network is changed or reconnected", Toast.LENGTH_LONG).show();
        }
     /*
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                 messageBody = smsMessage.getMessageBody();

            }
               Toast.makeText(context,messageBody,Toast.LENGTH_LONG).show();
        }
*/
       /* if (intent != null) {
            String action = intent.getAction();
            if (action != null) {
                if (action.equals("android.provider.Telephony.SMS_RECEIVED")) {
                    // DO YOUR STUFF
                    Toast.makeText(context, "sms received", Toast.LENGTH_LONG).show();
                } else if (action.equals("ANOTHER ACTION")) {
                    // DO ANOTHER STUFF
                    Toast.makeText(context, "sms not received", Toast.LENGTH_LONG).show();

                }
            }


        }
        */
    }
}

