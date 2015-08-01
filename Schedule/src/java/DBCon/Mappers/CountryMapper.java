/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCon.Mappers;

import DBCon.QueryHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Bas
 */
@ManagedBean
@SessionScoped
public class CountryMapper implements Serializable{
    private ArrayList<String> countries;
    
    public CountryMapper(){
        fillArray();
    }
    
    public void fillArray(){
        String[] array = QueryHelper.selectAllFrom("name", "country");
        countries = new ArrayList<String>(Arrays.asList(array));
    }
    
    public List<String> getCountries(){
        return countries;
    }
}
