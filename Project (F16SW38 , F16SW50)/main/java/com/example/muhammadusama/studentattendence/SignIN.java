package com.example.muhammadusama.studentattendence;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIN extends AppCompatActivity {

    Button signIn;
    EditText email1,password1;
    FirebaseAuth mAuth1;
    ProgressDialog progressDialog;
    DatabaseReference databaseReferenceForID1;
    TextView go_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
         Firebase.setAndroidContext(this);
        mAuth1= FirebaseAuth.getInstance();
        email1=findViewById(R.id.email);
        password1=findViewById(R.id.pas);
        signIn=findViewById(R.id.sign);
        progressDialog = new ProgressDialog(this);
        go_to=findViewById(R.id.go_to_register);

       if( mAuth1.getCurrentUser() !=null ){

           databaseReferenceForID1 = FirebaseDatabase.getInstance().getReference("Student").child(mAuth1.getUid()).child("Student_ID");
           databaseReferenceForID1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
               @Override
               public void onDataChange( com.google.firebase.database.DataSnapshot dataSnapshot) {
//                   sendItToMe = dataSnapshot.getValue().toString();
//                   Toast.makeText(Navigator.this, "sendItToMe "+sendItToMe, Toast.LENGTH_SHORT).show();
                   SharedPreferences.Editor editor = SignIN.this.getSharedPreferences("My Preference", MODE_PRIVATE).edit();
                   editor.putString("name", dataSnapshot.getValue().toString().trim());
                   editor.apply();

                   // Toast.makeText(Navigator.this, sendItToMe, Toast.LENGTH_SHORT).show();
               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           });

           Toast.makeText(this,"Already Signin with "+mAuth1.getCurrentUser().getEmail(),Toast.LENGTH_SHORT).show();
           Intent startQR= new Intent(SignIN.this,QR_code_scanner.class);
           startActivity(startQR);
           finish();
       }


        go_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIN.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            signIn(email1.getText().toString().trim(),password1.getText().toString().trim());

            }
        });
    }
    private void signIn(final String email, String password) {
        if (!validateForm()) {
            return;
        }

        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        // [START sign_in_with_email]
        mAuth1.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignIN.this, "Sign in successfully.",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Intent startQR= new Intent(SignIN.this,QR_code_scanner.class);
                            startActivity(startQR);
                            finish();
                            //getSupportFragmentManager().beginTransaction().replace(R.id.frame_view, new QR_code_scanner()).commit();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SignIN.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        // [START_EXCLUDE]

                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    public boolean validateForm() {
        boolean valid = true;

        String e = email1.getText().toString().trim();
        if (TextUtils.isEmpty(e)) {
            email1.setError("Required.");
            valid = false;
        } else {
            email1.setError(null);
        }

        String p = password1.getText().toString().trim();
        if (TextUtils.isEmpty(p)) {
            password1.setError("Required.");
            valid = false;
        } else {
            password1.setError(null);
        }


        return valid;
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            System.exit(0);
//            finish();
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
}
