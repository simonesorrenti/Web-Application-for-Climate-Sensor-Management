package restservice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import model.Credential;
import model.User;
import model.UserCredential;
import util.Database;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@GetMapping("/getUser")
	public User getUser(@RequestParam Integer id) {

		Statement stmt = null;
		ResultSet rs = null;
		User result = null;
		Connection conn = Database.getConnection();

		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * "
			 		+ " FROM user "
			 		+ " WHERE id ="+id);
			 
			 if(rs != null) {
				 if(rs.next()) {
				 result = new User(id, rs.getString("fiscal_code"), rs.getString("name"), rs.getString("surname"), rs.getString("sex"),
						 	rs.getString("phone"), rs.getDate("date_birth"), rs.getString("city_birth"));
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
	
	@GetMapping("/getAllIdCustomers")
	public List<Integer> getAllIdCustomers() {

		Statement stmt = null;
		ResultSet rs = null;
		List<Integer> result = new ArrayList<Integer>();
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * "
			 		+ " FROM user inner join credential on user.id=credential.user "
			 		+ " WHERE credential.role='C'");
			 
			 if(rs != null) {
				 while(rs.next()) {
					 result.add(rs.getInt("user.id"));
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
	
	
	@GetMapping("/getAllUsersOfRole")
	public List<UserCredential> getAllUsersOfRole(@RequestParam String role) {

		Statement stmt = null;
		ResultSet rs = null;
		List<UserCredential> result = new ArrayList<UserCredential>();
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * "
			 		+ " FROM credential inner join user on user.id=credential.user "
			 		+ " WHERE role ='"+role+"'");
			 
			 if(rs != null) {
				 while(rs.next()) {
					 result.add(new UserCredential(new User(rs.getInt("user.id"), rs.getString("fiscal_code"), rs.getString("name"),
							 rs.getString("surname"), rs.getString("sex"), rs.getString("phone"),
							 rs.getDate("date_birth"), rs.getString("city_birth")), 
							 new Credential(rs.getString("email"), rs.getString("password"), 
									 rs.getString("role"), rs.getInt("user"))));
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
	
	@DeleteMapping("/deleteUser")
	public boolean deleteUser(@RequestParam Integer user) {

		Statement stmt = null;
		boolean result = true;
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 stmt.executeUpdate("DELETE FROM user "
			 		+ " WHERE id = "+user);
			 
		} catch(Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			try {
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
	
	@PutMapping("/updateUser")
	public boolean updateUser(@RequestBody UserCredential userCredential) {
		System.out.println(userCredential);
		Statement stmt = null;
		boolean result = true;
		Connection conn = Database.getConnection();
        
		try {		
			 conn.setAutoCommit(false);
				
			 stmt = conn.createStatement();
			 stmt.executeUpdate("update user "
			 		+ " SET phone ='"+userCredential.getUser().getPhone()+"', fiscal_code='"+userCredential.getUser().getFiscalCode()+"', "
			 				+ " name='"+userCredential.getUser().getName()+"', surname='"+userCredential.getUser().getSurname()+"', "
			 				+ " sex='"+userCredential.getUser().getSex()+"', date_birth='"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(userCredential.getUser().getDateBirth()).toString()+"', "
			 				+ " city_birth='"+userCredential.getUser().getCityBirth()+"' "
			 		+ " WHERE id ="+userCredential.getUser().getId());
			 
			 System.out.println("update credential "
				 		+ " SET email ='"+userCredential.getCredential().getEmail()+"', password='"+userCredential.getCredential().getPassword()+"' "
				 		+ " WHERE user ="+userCredential.getUser().getId());
			 stmt.executeUpdate("update credential "
				 		+ " SET email ='"+userCredential.getCredential().getEmail()+"', password='"+userCredential.getCredential().getPassword()+"' "
				 		+ " WHERE user ="+userCredential.getUser().getId());
			 
			 conn.commit();
			 
		} catch(Exception e) {
			result = false;
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				result = false;
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	@PostMapping("/insertUser")
	public boolean insertUser(@RequestBody UserCredential userCredential) {
		System.out.println(userCredential);
		Statement stmt = null;
		boolean result = true;
		Connection conn = Database.getConnection();

		try {			
			
			 conn.setAutoCommit(false);
			 
			 stmt = conn.createStatement();
			 stmt.executeUpdate("INSERT INTO user (phone, fiscal_code, name, surname, sex, date_birth, city_birth) "
			 		+ " VALUES ('"+userCredential.getUser().getPhone()+"', '"+userCredential.getUser().getFiscalCode()+"', "
			 		+ "'"+userCredential.getUser().getName()+"', '"+userCredential.getUser().getSurname()+"', '"+userCredential.getUser().getSex()+"', "
			 		+ " '"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(userCredential.getUser().getDateBirth()).toString()+"', '"+userCredential.getUser().getCityBirth()+"' )");
		 		
			 conn.commit();
			 
			 ResultSet rs = stmt.executeQuery("select id from user where fiscal_code='"+userCredential.getUser().getFiscalCode()+"'");
			 if(rs != null) {
				 while(rs.next()) {
					 userCredential.getCredential().setUser(rs.getInt("user.id"));
				 	}
			 }
			 
			 stmt.executeUpdate("INSERT INTO credential (email, password, role, user) "
				 		+ " VALUES ('"+userCredential.getCredential().getEmail()+"', '"+userCredential.getCredential().getPassword()+"', "
				 		+ " '"+userCredential.getCredential().getRole()+"', "+userCredential.getCredential().getUser()+" )");
				 
			conn.commit();
			 
		} catch(Exception e) {
			result = false;
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				result = false;
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
