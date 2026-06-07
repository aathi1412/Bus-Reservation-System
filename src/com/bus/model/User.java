package com.bus.model;

public class User {
    private int userId;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;

    public User(String name, String email, String phone, String password, String role){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public int getUserId(){
        return this.userId;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getPassword(){
        return this.password;
    }

    public String getRole(){
        return this.role;
    }

}
