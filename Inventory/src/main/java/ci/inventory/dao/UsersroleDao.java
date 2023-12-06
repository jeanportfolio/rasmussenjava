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

import ci.inventory.dao.interfaces.IUsersroleDao;
import ci.inventory.entity.Usersrole;
import ci.inventory.utility.DbConnection;

import ci.inventory.utility.log.LoggingLog4j;

public class UsersroleDao implements IUsersroleDao{
	private Connection con = DbConnection.getConnection();
	//private Logger logManager = Logging.setLoggerName(UsersroleDao.class.getName());
	private static Logger logManager = new LoggingLog4j().getLogger(UsersroleDao.class.getName());

	//Insertion of a User role
	@Override
	public Usersrole create(Usersrole usersrole) {
		
		String req = "INSERT INTO usersrole (title, description, iduser) VALUES (?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, usersrole.getTitle());
			pstmt.setString(2, usersrole.getDescription());
			pstmt.setInt(3, usersrole.getIduser());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				usersrole.setId(rs.getInt(1));
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
		
		return usersrole;
	}

	@Override
	public Usersrole getById(int id) {
		Usersrole usersrole = null;
		String req = "SELECT * FROM usersrole WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				usersrole = new Usersrole();
				
				usersrole.setId(rs.getInt("id"));
				usersrole.setTitle(rs.getString("title"));
				usersrole.setDescription(rs.getString("description"));
				usersrole.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				usersrole.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				usersrole.setIduser(rs.getInt("iduser"));
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
		
		return usersrole;
	}

	@Override
	public Usersrole update(Usersrole usersrole) {
		String req = "UPDATE usersrole SET title = ?, description = ?, iduser = ? WHERE id = ?";
		PreparedStatement pstmt = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setString(1, usersrole.getTitle());
			pstmt.setString(2, usersrole.getDescription());
			pstmt.setInt(3, usersrole.getIduser());
			pstmt.setInt(4, usersrole.getId());
			
			int result = pstmt.executeUpdate();
			if(result == 0)
				usersrole.setId(0);
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
		
		return usersrole;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM usersrole WHERE id = ?";
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
	public List<Usersrole> getAll() {
		Usersrole usersrole = null;
		List<Usersrole> listRole = new ArrayList<>();
		String req = "SELECT * FROM usersrole";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				usersrole = new Usersrole();
				
				usersrole.setId(rs.getInt("id"));
				usersrole.setTitle(rs.getString("title"));
				usersrole.setDescription(rs.getString("description"));
				usersrole.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				usersrole.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				usersrole.setIduser(rs.getInt("iduser"));
				
				listRole.add(usersrole);
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
		
		return listRole;
	}
}
