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

import ci.inventory.dao.interfaces.IEvent_occurDao;
import ci.inventory.entity.Event_occur;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.Logging;

public class Event_occurDao implements IEvent_occurDao{
	private Connection con = DbConnection.getConnection();
	private Logger logManager = Logging.setLoggerName(Event_occurDao.class.getName());

	@Override
	public Event_occur create(Event_occur event_occur) {
		String req = "INSERT INTO event_occur (title, iduser) VALUES (?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, event_occur.getTitle());
			pstmt.setInt(2, event_occur.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				event_occur.setId(rs.getInt(1));
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
			System.err.println("Error "+ e.getMessage());
			logManager.log(Level.SEVERE, e.getMessage(), e);
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
		
		return event_occur;
	}

	@Override
	public Event_occur getById(int id) {
		Event_occur event_occur = null;
		String req = "SELECT * FROM event_occur WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				event_occur = new Event_occur();
				
				event_occur.setId(rs.getInt("id"));
				event_occur.setTitle(rs.getString("title"));
				event_occur.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				event_occur.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				event_occur.setIdusers(rs.getInt("idusers"));
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
			System.err.println("Error "+ e.getMessage());
			logManager.log(Level.SEVERE, e.getMessage(), e);
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
		
		return event_occur;
	}

	@Override
	public Event_occur update(Event_occur event_occur) {
		String req = "UPDATE event_occur SET title =?, iduser = ? WHERE id = ?";
		PreparedStatement pstmt = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setString(1, event_occur.getTitle());
			pstmt.setInt(2, event_occur.getIdusers());
			pstmt.setInt(3, event_occur.getId());
			
			int result = pstmt.executeUpdate();
			if(result == 0)
				event_occur.setId(0);
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
			System.err.println("Error "+ e.getMessage());
			logManager.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		
		return event_occur;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM event_occur WHERE id = ?";
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
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
			System.err.println("Error "+ e.getMessage());
			logManager.log(Level.SEVERE, e.getMessage(), e);
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
	public List<Event_occur> getAll() {
		Event_occur event_occur = null;
		List<Event_occur> listInventorylogs = new ArrayList<>();
		String req = "SELECT * FROM event_occur";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				event_occur = new Event_occur();
				
				event_occur.setId(rs.getInt("id"));
				event_occur.setTitle(rs.getString("title"));
				event_occur.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				event_occur.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				event_occur.setIdusers(rs.getInt("idusers"));
				
				listInventorylogs.add(event_occur);
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
			System.err.println("Error "+ e.getMessage());
			logManager.log(Level.SEVERE, e.getMessage(), e);
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
