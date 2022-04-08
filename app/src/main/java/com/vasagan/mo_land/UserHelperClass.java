package com.vasagan.mo_land;

public class UserHelperClass {
    String username, email, password, contactNumber, address_snup, postal_code;

    public UserHelperClass() {
    }

    public UserHelperClass(String uname, String mailid, String pass, String contactnum, String address, String postal) {
        this.username = uname;
        this.email = mailid;
        this.password = pass;
        this.contactNumber = contactnum;
        this.address_snup = address;
        this.postal_code = postal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress_snup() {
        return address_snup;
    }

    public void setAddress_snup(String address_snup) {
        this.address_snup = address_snup;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
}
