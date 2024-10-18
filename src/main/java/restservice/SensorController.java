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
import model.Sensor;
import util.Database;

@RestController
@RequestMapping("/sensor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SensorController {

	@GetMapping("/countSensorsOfSystem")
	public Integer countSensorsOfSystem(@RequestParam Integer system) {

		Statement stmt = null;
		ResultSet rs = null;
		Integer result = 0;
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * "
			 		+ " FROM sensor inner join position on sensor.position=position.id "
			 		+ " WHERE position.system = "+system);
			 
			 if(rs != null) {
				 rs.last();
				 result = rs.getRow();
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
	
	@GetMapping("/countSensorsOfPositions")
	public List<Integer> countSensorsOfPositions(@RequestParam String[] positions) {

		Statement stmt = null;
		ResultSet rs = null;
		List<Integer> result = new ArrayList<Integer>();
		Connection conn = Database.getConnection();

		try {
			if(positions != null) {
				for(String position: positions) {

					stmt = conn.createStatement();
					rs = stmt.executeQuery("select * "
					 		+ " FROM sensor inner join position on sensor.position=position.id "
					 		+ " WHERE name_position = '"+position+"'");
					 
					if(rs != null) {
						rs.last();
						result.add(rs.getRow());
					}
					 
					if(rs != null)
						rs.close();
					if(stmt != null)
						stmt.close();
				}
			}
		} catch(Exception e) {
			 e.printStackTrace();
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	@GetMapping("/getAllSensors")
	public List<Sensor> getAllSensors() {

		Statement stmt = null;
		ResultSet rs = null;
		List<Sensor> result = new ArrayList<Sensor>();
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * "
			 		+ " FROM sensor");
			 
			 if(rs != null) {
				 while(rs.next()) {
					 result.add(new Sensor(rs.getInt("id"), rs.getInt("position"), rs.getString("type"), rs.getString("brand")));
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
	
	@DeleteMapping("/deleteSensor")
	public boolean deleteSensor(@RequestParam Integer sensor) {

		Statement stmt = null;
		boolean result = true;
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 stmt.executeUpdate("DELETE FROM sensor "
			 		+ " WHERE id = "+sensor);
			 
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
	
	@PutMapping("/updateSensor")
	public boolean updateSensor(@RequestBody Sensor sensor) {

		Statement stmt = null;
		boolean result = true;
		Connection conn = Database.getConnection();
        
		try {			
			 stmt = conn.createStatement();
			 stmt.executeUpdate("update sensor "
			 		+ " SET sensor.position ="+sensor.getPosition()+", type='"+sensor.getType()+"',"
			 		+ " brand='"+sensor.getBrand()+"'"
			 + " WHERE id ="+sensor.getId());
			 
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
	
	@PostMapping("/insertSensor")
	public boolean insertSensor(@RequestBody Sensor sensor) {

		Statement stmt = null;
		boolean result = true;
		Connection conn = Database.getConnection();

		try {			
			 stmt = conn.createStatement();
			 stmt.executeUpdate("INSERT INTO sensor (sensor.position, type, brand) "
			 		+ " VALUES ("+sensor.getPosition()+", '"+sensor.getType()+"', '"+sensor.getBrand()+"' "
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
}
