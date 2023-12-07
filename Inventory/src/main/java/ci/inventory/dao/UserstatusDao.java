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

import ci.inventory.dao.interfaces.IUserstatusDao;
import ci.inventory.entity.Userstatus;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.LoggingLog4j;

public class UserstatusDao implements IUserstatusDao{
	private Connection con = DbConnection.getConnection();
	//private Logger logManager = Logging.setLoggerName(UserstatusDao.class.getName());
	private static Logger logManager = new LoggingLog4j().getLogger(UserstatusDao.class.getName());

	@Override
	public Userstatus create(Userstatus userstatus) {
		
		String req = "INSERT INTO userstatus (title, idusers) VALUES (?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, userstatus.getTitle());
			pstmt.setInt(2, userstatus.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				userstatus.setId(rs.getInt(1));
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
		
		return userstatus;
	}

	@Override
	public Userstatus getById(int id) {
		Userstatus userstatus = null;
		String req = "SELECT * FROM userstatus WHERE id = ?";
		PreparedStatement pstmt = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userstatus = new Userstatus();
				
				userstatus.setId(rs.getInt("id"));
				userstatus.setTitle(rs.getString("title"));
				userstatus.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				userstatus.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				userstatus.setIdusers(rs.getInt("idusers"));
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
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
		}
		
		return userstatus;
	}

	@Override
	public Userstatus update(Userstatus userstatus) {
		String req = "UPDATE userstatus SET title = ?, idusers = ? WHERE id = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setString(1, userstatus.getTitle());
			pstmt.setInt(2, userstatus.getIdusers());
			pstmt.setInt(3, userstatus.getId());
			
			result = pstmt.executeUpdate();
			if(result == 0)
				userstatus.setId(0);
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
		
		return userstatus;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM userstatus WHERE id = ?";
		PreparedStatement pstmt = null;
		int result = 0;
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
	public List<Userstatus> getAll() {
		List<Userstatus> listUserstatus = new ArrayList<>();
		String req = "SELECT * FROM userstatus";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			Userstatus userstatus;
			while(rs.next()) {
				userstatus = new Userstatus();
				
				userstatus.setId(rs.getInt("id"));
				userstatus.setTitle(rs.getString("title"));
				userstatus.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				userstatus.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				userstatus.setIdusers(rs.getInt("idusers"));
				listUserstatus.add(userstatus);
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
		
		return listUserstatus;
	}
}
