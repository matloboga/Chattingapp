package com.mycompany.chattingapp;
import java.util.Scanner;
public class Chattingapp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first name: ");
        String f = sc.nextLine();
        System.out.print("Enter last name: ");
        String l = sc.nextLine();
        System.out.print("Enter username (must have _ and <=5 chars): ");
        String u = sc.nextLine();
        System.out.print("Enter password (8 chars, capital, number, special): ");
        String p = sc.nextLine();
        System.out.print("Enter cell (+27... 12 chars): ");
        String c = sc.nextLine();
        
        Login login = new Login(u,p,c,f,l);
        System.out.println(login.registerUser());
        
        if(login.checkUserName() && login.checkPasswordComplexity() && login.checkCellPhoneNumber()){
            System.out.println("\n--- LOGIN ---");
            System.out.print("Enter username: ");
            String lu = sc.nextLine();
            System.out.print("Enter password: ");
            String lp = sc.nextLine();
            System.out.println(login.returnLoginStatus(lu, lp));
        }
    }
}