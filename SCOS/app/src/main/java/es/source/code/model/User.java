package es.source.code.model;

import java.io.Serializable;

public class User implements Serializable{
    private  String  userName;
    private  String  password;
    private  boolean oldUser;
    public  String GetUserName(){return userName; };
   public String GetPassword(){return  password;};
  public   boolean GetOldUser(){return  oldUser;};
   public void  SetUserName(String user){userName=user;};
    public void  SetPassword(String pass){password=pass;};
    public  void SetOldUser(boolean isold){oldUser=isold;};
}
