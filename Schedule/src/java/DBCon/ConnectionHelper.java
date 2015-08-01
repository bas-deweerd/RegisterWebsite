package DBCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bas
 */
public final class ConnectionHelper {
    private final static String database = "schedule";
    private final static String username = "schedule";
    private final static String password = "tiestoman2";
    private final static String host = "localhost";
    private final static String port = "5432";
    private final static String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
    private static Connection connection;
    
    public static Connection connect(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected.");
            return connection;
        } catch(Throwable ex){
            System.out.println("Connection failed.");
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void prepareStatement(String sql){
        try {
            connection.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void closeConnection() {
        try{
            if(!connection.isClosed() || connection != null){
                connection.close();
                System.out.println("Disconnected.");
            }
        }catch(Throwable ex){
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
