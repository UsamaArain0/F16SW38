package com.example.muhammadusama.trythistoo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskExample extends AsyncTask<Void,Integer,String> {
    AsyncTaskExample mContext= this;
    ProgressDialog progressDialog  ;
    TextView l;
    public AsyncTaskExample(TextView l,ProgressDialog myProgress){
        this.l=l;
        this.progressDialog=myProgress;
    }
    @Override
    protected String doInBackground(Void... v) {

        for(int i=0;i<10;i++)
        {
            publishProgress(i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Task Completed Successfully";
    }


    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Downloading in Progress");
        progressDialog.show();
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
        l.setText(s);
        super.onPostExecute(s);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        l.setText(values[0].toString());
       // progressDialog.setProgress(values[0]);

        super.onProgressUpdate(values);
    }
}

