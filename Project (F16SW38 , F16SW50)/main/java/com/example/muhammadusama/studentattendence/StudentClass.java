package com.example.muhammadusama.studentattendence;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.Context.MODE_PRIVATE;

public class StudentClass extends Fragment {

    Button signIn;
    EditText email1,password1;
    FirebaseAuth mAuth1;
    ProgressDialog progressDialog;
    TextView go_to;
    SharedPreferences sharedPreferences;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.login_layout,container,false);
        Firebase.setAndroidContext(getContext());
        mAuth1= FirebaseAuth.getInstance();
        email1=view.findViewById(R.id.email);
        password1=view.findViewById(R.id.pas);
        signIn=view.findViewById(R.id.sign);
        progressDialog = new ProgressDialog(getContext());
        go_to=view.findViewById(R.id.go_to_register);
        go_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(email1.getText().toString().trim(),password1.getText().toString().trim());

            }
        });
        return view;
    }
    private void signIn(final String email, String password) {
        if (!validateForm()) {
            return;
        }

        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        // [START sign_in_with_email]
        mAuth1.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getContext(), "Sign in successfully.",Toast.LENGTH_SHORT).show();
//                            SharedPreferences.Editor editor = getActivity().getSharedPreferences("My Preference", MODE_PRIVATE).edit();
//                            editor.putString("name", mAuth1.getCurrentUser().getUid());
//                            editor.putString("email",email );
//                            editor.apply();
                            progressDialog.dismiss();
                          //   getFragmentManager().beginTransaction().replace(R.id.frame_view,new QR_code_scanner()).commit();

//                            Navigator.Email_Lable=email;
//                            Navigator.Name_Lable=mAuth1.getCurrentUser().getUid();
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(getContext(), "Authentication failed.",
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

}
