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
public class Menu {
    private String[] opt;
    private int numOpt=0;
    int max = 0;

    public void setMax(int max) {this.max = max;}
    
    
    public Menu(){
        opt=new String[30];
    }
    public void add(String op){
        opt[numOpt]=op;
        numOpt++;
    }
    public int getUserChoice(){
        for (int i=0;i<numOpt;i++){
            System.out.printf("%d. %s.\n",i+1,opt[i]);
        }
        Scanner inp=new Scanner(System.in);
        int choice= getInt.get(0,max,"Enter your choice: ");
        System.out.print("------\n");
        return choice;
    }    
}
