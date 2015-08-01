/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Bas
 */
@ManagedBean
@SessionScoped
public class ValidDates implements Serializable{
    private ArrayList<String> years;
    private ArrayList<String> months;
    private ArrayList<String> days;
    
    public ValidDates(){
        this.years = new ArrayList<String>();
        for (int i = 0; i < 91; i++) {
            years.add(i+1915+"");
        }
        Collections.reverse(years);
        
        this.months = new ArrayList<String>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        
        this.days = new ArrayList<String>();
        for(int i = 1; i < 32; i++){
            days.add(i+"");
        }   
    }
    
    public ArrayList<String> getYears(){
        return years;
    }
    
    public ArrayList<String> getMonths(){
        return months;
    }
    
    public ArrayList<String> getDays(){
        return days;
    }
}
