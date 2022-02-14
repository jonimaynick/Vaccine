/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public abstract class Code implements Serializable{
    String code;

    public Code(String code) {
        this.code = code;
    }

    public abstract String getCode();

    public abstract void setCode(String code);
    
    
}
