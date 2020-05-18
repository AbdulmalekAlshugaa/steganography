package com.Rakan.steganography.registration;

import android.app.Activity;

public interface SginUpPresenter {
    void CreatNewAccount (Activity activity, String emailAddres, String password);

    void pushUserData (Activity activity, String UserName, String userId, String DeviceToekn, String personalImage, String TimeStampl);

    // Send email after the user has create his own account
 //   void SendEmailVerfication (Activity activity, String emailAddress);

}
