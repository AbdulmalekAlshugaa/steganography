package com.Rakan.steganography.Login;

import android.app.Activity;

public interface Presenter {

    void performFirebaseLogin(Activity activity, String UserName, String email, String password);

}
