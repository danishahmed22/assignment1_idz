package com.example.login_page;

public class storingdata {

    String name, dob, email, username, password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public storingdata() {
    }

    public storingdata(String name, String dob, String email, String username, String password) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
