package com.bus.model;

public class User {
    private int userId;
    private String name;
    private String email;
    private int phone;
    private String password;
    private String role;

    public User(String name, String email, int phone, String password, String role){
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

    public int getPhone(){
        return this.phone;
    }

    public String getPassword(){
        return this.password;
    }

    public String getRole(){
        return this.role;
    }

}
