package com.Rakan.steganography.registration;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;


import com.Rakan.steganography.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;


public class SginUpImp implements SginUpView, SginUpPresenter {
    private SginUpView sginUpView;
    private static final String TAG = "SginUpImp";

    public SginUpImp(SginUpView sginUpView) {
        this.sginUpView = sginUpView;
    }


    @Override
    public void CreatNewAccount(Activity activity, String emailAddres, String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailAddres, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    sginUpView.onSignUpuccess(task.getResult().toString());
                }else {
                    sginUpView.onSignFailure(task.getException().getMessage().toString());
                }
            }
        });
    }

    @Override
    public void pushUserData(Activity activity, String UserName, String userId, String DeviceToekn, String personalImage, String TimeStampl) {

        Users users = new Users(UserName, userId, DeviceToekn, personalImage, TimeStampl );
        FirebaseFirestore.getInstance().collection("UserData").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .set(users, SetOptions.merge())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
//                            sginUpView.onSignUpuccess(task.getResult().toString());
                            Log.d(TAG, "onComplete: "+"hey ");
                        }else {
                           // sginUpView.onSignFailure(task.getException().getMessage().toString());
                            Log.d(TAG, "onComplete: "+task.getException());

                        }
                    }
                });

    }



//    @Override
//    public void SendEmailVerfication(Activity activity, String emailAddress) {
//        FirebaseAuth.getInstance().verifyPasswordResetCode(emailAddress).addOnCompleteListener(new OnCompleteListener<String>() {
//            @Override
//            public void onComplete(@NonNull Task<String> task) {
//                if (task.isSuccessful()){
//                    Log.d(TAG, "onComplete: success");
//                }else {
//                    Log.d(TAG, "onComplete: Error email verification "+task.getException().getMessage().toString());
//                }
//            }
//        });
//
//    }



    @Override
    public void onSignUpuccess(String message) {
        sginUpView.onSignUpuccess(message);

    }

    @Override
    public void onSignFailure(String message) {
        sginUpView.onSignFailure(message);

    }
}
