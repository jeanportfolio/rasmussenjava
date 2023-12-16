package ci.inventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import ci.inventory.dao.interfaces.IStock_movementDao;
import ci.inventory.entity.Stock_movement;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.LoggingLog4j;

public class Stock_movementDao implements IStock_movementDao{

	private Connection con = DbConnection.getConnection();
	//private Logger logManager = Logging.setLoggerName(Stock_movementDao.class.getName());
	private static Logger logManager = new LoggingLog4j().getLogger(Stock_movementDao.class.getName());
	
	@Override
	public Stock_movement create(Stock_movement stoct_movement) {
		String req = "INSERT INTO stoct_movement (title, idusers) VALUES (?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, stoct_movement.getTitle());
			pstmt.setInt(2, stoct_movement.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				stoct_movement.setId(rs.getInt(1));
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
			return null;
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
		}
		
		return stoct_movement;
	}

	@Override
	public Stock_movement getById(int id) {
		Stock_movement stoct_movement = null;
		String req = "SELECT * FROM stoct_movement WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				stoct_movement = new Stock_movement();
				
				stoct_movement.setId(rs.getInt("id"));
				stoct_movement.setTitle(rs.getString("title"));
				stoct_movement.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				stoct_movement.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				stoct_movement.setIdusers(rs.getInt("idusers"));
				
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
			return null;
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
		}
		
		return stoct_movement;
	}

	@Override
	public Stock_movement update(Stock_movement stoct_movement) {
		String req = "UPDATE stoct_movement SET title = ?, idusers = ? WHERE id = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setString(1, stoct_movement.getTitle());
			pstmt.setInt(2, stoct_movement.getIdusers());
			pstmt.setInt(3, stoct_movement.getId());
			
			pstmt.executeUpdate();
			if(result == 0)
				stoct_movement.setId(0);
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
			return null;
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
		}
		
		return stoct_movement;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM stoct_movement WHERE id = ?";
		PreparedStatement pstmt = null;
		int result;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, id);
			
			result = pstmt.executeUpdate();
			
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
			return -1;
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
		}
		
		return result;
	}

	@Override
	public List<Stock_movement> getAll() {
		
		Stock_movement stoct_movement = null;
		List<Stock_movement> listStoct_movement = new ArrayList<>();
		String req = "SELECT * FROM stoct_movement";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				stoct_movement = new Stock_movement();
				
				stoct_movement.setId(rs.getInt("id"));
				stoct_movement.setTitle(rs.getString("title"));
				stoct_movement.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				stoct_movement.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				stoct_movement.setIdusers(rs.getInt("idusers"));
				
				listStoct_movement.add(stoct_movement);
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
			return null;
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
		}
		
		return listStoct_movement;
	}

	@Override
	public Stock_movement getNatureMovement(String nature) {
		Stock_movement stoct_movement = null;
		String req = "SELECT * FROM stoct_movement WHERE title = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setString(1, nature);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				stoct_movement = new Stock_movement();
				
				stoct_movement.setId(rs.getInt("id"));
				stoct_movement.setTitle(rs.getString("title"));
				stoct_movement.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				stoct_movement.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				stoct_movement.setIdusers(rs.getInt("idusers"));
				
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
			return null;
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
		}
		
		return stoct_movement;
	}
	
}
