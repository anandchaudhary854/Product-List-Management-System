package com.example.productList.controller.dto;

public class AdminRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int walletBalance;
    private boolean isAdmin = true;

    public AdminRegistrationDto(String firstName, String lastName, String email, String password, int walletBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.walletBalance = walletBalance;
    }

    public AdminRegistrationDto() {

    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public int getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(int walletBalance) {
        this.walletBalance = walletBalance;
    }
}
