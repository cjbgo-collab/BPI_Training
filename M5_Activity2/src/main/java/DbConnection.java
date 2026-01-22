package M5_Activity2;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class DbConnection {
	
    private static final String URL = "jdbc:postgresql://localhost:5432/training_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
	
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
			System.out.println("Connected successfully");
		}
				
		
	//	Statement stmt = conn.createStatement();
	//	ResultSet rs = stmt.executeQuery("SELECT * FROM students");



	}

}
