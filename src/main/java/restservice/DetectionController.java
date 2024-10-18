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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Detection;
import util.Database;

@RestController
@RequestMapping("/detection")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DetectionController {

	@GetMapping("/countDetectionsOfSystem")
	public Integer countDetectionsOfSystem(@RequestParam Integer system) {

		Statement stmt = null;
		ResultSet rs = null;
		Integer result = 0;
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * "
			 		+ " FROM detection inner join sensor on detection.sensor=sensor.id inner join position on sensor.position=position.id "
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
	
	@GetMapping("/getDetectionsOfSystem")
	public List<Detection> getDetectionsOfSystem(@RequestParam Integer system) {

		Statement stmt = null;
		ResultSet rs = null;
		List<Detection> result = new ArrayList<Detection>();
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * "
			 		+ " FROM detection inner join sensor on detection.sensor=sensor.id inner join position on sensor.position=position.id "
			 		+ " WHERE position.system = "+system);
			 
			 if(rs != null) {
				 while(rs.next()) {
					 result.add(new Detection(rs.getInt("detection.id"), rs.getInt("sensor.id"), rs.getString("value"), rs.getDate("date"), rs.getString("type"), rs.getString("brand"), rs.getString("name_position")));
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
	
	@DeleteMapping("/deleteDetection")
	public boolean deleteDetection(@RequestParam Integer detection) {

		Statement stmt = null;
		boolean result = true;
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 stmt.executeUpdate("DELETE FROM detection "
			 		+ " WHERE id = "+detection);
			 
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
}
