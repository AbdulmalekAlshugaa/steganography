
package com.Rakan.steganography;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Rakan.steganography.Login.LoginImp;
import com.Rakan.steganography.Login.LoginView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class loginPage extends AppCompatActivity implements LoginView {

    Button btnLogin;
    TextView tvRegister;
    EditText edtEmail, edtPassword;
    private LoginImp loginImp;
    private LoginView loginView;
    ProgressDialog mProgressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        // XML
        // firebase auth

        FirebaseApp.getApps(loginPage.this);
        firebaseAuth = FirebaseAuth.getInstance();
        edtEmail = findViewById(R.id.EmailAddress);
        edtPassword = findViewById(R.id.Passwords);
        btnLogin = findViewById(R.id.LoginBtn);
        loginImp = new LoginImp(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, Logging in..");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLoginDetails();
            }
        });



    }

    private void initLogin(String email, String password, String FullNaame) {
        mProgressDialog.show();

        loginImp.performFirebaseLogin(loginPage.this, email, password,FullNaame);
    }

    private void checkLoginDetails() {
        if(!TextUtils.isEmpty(edtEmail.getText().toString()) && !TextUtils.isEmpty(edtPassword.getText().toString())){

            initLogin(edtEmail.getText().toString(), edtPassword.getText().toString(),"Ahmed Ali");
        }else{
            if(TextUtils.isEmpty(edtEmail.getText().toString())){
                // Intent intent = new Intent(LoginActivity.this,MainActivity.class);

                edtEmail.setError("Please enter a valid email");
            }if(TextUtils.isEmpty(edtPassword.getText().toString())){
                edtPassword.setError("Please enter password");
            }
        }
    }

    @Override
    public void onLoginSuccess(String message) {
        mProgressDialog.dismiss();
        Intent intent = new Intent(loginPage.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Successfully Logged in" , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    public void SignUp(View view) {
        Intent intent = new Intent(loginPage.this, SignUp.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getUid()!= null){
            Intent intent = new Intent(loginPage.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}