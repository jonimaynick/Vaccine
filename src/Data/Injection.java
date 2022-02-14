/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author PC
 */
public class Injection extends Code {
    //String injecID; 
    EachVac vac[] ;
    Student student;
    

    public static final String tabbleInjec = String.format("|INJECTTION |%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|", "INJECTION ID","STUDENT ID", "STUDENT NAME", "VACCINE", "DATE 1", "PLACE 1", "DATE 2", "PLACE 2" );
    
    /*
    public Injection(String injecID, Student student, EachVac[] vac) {
        this.injecID = injecID;
        this.student = student;
        this.vac = vac;
    }

    public String getInjecID() {
        return injecID;
    }

    public void setInjecID(String injecID) {
        this.injecID = injecID;
    }
    */

    public Injection(String code, EachVac[] vac, Student student) {
        super(code);
        if(vac[1] == null){
            vac[1] = new EachVac(null, null, null);
        }
        
        this.vac = vac;
        this.student = student;

    }
    @Override
    public String getCode() {
        return code;
    }
    @Override
    public void setCode(String code) {
        this.code = code;
    }
    
    
    
    public EachVac getVac(int i) {
        return vac[i];
    }

    public void setVac(EachVac vac, int i) {
        this.vac[i] = vac;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    /*
    @Override
    public String toString() {
        if(vac[1] != null){
            return String.format("|INJECTTION |%-20s|%-20s|%-20s|%-20S|%-20s|%-20s|%-20s|%-20s|", code ,student.getCode() , student.getName(), vac[0].getVac().getName(), vac[0].getDate(), vac[0].getPlace(), vac[1].getDate(), vac[1].getPlace() );
        }
            return String.format("|INJECTTION |%-20s|%-20s|%-20s|%-20S|%-20s|%-20s|%-20s|%-20s|", code ,student.getCode() , student.getName(), vac[0].getVac().getName(), vac[0].getDate(), vac[0].getPlace(), "null", "null" ); 
    }*/
    @Override
    public String toString() {

            return String.format("|INJECTTION |%-20s|%-20s|%-20s|%-20S|%-20s|%-20s|%-20s|%-20s|", code ,student.getCode() , student.getName(), vac[0].getVac().getName(), vac[0].getDate(), vac[0].getPlace(), vac[1].getDate(), vac[1].getPlace() );
        
    }    
}
