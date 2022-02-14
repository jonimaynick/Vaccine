/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Data.*;
import tools.*;
/**
 *
 * @author PC
 */
public class Main {

    public static void main(String[] args){
    Doctor dt = new Doctor();
    dt.loadVacAndStu();
    Menu mn = new Menu();
    int option =1;
    mn.add("Show all injection");
    mn.add("Add injection");
    mn.add("Update injection");
    mn.add("Search by vaccine code");
    mn.add("Remove injection");
    mn.add("Remove by vaccine code");
    mn.add("Quit");
    mn.setMax(7);
        while (option>0 && option<7){
            System.out.println("===================INJECTION MANAGER=====================");
            dt.loadAllFile();
            option = mn.getUserChoice();
            switch(option){
                case 1: dt.showAllInjection();break;
                case 2: dt.addInjectionInfor();break;
                case 3: dt.updateInjection();break;
                case 4: dt.searchByStudentCode();break;
                case 5: dt.deleteInjection();break;
                case 6: dt.deleteInjectionByVacID();break;
            }
        }
    }
}
