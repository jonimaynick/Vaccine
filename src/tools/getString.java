/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Scanner;

/**
 *
 * @author PC
 */
public class getString {
    private static Scanner sc =new Scanner(System.in);

    public static String getPlace(String note){
        /*
        String[] regions  = {"Bắc bộ", "Trung bộ", "Nam Bộ"}, bacBo = {"Tây Bắc Bộ", "Đông Bắc Bộ", "Đồng Bằng sông Hồng"}, trungBo = {"Bắc Trung Bộ", "Nam Trung Bộ", "Tây Nguyên"};
        String[] namBo = {"Đông Nam Bộ", "Đồng bằng sông Cửu Long"}, 
        */
        String inp = null;
        boolean match = false;
        while(!match){
            String[] province = {"AN GIANG","BA RIA", "VUNG TAU", "BAC GIANG", "BAC KAN", "BAC LIEU","BAC NINH", "BEN TRE", "BINH DINH","BINH DUONG", "BINH PHUOC", "BINH THUAN", "CA MAU", "CAN THO", "CAO BANG", "DA NANG", "DAK LAK", "DAK NONG", "DIEN BIEN", "DONG NAI", "DONG THAP", "GIA LAI", "HA GIANG", "HA NAM", "HA NOI", "HA TINH", "HAI DUONG", "HAI PHONG", "HAU GIANG", "HOA BINH", "HUNG YEN", "KHANH HOA", "KIEN GIANG", "KON TUM","LAI CHAU", "LAM DONG", "LANG SON", "LAO CAI", "LONG AN", "NAM DINH", "NGHE AN", "NINH BINH", "NINH THUAN","PHU THO", "PHU YEN", "QUANG BINH","QUANG NAM","QUANG NGAI", "QUANG NINH","QUANG TRI","SOC TRANG","SON LA","TAY NINH","THAI BINH", "THAI NGUYEN", "THANH HOA", "THUA THIEN HUE", "TIEN GIANG", "HO CHI MINH", "TRA VINH", "TUYEN QUANG","VINH LONG","VINH PHUC", "YEN BAI"};
            System.out.println(note);
            inp = sc.nextLine().toUpperCase().trim();
        
            for(int i = 0; i < province.length; i++){
                if(inp.equals(province[i])){
                    match = true;
                    break;
                }
            }
            if(!match) System.out.println("The province " + inp + " is not illegal, please enter again.");
        }
        
        return inp;
    }
    
    public static String inputStudentCode(){
        boolean match = false;
        String[] prefix = {"SB", "HA", "SE", "SA", "SS"};
        String code = null;
        while(!match){
            for(int i = 0; i < prefix.length; i++){
                System.out.println((i + 1) + ". " + prefix[i]);
            }
            
            boolean notNull = false;
            while(!notNull){
                System.out.print("Please enter the student code ((2 prefix) and number): ");
                code = sc.nextLine().toUpperCase().trim();
                if(code.isEmpty()){
                    System.out.println("Can not input blank!!");
                }
                //else if(code.){}
                else notNull = true;
            }
            match = prefix(code);
            if(!match) System.out.println("Your prefix code is wrong, please enter again.");
        }
        return code;
    }
    //Hàm để lấy Inject code từ người dùng
    public static String getInjecCode(){
        boolean matched = false;
        String injectCode = null;
        while(!matched){
            System.out.print("Enter the code format (IJ + 3 digit): ");
            injectCode = sc.nextLine().toUpperCase().trim();
            matched = injectCode.matches("IJ\\d{3}$");
            if(!matched) System.out.println("The code " + injectCode + " is not valid, please enter again!!");
        }
        return injectCode;
    }    
    
    public static boolean prefix(String code){
    try{
        String[] prefix = {"SB", "HA", "SE", "SA", "SS"};
        String pre = code.substring(0, 2);
        for(int i = 0; i < prefix.length; i++){
            if(pre.equals(prefix[i])) return true;
        }
        
    }
    catch(Exception e){
        System.out.println("Please enter valid code!!!!");
        return false;
    }
    return false;
    }
    

    

    
}
