package com.example.muhammadusama.studentattendence;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Navigator extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

   TextView emailText,nameText;
   FirebaseAuth mAuth;
   DatabaseReference databaseReferenceForID;

  static String sendItToMe="";
 static String Email_Lable="";
 static String Name_Lable="";

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth= FirebaseAuth.getInstance();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view =navigationView.getHeaderView(0);
        emailText=view.findViewById(R.id.student_Email);
        nameText=view.findViewById(R.id.student_Name);

        FirebaseUser firebaseUser=mAuth.getCurrentUser();


        if(mAuth.getCurrentUser()!=null) {

            databaseReferenceForID = FirebaseDatabase.getInstance().getReference("Student").child(mAuth.getUid()).child("Student_ID");
            databaseReferenceForID.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange( com.google.firebase.database.DataSnapshot dataSnapshot) {
                    sendItToMe = dataSnapshot.getValue().toString();
                    Toast.makeText(Navigator.this, "sendItToMe "+sendItToMe, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = Navigator.this.getSharedPreferences("My Preference", MODE_PRIVATE).edit();
                 editor.putString("name", sendItToMe);
                      editor.apply();

                    // Toast.makeText(Navigator.this, sendItToMe, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        prefs = getSharedPreferences("My Preference", MODE_PRIVATE);
        navigationView.setNavigationItemSelectedListener(this);
        if(mAuth.getCurrentUser() == null){
            emailText.setText("User Email");
            nameText.setText("User Name/ID");
        if(savedInstanceState== null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_view, new StudentClass()).commit();
            navigationView.setCheckedItem(R.id.Student);

        }
        }else{
//            databaseReferenceForID = FirebaseDatabase.getInstance().getReference("Student").child(mAuth.getUid()).child("Student_ID");
//            databaseReferenceForID.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
//                @Override
//                public void onDataChange( com.google.firebase.database.DataSnapshot dataSnapshot) {
////                    sendItToMe = dataSnapshot.getValue().toString();
////                    SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("My Preference", MODE_PRIVATE).edit();
////                    editor.putString("name", sendItToMe);
////                    editor.apply();
////                    emailText.setText(mAuth.getCurrentUser().getEmail());
////                    nameText.setText(mAuth.getUid());
//
//                    // Toast.makeText(Navigator.this, sendItToMe, Toast.LENGTH_SHORT).show();
//               }
//
//                @Override
//               public void onCancelled(@NonNull DatabaseError databaseError) {
//
//               }
//           });
           // navigationView.setCheckedItem(R.id.Student);
            emailText.setText(firebaseUser.getEmail());
            nameText.setText(prefs.getString("name",null));

          //  getSupportFragmentManager().beginTransaction().replace(R.id.frame_view, new QR_code_scanner()).commit();

        }


       // emailText.setText(prefs.getString("email",null));
      //  nameText.setText(prefs.getString("name",null));

        if(mAuth.getCurrentUser() == null) {
//            emailText.setText("User Email");
//            nameText.setText("User Name/ID");
        }else{
//            emailText.setText(firebaseUser.getEmail());
//            nameText.setText(prefs.getString("id",null));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Toast.makeText(Navigator.this,"Setting",Toast.LENGTH_SHORT).show();

            return true;
        }
        if ((id == R.id.about_us))
        {
            Toast.makeText(Navigator.this,"About Us",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_view, new aboutUS()).addToBackStack(null).commit();

            return true;
        }
        if ((id == R.id.signout))
        {
            if(mAuth.getCurrentUser() != null  )
            {
                String signoutuser= mAuth.getCurrentUser().getEmail();
                mAuth.signOut();
                Toast.makeText(Navigator.this,"User with email "+signoutuser+" \nsign out successfully",Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_view, new StudentClass()).commit();


            }else{
                Toast.makeText(Navigator.this,"Sign in first",Toast.LENGTH_SHORT).show();

            }


         //   Toast.makeText(Navigator.this,"Sign out",Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Admin) {
            // Handle the camera action
            Toast.makeText(Navigator.this,"Admin",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_view,new AdminClass()).addToBackStack(null).commit();
        }  else if (id == R.id.Student) {

            if(mAuth.getCurrentUser() == null) {
                Toast.makeText(Navigator.this, "Student", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_view, new StudentClass()).commit();
                   //   startActivity(new Intent(Navigator.this,SignIN.class));
                // getSupportFragmentManager().beginTransaction().replace(R.id.frame_view,new QR_code_scanner()).commit();
            }

            //    getSupportFragmentManager().beginTransaction().replace(R.id.frame_view, new QR_code_scanner()).commit();
         //   FragmentManager fragmentManager = getSupportFragmentManager();
            //this will clear the back stack and displays no animation on the screen
           // fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        }
//        else if (id == R.id.Attendence) {
//            Toast.makeText(Navigator.this,"Attendence",Toast.LENGTH_SHORT).show();
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame_view,new AttendenceClass()).commit();
//
//        }
        else if (id == R.id.nav_share) {
            Toast.makeText(Navigator.this,"Share",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(Navigator.this,"Send",Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
