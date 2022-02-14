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
public class Vaccine extends Code {
    String name;

    public static final String tabbleVac = String.format("|VACCINE |%-20s|%-20s|", "VACCINE NAME", "VACCINE CODE" );
    
    /*
    public Vaccine(String name, String code) {
        this.name = name;
        this.code = code;
    }*/

    public Vaccine(String code, String name) {
        super(code);
        this.name = name;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getCode() {
        return code;
    }
    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return String.format("|VACCINE |%-20s|%-20s|",name, code );
    }    
}
