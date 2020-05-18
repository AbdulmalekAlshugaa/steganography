package com.Rakan.steganography.EmailVerfication;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class EmailVerifactionIMP implements OnEmailVerficationView, Verfication {
    private OnEmailVerficationView onEmailVerficationView;

    public EmailVerifactionIMP(OnEmailVerficationView onEmailVerficationView) {
        this.onEmailVerficationView = onEmailVerficationView;
    }

    @Override
    public void OnSucess(String SuccessMesagee) {
        onEmailVerficationView.OnSucess(SuccessMesagee);

    }

    @Override
    public void OnError(String SuccessMesagee) {
        onEmailVerficationView.OnError(SuccessMesagee);

    }

    @Override
    public void OnSucessEmailVerfication(Activity activity, String Email) {
        FirebaseAuth.getInstance().verifyPasswordResetCode(Email).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                onEmailVerficationView.OnSucess(s);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onEmailVerficationView.OnError(e.getMessage());
            }
        });

    }
}
