
import DBCon.ConnectionHelper;
import DBCon.Mappers.CountryMapper;
import beans.UserController;
import DBCon.Mappers.UsersMapper;
import DBCon.QueryHelper;
import beans.RegisterBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bas
 */
public class TestingClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QueryHelper.connect();
        
        QueryHelper.closeConnection();
    }
}
