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

import ci.inventory.dao.interfaces.ILogs_detailDao;
import ci.inventory.entity.Logs_detail;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.Logging;

public class Logs_detailDao implements ILogs_detailDao{
	private Connection con = DbConnection.getConnection();
	private Logger logManager = Logging.setLoggerName(Logs_detailDao.class.getName());

	@Override
	public Logs_detail create(Logs_detail logs_detail) {
		String req = "INSERT INTO logs_detail (creationtime, ocurence_id, description, table_affected, idusers, idlogs,"
				+ " ideventoccur) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setTime(1, logs_detail.getCreationtime());
			pstmt.setInt(2, logs_detail.getOcurence_id());
			pstmt.setString(3, logs_detail.getDescription());
			pstmt.setString(4, logs_detail.getTable_affected());
			pstmt.setInt(5, logs_detail.getIdusers());
			pstmt.setInt(6, logs_detail.getIdlogs());
			pstmt.setInt(7, logs_detail.getIdeventoccur());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				logs_detail.setId(rs.getInt(1));
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
		
		return logs_detail;
	}

	@Override
	public Logs_detail getById(int id) {
		Logs_detail logs_detail = null;
		String req = "SELECT * FROM logs_detail WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				logs_detail = new Logs_detail();
				
				logs_detail.setId(rs.getInt("id"));
				logs_detail.setCreationtime(rs.getTime("creationtime"));
				logs_detail.setDescription(rs.getString("description"));
				logs_detail.setIdeventoccur(rs.getInt("ideventoccur"));
				logs_detail.setIdlogs(rs.getInt("idlogs"));
				logs_detail.setOcurence_id(rs.getInt("ocurence_id"));
				logs_detail.setTable_affected(rs.getString("table_affected"));
				logs_detail.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				logs_detail.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				logs_detail.setIdusers(rs.getInt("idusers"));
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
		
		return logs_detail;
	}

	@Override
	public Logs_detail update(Logs_detail logs_detail) {
		String req = "UPDATE logs_detail SET creationtime = ?, ocurence_id =?, description =?, table_affected= ?, idusers =?,"
				+ " idlogs =?, ideventoccur =? ? WHERE id = ?";
		PreparedStatement pstmt = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setTime(1, logs_detail.getCreationtime());
			pstmt.setInt(2, logs_detail.getOcurence_id());
			pstmt.setString(3, logs_detail.getDescription());
			pstmt.setString(4, logs_detail.getTable_affected());
			pstmt.setInt(5, logs_detail.getIdusers());
			pstmt.setInt(6, logs_detail.getIdlogs());
			pstmt.setInt(7, logs_detail.getIdeventoccur());
			pstmt.setInt(8, logs_detail.getId());
			
			int result = pstmt.executeUpdate();
			if(result == 0)
				logs_detail.setId(0);
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
		
		return logs_detail;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM logs_detail WHERE id = ?";
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
	public List<Logs_detail> getAll() {
		Logs_detail logs_detail = null;
		List<Logs_detail> listLogs_detail = new ArrayList<>();
		String req = "SELECT * FROM logs_detail";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				logs_detail = new Logs_detail();
				
				logs_detail.setId(rs.getInt("id"));
				logs_detail.setCreationtime(rs.getTime("creationtime"));
				logs_detail.setDescription(rs.getString("description"));
				logs_detail.setIdeventoccur(rs.getInt("ideventoccur"));
				logs_detail.setIdlogs(rs.getInt("idlogs"));
				logs_detail.setOcurence_id(rs.getInt("ocurence_id"));
				logs_detail.setTable_affected(rs.getString("table_affected"));
				logs_detail.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				logs_detail.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				logs_detail.setIdusers(rs.getInt("idusers"));
				
				listLogs_detail.add(logs_detail);
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
		
		return listLogs_detail;
	}
}
