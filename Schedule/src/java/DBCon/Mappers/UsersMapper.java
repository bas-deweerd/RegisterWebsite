/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCon.Mappers;

import DBCon.QueryHelper;
import java.time.LocalDate;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bas
 */
public final class UsersMapper {
    private static final String tableName = "users";
    
    public static boolean userExists(String username){
        return QueryHelper.containsString(username, "username", tableName);
    }
    
    public static String getUsername(int id){
        return QueryHelper.selectStringQuery("username", tableName, "userid", id);
    }
    
    public static String getPassword(int id){
        return QueryHelper.selectStringQuery("password", tableName, "userid", id);
    }
    
    public static String getPassword(String username){
        return QueryHelper.selectStringQuery("password", tableName, "username", username);
    }
    
    public static int getId(String username){
        return QueryHelper.selectIntQuery("userid", tableName, "username", username);
    }
    
    public static boolean credentialsCorrect(String username, String password){
        return userExists(username) && getPassword(username).equals(password);
    }

    public static String getDateCreated(int id) {
        return QueryHelper.selectStringQuery("datecreated", tableName, "userid", id);
    }
    
    public static String getDateCreated(String username){
        return QueryHelper.selectStringQuery("datecreated", tableName, "username", username);
    }

    public static String getDateOfBirth(String username) {
        return QueryHelper.selectStringQuery("dob", tableName, "usernam", username);
    }
    
    public static void setUsername(String newUsername, String oldUsername){
        FacesContext facesContext = FacesContext.getCurrentInstance(); 
        if(userExists(newUsername)){
            facesContext.addMessage("errorMessage", new FacesMessage("New username is already taken."));
        }
        if(!userExists(oldUsername)){
            facesContext.addMessage("errorMessage", new FacesMessage("Old username does not exist."));
        }
        QueryHelper.updateStringQuery(newUsername, "username", tableName, "username", oldUsername);
    }
    
    public static void setPassword(String username, String oldPassword, String password){
        FacesContext facesContext = FacesContext.getCurrentInstance(); 
        // Check if username and password are correct.
        // Check if password is up to standards. 
        // TODO
        if(!credentialsCorrect(username, oldPassword)){
            facesContext.addMessage("errorMessage", new FacesMessage("Password is incorrect."));
        }
        QueryHelper.updateStringQuery(password, "password", tableName, "username", username);
    }
    
    
}
