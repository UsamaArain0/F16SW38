package com.example.muhammadusama.studentattendence;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

public class QR_code_scanner extends AppCompatActivity {
    DatabaseReference scheduleReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference attendenceReference;
    DatabaseReference referenceToAttendence;
    SharedPreferences mySharedPreferences;
    SurfaceView cameraPreview;
    DatabaseReference databaseReferenceForID;
    TextView textResult;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    FirebaseAuth studentAuth;
    String current_user_id;
    boolean isKeyFound = true;
    String scannResult;
    // FloatingActionButton floatingActionButton;
    Camera camera;
    final int RequestCameraPermissionID = 1001;

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
////        final String todays_date = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(System.currentTimeMillis());
//        final String current_time = new SimpleDateFormat("hh:mm:ss", Locale.ENGLISH).format(System.currentTimeMillis());
//         final String[] scanMin = current_time.split(":");
////        final String todays_day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());
//
//
//     mySharedPreferences = this.getContext().getSharedPreferences("My Preference", MODE_PRIVATE);
//
//
//        View view = inflater.inflate(R.layout.qr_scanner_layout, container, false);
//        cameraPreview = view.findViewById(R.id.cameraPreview);
//        Firebase.setAndroidContext(getContext());
//
//        studentAuth = FirebaseAuth.getInstance();
//        scheduleReference = FirebaseDatabase.getInstance().getReference().child("ClassSchedule");
//        //attendenceReference = FirebaseDatabase.getInstance().getReference().child("Attendence").setValue("willyoumarryme");
//        referenceToAttendence = FirebaseDatabase.getInstance().getReferenceFromUrl("https://attendence-ce242.firebaseio.com/");
//
//        barcodeDetector = new BarcodeDetector.Builder(getContext()).setBarcodeFormats(Barcode.QR_CODE).build();
//        cameraSource = new CameraSource.Builder(getContext(), barcodeDetector).setRequestedPreviewSize(1920, 1080).setAutoFocusEnabled(true).build();
//        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    //Request Permission
//                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
//                    return;
//                }
//                try {
//                    cameraSource.start(cameraPreview.getHolder());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                cameraSource.stop();
//
//            }
//        });
//        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
//            @Override
//            public void release() {
//                Toast.makeText(getContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void receiveDetections(Detector.Detections<Barcode> detections) {
//                final SparseArray<Barcode> qrcode = detections.getDetectedItems();
//                // fetch data
//
//                if (qrcode.size() != 0) {
//                    cameraPreview.post(new Runnable() {
//
//
//                        @Override
//                        public void run() {
//
//                            String[] parts = qrcode.valueAt(0).displayValue.split(" ");
//                             String splitGenerated[]=parts[2].split(":");
//// Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
////                            vibrator.vibrate(700);
////                            //textResult.setText(qrcode.valueAt(0).displayValue);
//////                           Toast.makeText(getContext(), "Subject " + qrcode.valueAt(0).displayValue.toString(), Toast.LENGTH_SHORT).show();
////                            scannResult = qrcode.valueAt(0).displayValue.trim();
////
////                            //Toast.makeText(getContext(), scannResult, Toast.LENGTH_SHORT).show();
////
////                            String[] parts = scannResult.split(" ");
////
////
////
////                            if(parts[3].equals("考勤系統"))
////                            { referenceToAttendence.child("Attendence").child(""+parts[1]).child(""+parts[0]).child(""+studentAuth.getUid()).setValue(""+parts[2]);}
////                            else
////                            {Toast.makeText(getContext(), "Humaray sath Hoshyaari  ??  "+parts[3], Toast.LENGTH_SHORT).show();}
//                            //
//                            //   referenceToAttendence.child("Attendence").child("" + todays_date).child("" + scannResult).child("" + mySharedPreferences.getString("name", null)).setValue("" + current_time);
//
//                            //  Toast.makeText(getContext(), "todays_day "+todays_day, Toast.LENGTH_SHORT).show();
//                            // Toast.makeText(getContext(), "todays_date "+todays_date, Toast.LENGTH_SHORT).show();
//                            // Toast.makeText(getContext(),   "current_time "+current_time, Toast.LENGTH_SHORT).show();
//                            // todaySubject();
//
//
//                            //Try this too
//
//                            if (parts.length == 4 && checkTimeDifference(Integer.parseInt(scanMin[1]),Integer.parseInt(splitGenerated[1]))) {
//
//                                if (parts[3].equals("考勤系統")) {
//                                    referenceToAttendence.child("Attendence").child("" + parts[1]).child("" + parts[0]).child("" + mySharedPreferences.getString("name",null)).setValue("" + parts[2]);
//
//                                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
//                                    vibrator.vibrate(200);
//                                    Toast.makeText(getContext(), "Attendence submitted Successfully ", Toast.LENGTH_SHORT).show();
//
//                                }
//
//
//                            } else {
//                                Toast.makeText(getContext(), "Humaray sath Hoshyaari  ??  " , Toast.LENGTH_SHORT).show();
//                            }
//
//
//                        }
//                    });
////                    if (isKeyFound) {
////                        //  attendenceReference.child(todays_date).child(qrcode.valueAt(0).toString()).child(studentAuth.getUid());
////                        referenceToAttendence.child("Attendence").child(""+todays_date).child(""+scannResult).child(""+mySharedPreferences.getString("name",null)).setValue(""+current_time);
////                    }
//
//                }
//                //  Toast.makeText(getContext(),"yes detection recieve "+qrcode.valueAt(0),Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //   DatabaseReference referenceToStudentINfo= FirebaseDatabase.getInstance().getReferenceFromUrl("https://attendence-ce242.firebaseio.com/Student/"+studentAuth.getUid().trim()+"/Student_ID");
//
//
//        return view;
//
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(QR_code_scanner.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    try {
                        cameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_scanner_layout);
        final String current_time = new SimpleDateFormat("hh:mm:ss", Locale.ENGLISH).format(System.currentTimeMillis());
        final String[] scanMin = current_time.split(":");
        cameraPreview = findViewById(R.id.cameraPreview);
        mySharedPreferences = getSharedPreferences("My Preference", MODE_PRIVATE);
        Firebase.setAndroidContext(this);
        studentAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.qrtoolbar);
        setSupportActionBar(toolbar);


        if( studentAuth.getCurrentUser() !=null ) {

            databaseReferenceForID = FirebaseDatabase.getInstance().getReference("Student").child(studentAuth.getUid()).child("Student_ID");
            databaseReferenceForID.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
//                   sendItToMe = dataSnapshot.getValue().toString();
//                   Toast.makeText(Navigator.this, "sendItToMe "+sendItToMe, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = QR_code_scanner.this.getSharedPreferences("My Preference", MODE_PRIVATE).edit();
                    editor.putString("name", dataSnapshot.getValue().toString().trim());
                    editor.apply();

                    // Toast.makeText(Navigator.this, sendItToMe, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }else{
            finish();
        }


        scheduleReference = FirebaseDatabase.getInstance().getReference().child("ClassSchedule");
        //attendenceReference = FirebaseDatabase.getInstance().getReference().child("Attendence").setValue("willyoumarryme");
      //  referenceToAttendence = FirebaseDatabase.getInstance().getReferenceFromUrl("https://attendence-ce242.firebaseio.com/");
          referenceToAttendence = FirebaseDatabase.getInstance().getReferenceFromUrl("https://attendence-51b5c.firebaseio.com/");

        barcodeDetector = new BarcodeDetector.Builder(QR_code_scanner.this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(QR_code_scanner.this, barcodeDetector).setRequestedPreviewSize(1920, 1080).setAutoFocusEnabled(true).build();
        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(QR_code_scanner.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //Request Permission
                    ActivityCompat.requestPermissions(QR_code_scanner.this, new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                    return;
                }
                try {
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();

            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Toast.makeText(QR_code_scanner.this, "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcode = detections.getDetectedItems();
                // fetch data

                if (qrcode.size() != 0) {
                    cameraPreview.post(new Runnable() {


                        @Override
                        public void run() {

                            String[] parts = qrcode.valueAt(0).displayValue.split(" ");
                            String splitGenerated[] = parts[2].split(":");
// Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
//                            vibrator.vibrate(700);
//                            //textResult.setText(qrcode.valueAt(0).displayValue);
////                           Toast.makeText(getContext(), "Subject " + qrcode.valueAt(0).displayValue.toString(), Toast.LENGTH_SHORT).show();
//                            scannResult = qrcode.valueAt(0).displayValue.trim();
//
//                            //Toast.makeText(getContext(), scannResult, Toast.LENGTH_SHORT).show();
//
//                            String[] parts = scannResult.split(" ");
//
//
//
//                            if(parts[3].equals("考勤系統"))
//                            { referenceToAttendence.child("Attendence").child(""+parts[1]).child(""+parts[0]).child(""+studentAuth.getUid()).setValue(""+parts[2]);}
//                            else
//                            {Toast.makeText(getContext(), "Humaray sath Hoshyaari  ??  "+parts[3], Toast.LENGTH_SHORT).show();}
                            //
                            //   referenceToAttendence.child("Attendence").child("" + todays_date).child("" + scannResult).child("" + mySharedPreferences.getString("name", null)).setValue("" + current_time);

                            //  Toast.makeText(getContext(), "todays_day "+todays_day, Toast.LENGTH_SHORT).show();
                            // Toast.makeText(getContext(), "todays_date "+todays_date, Toast.LENGTH_SHORT).show();
                            // Toast.makeText(getContext(),   "current_time "+current_time, Toast.LENGTH_SHORT).show();
                            // todaySubject();


                            //Try this too

                            if (parts.length == 4 && checkTimeDifference(Integer.parseInt(scanMin[1]), Integer.parseInt(splitGenerated[1]))) {

                                if (parts[3].equals("考勤系統")) {
                                    referenceToAttendence.child("Attendence").child("" + parts[1]).child("" + parts[0]).child("" + mySharedPreferences.getString("name", null)).setValue("" + parts[2]);

                                    Vibrator vibrator = (Vibrator) QR_code_scanner.this.getSystemService(Context.VIBRATOR_SERVICE);
                                    vibrator.vibrate(200);
                                    Toast.makeText(QR_code_scanner.this, "Attendence submitted Successfully ", Toast.LENGTH_SHORT).show();

                                }


                            } else {
                                Toast.makeText(QR_code_scanner.this, "Humaray sath Hoshyaari  ??  ", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
//                    if (isKeyFound) {
//                        //  attendenceReference.child(todays_date).child(qrcode.valueAt(0).toString()).child(studentAuth.getUid());
//                        referenceToAttendence.child("Attendence").child(""+todays_date).child(""+scannResult).child(""+mySharedPreferences.getString("name",null)).setValue(""+current_time);
//                    }

                }
                //  Toast.makeText(getContext(),"yes detection recieve "+qrcode.valueAt(0),Toast.LENGTH_SHORT).show();
            }
        });

        //   DatabaseReference referenceToStudentINfo= FirebaseDatabase.getInstance().getReferenceFromUrl("https://attendence-ce242.firebaseio.com/Student/"+studentAuth.getUid().trim()+"/Student_ID");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.navigator,menu);
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        cameraSource.stop();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();
        onResume();

    }

    public void currentDayAndTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMMMM-yyyy");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        DateFormat dayFormat = new SimpleDateFormat("EEEEE");
        Date date = new Date();
        System.out.println("Date " + dateFormat.format(date));
        System.out.println("Day " + dayFormat.format(date));
        System.out.println("Time " + timeFormat.format(date));
    }

//    public void insertDataIntoDatabase(String todaysDate, String scannedTime, String student_id, String whichSubject) {
//
//        DatabaseReference insertTODB = attendenceReference.child(todaysDate).child(whichSubject).child(student_id);
//        insertTODB.setValue(scannedTime);
//    }

//    public void todaySubject() {
//
//
//        DateFormat dayFormat = new SimpleDateFormat("EEEEE", Locale.ENGLISH);
//        Date date = new Date();
//        final String currentDay = dayFormat.format(System.currentTimeMillis());
//
//        //String ss = new SimpleDateFormat("EE yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
//        final String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());
//        Toast.makeText(getContext(), weekday_name, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getContext(), currentDay, Toast.LENGTH_SHORT).show();
//
//
//// Assuming that you need date and time in a separate
//// textview named txt_date and txt_time.
//
//
//        scheduleReference.orderByKey().equalTo(weekday_name).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot == null || dataSnapshot.getChildren() == null) {
//                    //Key does not exist
//                    Toast.makeText(getContext(), "No such key exists", Toast.LENGTH_SHORT).show();
//                    isKeyFound = false;
//                } else {
//                    //Key exists
//                    Toast.makeText(getContext(), "Yes key " + weekday_name + " exists", Toast.LENGTH_SHORT).show();
//                    isKeyFound = true;
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        });
//
////    }
//    public void isNetworkAvailable(){
//
//        ConnectivityManager connMgr = (ConnectivityManager) getActivity()
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//
//        if (networkInfo != null && networkInfo.isConnected()) {
//            // fetch data
//        } else {
//            // display error
//        }
//    }

    public boolean checkTimeDifference(int scanTime, int generatedTime) {

        if (scanTime - generatedTime <= 10) {
            return true;
        } else
            return false;
    }
    boolean doubleBackToExitPressedOnce = false;


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
           finish();
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.about_us:
                Toast.makeText(this, "about us", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.signout:
                String current_user=  studentAuth.getCurrentUser().getEmail();
                studentAuth.signOut();
                Toast.makeText(this, "Student :"+current_user +" SIGN OUT", Toast.LENGTH_SHORT).show();
           //     Toast.makeText(this, , Toast.LENGTH_SHORT).show();

//                Intent signout= new Intent(QR_code_scanner.this,SignIN.class);
//                finish();

                Intent signout= new Intent(QR_code_scanner.this,SignIN.class);
                signout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // signout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(signout);
            //    QR_code_scanner.this.finish();

                //                finish();

//               startActivity(signout);

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
