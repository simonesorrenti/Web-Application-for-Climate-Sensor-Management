package util;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Database {
    
	private static final String URL = "jdbc:mysql://localhost:3306/sensorlogicsystem?characterEncoding=latin1&useConfigs=maxPerformance";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
	
	public static Connection getConnection() {
		
	    Connection conn = null;
	    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if (conn != null) {
                System.out.println("Connected to the database");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Could not find database driver class");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
        
        return conn;
    }
}
 