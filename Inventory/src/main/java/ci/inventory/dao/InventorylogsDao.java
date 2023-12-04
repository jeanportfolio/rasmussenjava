package ci.inventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ci.inventory.dao.interfaces.IInventorylogsDao;
import ci.inventory.entity.Inventorylogs;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.Logging;

public class InventorylogsDao implements IInventorylogsDao{
	private Connection con = DbConnection.getConnection();
	private Logger logManager = Logging.setLoggerName(InventorylogsDao.class.getName());

	@Override
	public Inventorylogs create(Inventorylogs inventorylogs) {
		String req = "INSERT INTO inventorylogs (title, iduser) VALUES (?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, inventorylogs.getTitle());
			pstmt.setInt(2, inventorylogs.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				inventorylogs.setId(rs.getInt(1));
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
			return null;
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		
		return inventorylogs;
	}

	@Override
	public Inventorylogs getById(int id) {
		Inventorylogs inventorylogs = null;
		String req = "SELECT * FROM inventorylogs WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				inventorylogs = new Inventorylogs();
				
				inventorylogs.setId(rs.getInt("id"));
				inventorylogs.setTitle(rs.getString("title"));
				inventorylogs.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				inventorylogs.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				inventorylogs.setIdusers(rs.getInt("idusers"));
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
			return null;
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		
		return inventorylogs;
	}

	@Override
	public Inventorylogs update(Inventorylogs inventorylogs) {
		String req = "UPDATE inventorylogs SET title =?, iduser = ? WHERE id = ?";
		PreparedStatement pstmt = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setString(1, inventorylogs.getTitle());
			pstmt.setInt(2, inventorylogs.getIdusers());
			pstmt.setInt(3, inventorylogs.getId());
			
			int result = pstmt.executeUpdate();
			if(result == 0)
				inventorylogs.setId(0);
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
			return null;
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		
		return inventorylogs;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM inventorylogs WHERE id = ?";
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
				logManager.log(Level.SEVERE, e.getMessage(), e);
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
			return -1;
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		
		return result;
	}

	@Override
	public List<Inventorylogs> getAll() {
		Inventorylogs inventorylogs = null;
		List<Inventorylogs> listInventorylogs = new ArrayList<>();
		String req = "SELECT * FROM inventorylogs";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				inventorylogs = new Inventorylogs();
				
				inventorylogs.setId(rs.getInt("id"));
				inventorylogs.setTitle(rs.getString("title"));
				inventorylogs.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				inventorylogs.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				inventorylogs.setIdusers(rs.getInt("idusers"));
				
				listInventorylogs.add(inventorylogs);
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
			return null;
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		
		return listInventorylogs;
	}
}
