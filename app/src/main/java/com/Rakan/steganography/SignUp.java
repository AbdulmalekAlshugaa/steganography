package com.Rakan.steganography;

import androidx.annotation.NonNull;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Rakan.steganography.EmailVerfication.EmailVerifactionIMP;
import com.Rakan.steganography.EmailVerfication.OnEmailVerficationView;
import com.Rakan.steganography.registration.SginUpImp;
import com.Rakan.steganography.registration.SginUpView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;

public class SignUp extends AppCompatActivity implements SginUpView, OnEmailVerficationView {
    private EditText UserName, emailAddrees, Password;
    private Button SginUp;
    private SginUpImp sginUpImp;
    private EmailVerifactionIMP emailVerifactionIMP;
    ProgressDialog mProgressDialog;
    private static final String TAG = "SignUp";
    private FirebaseFirestore firestore ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        UserName = findViewById(R.id.UserName);
        emailAddrees = findViewById(R.id.EmailAddressSigup);
        Password = findViewById(R.id.PasswordCreate);
        SginUp = findViewById(R.id.SignUp);
        emailVerifactionIMP = new EmailVerifactionIMP(this);
        sginUpImp = new SginUpImp(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, creating an account ..");
        firestore = FirebaseFirestore.getInstance();
        SginUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSignUpDetails();
            }
        });
    }


    private void initSignUp( String email, String password) {
        mProgressDialog.show();
        sginUpImp.CreatNewAccount(SignUp.this,  email,password);
    }


    private void checkSignUpDetails() {
        if(!TextUtils.isEmpty(emailAddrees.getText().toString()) && !TextUtils.isEmpty(Password.getText().toString())){
            initSignUp(emailAddrees.getText().toString(),Password.getText().toString());
        }else{
            if(TextUtils.isEmpty(UserName.getText().toString())){
                // Intent intent = new Intent(LoginActivity.this,MainActivity.class);

                UserName.setError("User name is required");
                return;
            }if(TextUtils.isEmpty(emailAddrees.getText().toString())){
                emailAddrees.setError("Please enter a valid email");
                return;
            }if (TextUtils.isEmpty(Password.getText().toString())){
                Password.setError("Please enter password");

            }
        }
    }

//    private void SendEmailVerfication (String email){
//        sginUpImp.SendEmailVerfication(SignUp.this, email);
//
//    }

    public void login(View view) {
        Intent intent = new Intent(SignUp.this, loginPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    public void SendEmailVerfication (String Email ){
        emailVerifactionIMP.OnSucessEmailVerfication(SignUp.this, Email);
    }




    @Override
    public void onSignUpuccess(String message) {
        mProgressDialog.dismiss();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // send the request here , it will work much betther
//        SendEmailVerfication(emailAddrees.getText().toString());
        sginUpImp.pushUserData(SignUp.this, UserName.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getUid(), "Token","URL",timestamp.toString());
        FirebaseFirestore.getInstance().collection("UserData")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String EmailAddress = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                        Log.d(TAG, "onComplete: "+EmailAddress);
                        Intent intent = new Intent(SignUp.this, loginPage.class);
                        startActivity(intent);
                        finish();
                        SendEmailVerfication(EmailAddress);
                    }
                });



    }

    @Override
    public void onSignFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(SignUp.this, "Error", Toast.LENGTH_LONG).show();

    }

    public void PushDatatest(View view) {
        Log.d(TAG, "PushDatatest: ");


    }

    // Email verfication
    @Override
    public void OnSucess(String SuccessMesagee) {
        mProgressDialog.dismiss();
        Intent intent = new Intent(SignUp.this, loginPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    @Override
    public void OnError(String SuccessMesagee) {
        Log.d(TAG, "OnError: "+SuccessMesagee);

    }
}