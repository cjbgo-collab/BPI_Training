
package M5_Activity3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/training_db";
    private static final String USER = "cjbgo";
    private static final String PASSWORD = "postgres";
    
    public static void main(String[] args) {
    	
    	try	{
    		
    		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected successfully");  
    		
    	} catch (Exception e) {
    		
    		System.out.println("Connection failed");
    		e.printStackTrace(); 
    	}
    	
    }
    
    public static Connection getConnection() throws SQLException {
    	return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
