package restservice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Credential;
import util.Database;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CredentialController {

	@GetMapping("/login")
	public Credential login(@RequestParam String email, @RequestParam String password) {

		Statement stmt = null;
		ResultSet rs = null;
		Credential result = null;
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select email, password, role, user "
			 		+ " FROM credential "
			 		+ " WHERE email = '"+email+"' AND password ='"+password+"'");
			 
			 if(rs != null) {
				 rs.last();
				 if(rs.getRow() > 0) {
					 result = new Credential(rs.getString("email"), rs.getString("password"), 
							 				rs.getString("role"), rs.getInt("user"));
				 }
			 }
		} catch(Exception e) {
			 e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	@GetMapping("/getPassword")
	public String getPassword(@RequestParam String email) {

		Statement stmt = null;
		ResultSet rs = null;
		String result = null;
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select password "
			 		+ " FROM credential "
			 		+ " WHERE email = '"+email+"'");
			 
			 if(rs != null) {
				 rs.last();
				 if(rs.getRow() > 0) {
					 result = rs.getString("password");
				 }
			 }
		} catch(Exception e) {
			 e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
