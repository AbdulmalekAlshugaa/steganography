package com.Rakan.steganography.Login;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginImp implements LoginView, Presenter {

    private LoginView mView;
    private static final String TAG = "LoginImp";

    public LoginImp(LoginView mView) {
        this.mView = mView;
    }

    @Override
    public void onLoginSuccess(String message) {
        mView.onLoginSuccess(message);

    }

    @Override
    public void onLoginFailure(String message) {
        mView.onLoginFailure(message);

    }


    @Override
    public void performFirebaseLogin(Activity activity, String email, String Password, String UserName) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "onComplete: success");
                            mView.onLoginSuccess(task.getResult().toString());
                        }else {
                            Log.d(TAG, "onComplete: Error"+task.getException().getMessage());
                           // Log.d(TAG, "onComplete: ");
                            mView.onLoginFailure(task.getException().toString());

                        }
                    }
                });

    }
}
