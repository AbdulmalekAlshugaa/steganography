package com.Rakan.steganography.model;

public class Users {
    private String userName ,userId, DeviceToken, TimeStamp, personalImage;


    public Users(String userName,  String userId, String deviceToken, String personalImage , String timeStamp) {
        this.userName = userName;
        this.userId = userId;
        DeviceToken = deviceToken;
        TimeStamp = timeStamp;
        this.personalImage = personalImage;
    }

    public String getPersonalImage() {
        return personalImage;
    }

    public void setPersonalImage(String personalImage) {
        this.personalImage = personalImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceToken() {
        return DeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        DeviceToken = deviceToken;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public Users() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
