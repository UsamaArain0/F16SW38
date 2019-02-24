package com.example.muhammadusama.studentattendence;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Firebase mRoot;
    EditText mEmailField,mPasswordField,mUserId;
    Button register;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmailField=findViewById(R.id.u_email);
        mPasswordField=findViewById(R.id.u_pass);
        mUserId=findViewById(R.id.u_id);
        progressDialog = new ProgressDialog(this);
        register=findViewById(R.id.register);

          databaseReference= FirebaseDatabase.getInstance().getReference().child("Student");
        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                UserRegistration(mEmailField.getText().toString().trim(),mPasswordField.getText().toString().trim());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void UserRegistration(String email, String password){
        if (!validateForm()) {
            return;
        }
        progressDialog.setMessage("Registring Student...");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Register successfully.",Toast.LENGTH_LONG).show();


                         String id= mAuth.getCurrentUser().getUid();
                         DatabaseReference current_user = databaseReference.child(id);
                         current_user.child("Student_ID").setValue(mUserId.getText().toString().trim());

                            SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("My Preference", MODE_PRIVATE).edit();
                            editor.putString("id", mUserId.getText().toString().trim());
                            editor.apply();


                            mEmailField.setText("");
                            mPasswordField.setText("");
                            mUserId.setText("");
                            progressDialog.dismiss();

                            startActivity(new Intent(MainActivity.this,SignIN.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));


                            // Sign in success, update UI with the signed-in user's information
                          //FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                          Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                          progressDialog.dismiss();
                        }

                        // [START_EXCLUDE]
                        // [END_EXCLUDE]
                    }
                });

        // [END create_user_with_email]
    }




    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }
        String id = mUserId.getText().toString().trim();
        if(TextUtils.isEmpty(id))
        {
            mUserId.setError("Required");
            valid=false;
        } else {
            mUserId.setError(null);
        }

        return valid;
    }

}
