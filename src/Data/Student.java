/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Student extends Code {
    String name, phone;

    public static final String tabbleStu = String.format("|STUDENT |%-20s|%-20s|%-20s|", "STUDENT CODE", "STUDENT NAME", "PHONE NUMBER" );

    
    public static String inputPhone(){
        Scanner sc =new Scanner(System.in);
        String phone = null;
        boolean matched = false;
        while(!matched){
            System.out.print("Enter the phone number format (" + "0" + " and 9 digit): ");
            phone = sc.nextLine().toUpperCase().trim();
            matched = phone.matches( "0" + "\\d{9}$");
        }
        return phone;
    }
    /*
    public Student(String code, String name, String phone) {
        this.code = code;
        this.name = name;
        this.phone = phone;
    }*/

    public Student(String code, String name, String phone) {
        super(code);
        this.name = name;
        this.phone = phone;
    }
    
    
    @Override
    public String getCode() {
        return code;
    }
    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("|STUDENT |%-20s|%-20s|%-20s|", code, name, phone );
    }    
}
