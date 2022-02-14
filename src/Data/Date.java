/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Date implements Serializable{
    private int day, mont, year;
    LocalDate today = java.time.LocalDate.now();
    
    public Date(int day, int mont, int year) {
        this.day = day;
        this.mont = mont;
        this.year = year;
    }
    
    //chuyển ngày thành int
    public static int countDate(Date date){
    int num = 0;
    boolean leap = false;
    //tính tham số tháng
    if (date.getYear() % 400 == 0) leap = true;
    else if (date.getYear() % 4 == 0 && date.getYear() % 100 != 0)leap = true;   
    int numMont = getParameterMont(date.getMont(), leap);
    //Tính tham số năm
    int yearLeap = 0, numYear = 0;
    yearLeap = date.getYear() / 4;
    numYear = yearLeap*366 + (date.getYear() - yearLeap)*365 - 365;
    
    num = date.getDay()  + numMont + numYear;
    return num;
    }
    
    //Sử dụng đệ quy để trả về tham số tháng trong năm
    private static int getParameterMont(int mont, boolean leapYear){
        if(mont == 1) return 0;
        else if(mont == 2 || mont == 4 || mont == 6 || mont == 8 || mont == 9 || mont == 11){
            return getParameterMont(mont - 1, leapYear) + 31;
        }
        else if(mont == 3){
            if(leapYear) return getParameterMont(mont - 1, leapYear) + 29;
            else return getParameterMont(mont - 1, leapYear) + 28;
        }
        else{
            return getParameterMont(mont - 1, leapYear) + 30;
        }
    }
    
    // check days
    public boolean dayValid(){
        int flag = 0;
        if (this.year % 400 == 0) flag = 1;
        
        else if (this.year % 4 ==0 && this.year % 100 != 0)flag = 1; 
        
        if(this.mont < 1 || this.mont > 12) {System.out.println("The day " + this.toString() + " is not valid!!"); return false;}
        else if (this.mont == 1 || this.mont == 3 || this.mont == 5 || this.mont == 7 || this.mont == 8 || this.mont == 10 || this.mont == 12){
            if (this.day < 1 || this.day > 31) {System.out.println("The day " + this.toString() + " is not valid!!"); return false;}
        }
        else if (this.mont == 2){
            if(flag == 0){
                if(this.day < 1 || this.day > 28){System.out.println("The day " + this.toString() + " is not valid!!"); return false;}
            }
            else if(flag == 1){
                if(this.day < 1 || this.day > 29) {System.out.println("The day " + this.toString() + " is not valid!!"); return false;}
            }
        }
        else if(this.mont == 4 || this.mont == 6 || this.mont == 9 || this.mont == 11){
            if(this.day < 1 || this.day > 31) {System.out.println("The day " + this.toString() + " is not valid!!"); return false;}
        }
        return true;
    }
    //hàm thêm ngày
    public static Date addDate(){
        Scanner sc =new Scanner(System.in);
        int day, mont, year;
        Date date = null;
        boolean matched = false, trueDay = false;  
        String input = null;
        //enter day, mont, year
        while(!trueDay ){
            while(!matched){
                System.out.println("Enter the day format (dd/mm/yyyy):");
                input = sc.nextLine().trim();
                matched = input.matches("\\d{2}/\\d{2}/\\d{4}$");
            }
            date = stringToDate(input);
            trueDay = date.dayValid();
            if(!trueDay) {System.out.println("The day: " + date +" is not exist !"); matched = false;}
        }
        return date;
    }
    //String thành ngày
    private static Date stringToDate(String sDate){
        int day, mont, year;
        //day
        char c1 = sDate.charAt(0);
        int num1 = Character.getNumericValue(c1);
        c1 = sDate.charAt(1);
        day = num1*10 + Character.getNumericValue(c1);
        //month
        c1 = sDate.charAt(3);
        num1 = Character.getNumericValue(c1);
        c1 = sDate.charAt(4);
        mont = num1*10 + Character.getNumericValue(c1);
        //year
        c1 = sDate.charAt(6);
        num1 = Character.getNumericValue(c1);
        c1 = sDate.charAt(7);
        year = num1*10 + Character.getNumericValue(c1);
        c1 = sDate.charAt(8);
        year = year*10 + Character.getNumericValue(c1);
        c1 = sDate.charAt(9);
        year = year*10 + Character.getNumericValue(c1);              
        return (new Date(day, mont, year));
    }
    /*
    //check date so với ngày hôm nay
    public boolean checkDate(Date date){
        Date today = new Date(this.today.getDayOfMonth(), this.today.getMonthValue(), this.today.getYear());
        int toD = Date.countDate(today);
        int checkD = Date.countDate(date);
        if(toD < checkD){
            System.out.println("");}
        return true;
    }*/
    //
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMont() {
        return mont;
    }

    public void setMont(int mont) {
        this.mont = mont;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    

    @Override
    public String toString() {
        return (day + "/" + mont + "/" + year);
    }    
}
