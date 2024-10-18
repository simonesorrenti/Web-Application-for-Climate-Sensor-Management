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
import model.Position;
import util.Database;

@RestController
@RequestMapping("/position")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PositionController {

	@GetMapping("/countPositionsOfSystem")
	public Integer countPositionsOfSystem(@RequestParam Integer system) {

		Statement stmt = null;
		ResultSet rs = null;
		Integer result = 0;
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * "
			 		+ " FROM position "
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
	
	@GetMapping("/getAllPositionsOfSystem")
	public List<String> getAllPositionsOfSystem(@RequestParam Integer system) {

		Statement stmt = null;
		ResultSet rs = null;
		List<String> result = new ArrayList<String>();
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select position.name_position "
			 		+ " FROM position "
			 		+ " WHERE position.system = "+system);
			 
			 if(rs != null) {
				 while(rs.next()) {
					 result.add(rs.getString("name_position"));
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
	
	@GetMapping("/getAllPositions")
	public List<Position> getAllPositions() {

		Statement stmt = null;
		ResultSet rs = null;
		List<Position> result = new ArrayList<Position>();
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * "
			 		+ " FROM position");
			 
			 if(rs != null) {
				 while(rs.next()) {
					 result.add(new Position(rs.getInt("id"), rs.getInt("system"), rs.getString("name_position"), rs.getString("description")));
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
	
	@DeleteMapping("/deletePosition")
	public boolean deletePosition(@RequestParam Integer position) {

		Statement stmt = null;
		boolean result = true;
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 stmt.executeUpdate("DELETE FROM position "
			 		+ " WHERE id = "+position);
			 
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
	
	@PutMapping("/updatePosition")
	public boolean updatePosition(@RequestBody Position position) {

		Statement stmt = null;
		boolean result = true;
		Connection conn = Database.getConnection();
        
		try {			
			 stmt = conn.createStatement();
			 stmt.executeUpdate("update position "
			 		+ " SET position.system ="+position.getSystem()+", name_position='"+position.getNamePosition()+"',"
			 		+ " description='"+position.getDescription()+"'"
			 + " WHERE id ="+position.getId());
			 
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
	
	@PostMapping("/insertPosition")
	public boolean insertPosition(@RequestBody Position position) {

		Statement stmt = null;
		boolean result = true;
		Connection conn = Database.getConnection();

		try {			
			 stmt = conn.createStatement();
			 stmt.executeUpdate("INSERT INTO position (position.system, name_position, description) "
			 		+ " VALUES ("+position.getSystem()+", '"+position.getNamePosition()+"', '"+position.getDescription()+"' "
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
	
	@GetMapping("/getAllIdPositions")
	public List<Integer> getAllIdPositions() {

		Statement stmt = null;
		ResultSet rs = null;
		List<Integer> result = new ArrayList<Integer>();
		Connection conn = Database.getConnection();
		
		try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * "
			 		+ " FROM position ");
			 
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
