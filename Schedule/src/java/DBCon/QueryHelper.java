/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Bas
 */
public final class QueryHelper {
    
    private static String query;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    private static Connection connection;
    
    public static void connect(){
        if(isConnected() == false){
            connection = ConnectionHelper.connect();
        }
        
    }
    
    public static boolean isConnected(){
        try{
            if(connection == null || connection.isClosed()){
                return false;
            } else {
                return true;
            }
        }catch(Throwable ex){
            System.out.println(ex);
            return false;
        }
    }
    
    public static void closeConnection(){
        ConnectionHelper.closeConnection();
        
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(QueryHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static List selectAllFromUsingList(String tableName){
        List list = new ArrayList();
        try{
            query = "SELECT * FROM " + tableName + ";";
            statement = connection.prepareStatement(query);
            System.out.println("Statement: " + statement);
            resultSet = statement.executeQuery();
            System.out.println("ResultSet: " + resultSet);
            
            if (!resultSet.next()) {
                System.out.println("ResultSet is empty");
            } else {
                do {
                    List<String> element = new ArrayList<>();
                    int nrOfColumns = resultSet.getMetaData().getColumnCount();
                    for(int i = 1; i <= nrOfColumns; i++){
                        String name = resultSet.getMetaData().getColumnLabel(i);
                        String field = resultSet.getString(name);
                        element.add(field);
                    }
                    list.add(element);
                } while (resultSet.next());
            }
            
            //resultSet.beforeFirst();
            while(resultSet.next()){
                
            }
        } catch(Throwable ex){
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static ResultSet selectAllFromUsingResultSet(String tableName){
        try{
            query = "SELECT * FROM " + tableName + ";";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            
            return resultSet;
        } catch(Throwable ex){
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean containsString(String string, String fieldName, String tableName){
        try{
            query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + fieldName + " = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1, string);
            resultSet = statement.executeQuery();
            
            resultSet.next();
            return resultSet.getInt(1) > 0;
            
        } catch(Throwable ex){
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static String selectStringQuery(String selectFieldName, String tableName, String conditionFieldName, int conditionValue){
        try{
            // *** Start execution of query ***
            query = "SELECT " + selectFieldName + " FROM " + tableName + " WHERE " + conditionFieldName + " = ?;";
            statement = connection.prepareStatement(query);
            statement.setInt(1, conditionValue);
            resultSet = statement.executeQuery();

            // *** End execution of query ***
            // *** Start validity checks ***       
            if (!resultSet.next()) {
                System.out.println("Query did not return any results.");
                return null;
            }
            // *** End validity checks ***

            // *** Start process query results ***
            String result = resultSet.getString(selectFieldName);
            return result;
            // *** End process query results ***
        } catch(Throwable ex){
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static String selectStringQuery(String selectFieldName, String tableName, String conditionFieldName, String conditionValue){
        try{
            // *** Start execution of query ***
            query = "SELECT " + selectFieldName + " FROM " + tableName + " WHERE " + conditionFieldName + " = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1, conditionValue);
            resultSet = statement.executeQuery();

            // *** End execution of query ***
            // *** Start validity checks ***       
            if (!resultSet.next()) {
                System.out.println("Query did not return any results.");
                return null;
            }
            // *** End validity checks ***

            // *** Start process query results ***
            String result = resultSet.getString(selectFieldName);
            return result;
            // *** End process query results ***            
        }catch(Throwable ex){
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static int selectIntQuery(String selectFieldName, String tableName, String conditionFieldName, String conditionValue) {
        try{
            // *** Start execution of query ***
            query = "SELECT " + selectFieldName + " FROM " + tableName + " WHERE " + conditionFieldName + " = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1, conditionValue);
            resultSet = statement.executeQuery();

            // *** End execution of query ***
            // *** Start validity checks ***       
            if (!resultSet.next()) {
                System.out.println("Query did not return any results.");
                return -1;
            }
            // *** End validity checks ***

            // *** Start process query results ***
            int result = resultSet.getInt(selectFieldName);
            return result;
            // *** End process query results ***            
        }catch(Throwable ex){
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public static void updateStringQuery(String insertValue, String insertFieldName, String tableName, String conditionFieldName, String conditionValue){
        try{
            query = "UPDATE " + tableName + " SET " + insertFieldName + " = ? WHERE " + conditionFieldName + " = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1, insertValue);
            statement.setString(2, conditionValue);
            statement.executeUpdate();
            
        }catch(Throwable ex){
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateStringQuery(String insertValue, String insertField, String tableName, String conditionFieldName, int conditionValue){
        
    }
    
    public static void updateIntQuery(int insertValue, String insertField, String tableName, String conditionFieldName, String conditionValue){
        
    }
    
    public static void updateIntQuery(int insertValue, String insertField, String tableName, String conditionFieldName, int conditionValue){
        
    }
    
    public static int selectCountFrom(String tableName){
        try{
            query = "SELECT COUNT(*) FROM " + tableName;
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            
            if (!resultSet.next()) {
                System.out.println("Query did not return any results.");
                return -1;
            }
            
            return resultSet.getInt(1);
        } catch (Throwable ex){
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public static String[] selectAllFrom(String selectFieldName, String tableName){
        try{
            int count = selectCountFrom(tableName);
            String[] result = new String[count];
            
            query = "SELECT " + selectFieldName + " FROM " + tableName + ";";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            
            int i = 0;
            if(resultSet.next()){
                do{
                    result[i] = resultSet.getString(1);
                    i++;
                    //System.out.println(resultSet.getString(1));
                } while(resultSet.next());
            } else {
                System.out.println("Query did not return any results.");
            }
            return result;
        }catch(Throwable ex){
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
}
