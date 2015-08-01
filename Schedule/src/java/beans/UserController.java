package beans;

import DBCon.Mappers.UsersMapper;
import DBCon.QueryHelper;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import javafx.util.converter.LocalDateStringConverter;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bas
 */
@ManagedBean
@SessionScoped
public class UserController implements Serializable{
    private String username;
    private String password;
    private String dateOfBirth;
    private String dateCreated;
    private String dateLastLogin;
        
    public UserController() {
    }

    public void setDateCreated(){
        this.dateCreated = UsersMapper.getDateCreated(username);
    }
    
    public void setDateCreatedToNow(){
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();
        this.dateCreated = year + month + day + "";
        
    }
    
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateLastLogin() {
        return dateLastLogin;
    }

    public void setDateLastLogin(String dateLastLogin) {
        this.dateLastLogin = dateLastLogin;
    }
    
    public String getDateCreated() {
        return dateCreated;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String login(){
        FacesContext facesContext = FacesContext.getCurrentInstance();        
        QueryHelper.connect();
        if(UsersMapper.credentialsCorrect(username, password)){
            setDateCreated();
            setDateOfBirth();
            setDateLastLogin();
            return "success.xhtml";
        } else{
            facesContext.addMessage("errorMessage", new FacesMessage("Username or password is incorrect."));
            return null;
        }        
    }
    
    public void connect(){
        QueryHelper.connect();
    }
    
    public void disconnect(){
        QueryHelper.closeConnection();
    }
    
    public String logout(){
        //TODO
        //dateLastLogin = LocalDate.now();
        
        QueryHelper.closeConnection();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public void setDateOfBirth() {
        this.dateOfBirth = UsersMapper.getDateOfBirth(username);
    }

    public void setDateLastLogin() {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void createAccount(String username, String password, String dateOfBirth, String gender){
        //TODO
    }
    
}
