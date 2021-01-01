package com.example.shiftapp;

public class User {

    private String fullName;
    private String phone;
    private Boolean Manger;

    public User(){

    }

    public User(String fullName, String phone,Boolean Manger) {
        this.fullName = fullName;
        this.phone = phone;
        this.Manger = Manger;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getManger() {
        return Manger;
    }

    public void setManger(Boolean manger) {
        Manger = manger;
    }

}
