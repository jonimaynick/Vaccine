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
public class getInt {
    public static int get(int min,String s){
        Scanner inp=new Scanner(System.in);
        int ans=min-1;
        while(ans<min){
            try{
                System.out.print(s);
                ans=inp.nextInt();
            }
            catch(Exception e){
                System.out.println("Please enter valid integer!!!!");
                inp=new Scanner(System.in);
            }
        }
        return ans;
    }
    
    public static int get(int min,int max, String s){
    Scanner inp=new Scanner(System.in);
    int ans=min-1;
    int anss = max + 1;
    while(ans < min || anss > max){
        try{
            
            System.out.print(s);
            anss = ans = inp.nextInt();
        }
        catch(Exception e){
            System.out.println("Please enter valid integer!!!!");
            inp=new Scanner(System.in);
        }
    }
        return ans;
}
    
    public static int get(String s){
        Scanner inp = new Scanner(System.in);
        int ans = 0;
            try{
                System.out.print(s);
                ans = inp.nextInt();
            }
            catch(Exception e){
                System.out.println("Please enter valid integer!!!!");
                inp = new Scanner(System.in);
            }
        return ans;
    }
    
    public static double getD(double min, double max, String s){
        Scanner inp=new Scanner(System.in);
        double ans=min-1;
        double anss = max + 1;
        while(ans < min || anss > max){
            try{
            
                System.out.print(s);
                anss = ans = inp.nextDouble();
            }
            catch(Exception e){
                System.out.println("Please enter valid integer!!!!");
                inp=new Scanner(System.in);
            }
        }
        return ans;
    }         
}
