package com.aiyalucky.shortplay.pojo;

import android.content.SharedPreferences;

/**
 * @Author: xu xiao wei
 * @Date: 2023/2/19 15:32
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class User {
    private String uid;
    private String username;
    private String account;
    private String password;
    private String email;
    private String birthday;
    private String address;
    private String phone;

    public User(String uid, String username, String account, String password, String email, String birthday, String address, String phone) {
        this.uid = uid;
        this.username = username;
        this.account = account;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
    }

    public User() {
    }

    public void userSaveSp(SharedPreferences.Editor userSP){
        userSP.putString("uid",this.uid);
        userSP.putString("username",this.username);
        userSP.putString("account",this.account);
        userSP.putString("password",this.password);
        userSP.putString("email",this.email);
        userSP.putString("birthday",this.birthday);
        userSP.putString("address",this.address);
        userSP.putString("phone",this.phone);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
