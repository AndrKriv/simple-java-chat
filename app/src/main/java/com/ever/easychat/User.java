package com.ever.easychat;

import java.util.Date;

public class User {
    public String mail;
    public String name;
    public String pass;

    public User(){}

    public User(String name){
        this.name = name;
    }
    public User(String name, String mail){
        this.mail = mail;
        this.name = name;
    }
    public User(String name, String mail, String pass){
        this.mail = mail;
        this.name = name;
        this.pass = pass;
    }

    public String getMail(){
        return mail;
    }
    public void setMail(String mail){
        this.mail = mail;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getPass(){
        return pass;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
}
