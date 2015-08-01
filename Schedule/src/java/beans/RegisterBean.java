package beans;

import DBCon.Mappers.UsersMapper;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Bas
 */
@ManagedBean
@SessionScoped
public class RegisterBean implements Serializable{
    private String username = "";
    private String password = "";
    private String gender;
    private String day;
    private String month;
    private String year;
    private String country;
    private String agreeRules;
    
    public RegisterBean(){
    
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public boolean usernameIsValid(){
        if(UsersMapper.userExists(username)){
            return false;
        }
        if(username.length() < 1 || username.length() > 35){
            return false;
        }
        
        return true;
    }
    
    public int passwordPoints(){
        int points = 0; // total points = 9
        if(password.length() > 5){
            points++;
            points++;
            points++;
        }
        if(password.length() > 7){
            points++;
        }
        if(password.length() > 9){
            points++;
        }
        if(password.length() > 11){
            points++;
        }
        for(char c : password.toCharArray()){
            if(Character.isDigit(c)){
                points++;
                break;
            }
            if(Character.isUpperCase(c)){
                points++;
                break;
            }
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(password);
        if (m.find()){
            points++;
        }
        return points;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAgreeRules() {
        return agreeRules;
    }

    public void setAgreeRules(String agreeRules) {
        this.agreeRules = agreeRules;
    }
}
