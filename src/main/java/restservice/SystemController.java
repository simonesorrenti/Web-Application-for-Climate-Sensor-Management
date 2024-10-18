package restservice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import model.Detection;
import model.System;
import util.Database;

@RestController
@RequestMapping("/system")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SystemController {

	@GetMapping("/getAllSystemsOfCustomer")
	public List<System> getAllSystemsOfCustomer(@RequestParam Integer user) {

		Statement stmt = null;
		ResultSet rs = null;
		List<System> result = new ArrayList<System>();
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * "
			 		+ " FROM sensorlogicsystem.system "
			 		+ " WHERE owner = "+user);
			 
			 if(rs != null) {
				 while(rs.next()) {
					 result.add(new System(rs.getInt("id"), rs.getInt("owner"), rs.getString("name_system"),
							 rs.getString("city"), rs.getString("address"), rs.getString("house_number"),
							 rs.getString("description"), rs.getInt("cap")));
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
	
	@GetMapping("/getAllSystems")
	public List<System> getAllSystems() {

		Statement stmt = null;
		ResultSet rs = null;
		List<System> result = new ArrayList<System>();
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * "
			 		+ " FROM sensorlogicsystem.system");
			 
			 if(rs != null) {
				 while(rs.next()) {
					 result.add(new System(rs.getInt("id"), rs.getInt("owner"), rs.getString("name_system"),
							 rs.getString("city"), rs.getString("address"), rs.getString("house_number"),
							 rs.getString("description"), rs.getInt("cap")));
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
	
	@DeleteMapping("/deleteSystem")
	public boolean deleteSystem(@RequestParam Integer system) {

		Statement stmt = null;
		boolean result = true;
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 stmt.executeUpdate("DELETE FROM sensorlogicsystem.system "
			 		+ " WHERE id = "+system);
			 
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
	
	@PutMapping("/updateSystem")
	public boolean updateSystem(@RequestBody System system) {

		Statement stmt = null;
		boolean result = true;
		Connection conn = Database.getConnection();
        
		try {			
			 stmt = conn.createStatement();
			 stmt.executeUpdate("update sensorlogicsystem.system "
			 		+ " SET address ='"+system.getAddress()+"', cap="+system.getCap()+", city='"+system.getCity()+"',"
			 		+ " description='"+system.getDescription()+"', house_number='"+system.getHouseNumber()+"', "
			 		+ " name_system='"+system.getNameSystem()+"', owner="+system.getOwner()
			 		+ " WHERE id ="+system.getId());
			 
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
	
	@PostMapping("/insertSystem")
	public boolean insertSystem(@RequestBody System system) {

		Statement stmt = null;
		boolean result = true;
		Connection conn = Database.getConnection();

		try {			
			 stmt = conn.createStatement();
			 stmt.executeUpdate("INSERT INTO sensorlogicsystem.system (address, cap, city, description, house_number, name_system, owner) "
			 		+ " VALUES ('"+system.getAddress()+"', "+system.getCap()+", '"+system.getCity()+"',"
			 		+ " '"+system.getDescription()+"', '"+system.getHouseNumber()+"', "
			 		+ " '"+system.getNameSystem()+"', "+system.getOwner()
			 		+ " )");
			 
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
				result = false;
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	@GetMapping("/getAllIdSystems")
	public List<Integer> getAllIdSystems() {

		Statement stmt = null;
		ResultSet rs = null;
		List<Integer> result = new ArrayList<Integer>();
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * "
			 		+ " FROM sensorlogicsystem.system ");
			 
			 if(rs != null) {
				 while(rs.next()) {
					 result.add(rs.getInt("id"));
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
