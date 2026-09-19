package com.mycompany.chattingapp;

public class Login {
    private String username, password, cellNumber, firstName, lastName;

    public Login(String u, String p, String c, String f, String l){
        username=u; password=p; cellNumber=c; firstName=f; lastName=l;
    }
    public boolean checkUserName(){
        return username.contains("_") && username.length()<=5;
    }
    public boolean checkPasswordComplexity(){
        return password.length()>=8 && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.matches(".*[^a-zA-Z0-9].*");
    }
    public boolean checkCellPhoneNumber(){
        return cellNumber.matches("^\\+27[0-9]{9}$");
    }
    public String registerUser(){
        if(!checkUserName()) return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        if(!checkPasswordComplexity()) return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        if(!checkCellPhoneNumber()) return "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        return "Welcome " + firstName + " " + lastName + " it is great to see you.";
    }
    public boolean loginUser(String u, String p){
        return username.equals(u) && password.equals(p);
    }
    public String returnLoginStatus(String u, String p){
        if(loginUser(u,p)) return "Welcome " + firstName + " " + lastName + " it is great to see you again.";
        else return "Username or password incorrect, please try again.";
    }
}