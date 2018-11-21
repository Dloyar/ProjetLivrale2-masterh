package com.example.rayold.everydayneeds.activities;

public class User {
    private String email,name,password,role;

    public User(){

    }
    public User(String email,String name,String password,String role){
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

