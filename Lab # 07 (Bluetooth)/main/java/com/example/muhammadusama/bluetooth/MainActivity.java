package com.example.muhammadusama.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button onButton,offButton,discoverable,pairedD;
    TextView status;
    ArrayList list = new ArrayList();
    ImageView imageView;
    BluetoothAdapter bluetoothAdapter;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        onButton=findViewById(R.id.onBtn);
        offButton=findViewById(R.id.offBtn);
        discoverable=findViewById(R.id.visibility);
        pairedD=findViewById(R.id.pairDevices);
        status=findViewById(R.id.status);
        imageView=findViewById(R.id.imageView);
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();



       if(bluetoothAdapter==null)
       {
           status.setText("Devices has no Bluetooth");
       }else
       {
           status.setText("Bluetooth is available");
       }

        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!bluetoothAdapter.isEnabled()) {
                    msgToast("Turning on Bluetooth...");
                    Intent on = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(on,0 );
                    imageView.setImageResource(R.drawable.ic_bluetooth_black_24dp);
                }else {
                    msgToast("Bluetooth already open");
                }
                }
        });
        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   if(bluetoothAdapter.isEnabled())
                   {
                       msgToast("Turning Bluetooth off...");
                       bluetoothAdapter.disable();
                       imageView.setImageResource(R.drawable.ic_bluetooth_disabled_black_24dp);
                     //  listView.removeView(listView)
                     //  listView.removeView();
                       list.clear();
                       final ArrayAdapter adapter = new  ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1, list);
                       listView.setAdapter(adapter);

                   }else{
                       msgToast("Bluetooth already off");
                   }
            }
        });
        discoverable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter.isEnabled()) {
                    if (!bluetoothAdapter.isDiscovering()) {
                        msgToast("Turning visibility on");
                        Intent discover = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                        startActivityForResult(discover, 0);
                    }

                }else{
                    msgToast("Turn on bluetooth first");
                }
            }
        });
        pairedD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(bluetoothAdapter.isEnabled())
               {

                    Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                   for(BluetoothDevice bt : pairedDevices) list.add(bt.getName());
                   Toast.makeText(getApplicationContext(), "Showing Paired Devices",Toast.LENGTH_SHORT).show();
                   final ArrayAdapter adapter = new  ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1, list);
                   listView.setAdapter(adapter);

               }
            }
        });
    }
    public void msgToast(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

}
