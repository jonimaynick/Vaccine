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
public class EachVac implements Serializable{
    String place;
    Date date;
    Vaccine vac;

    public EachVac(String place, Date date, Vaccine vac) {
        this.place = place;
        this.date = date;
        this.vac = vac;
    }    
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Vaccine getVac() {
        return vac;
    }
    public void setVac(Vaccine vac) {
        this.vac = vac;
    }    
}
