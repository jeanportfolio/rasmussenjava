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

import ci.inventory.dao.interfaces.IUsersDao;
import ci.inventory.entity.Users;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.Logging;

public class UsersDao implements IUsersDao{
	private Connection con = DbConnection.getConnection();
	private Logger logManager = Logging.setLoggerName(UsersDao.class.getName());

	@Override
	public Users create(Users users) {
		String req = "INSERT INTO users (login, password, fisrtname, lastname, birthday, idusersrole, iduserstatus, idusers) VALUES "
				+ "(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, users.getLogin());
			pstmt.setString(2, users.getPassword());
			pstmt.setString(3, users.getFisrtname());
			pstmt.setString(4, users.getLastname());
			pstmt.setString(5, users.getBirthday());
			pstmt.setInt(6, users.getIdusersrole());
			pstmt.setInt(7, users.getIduserstatus());
			pstmt.setInt(8, users.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				users.setId(rs.getInt(1));
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
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		
		return users;
	}

	@Override
	public Users getById(int id) {
		Users users = null;
		String req = "SELECT * FROM users WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				users = new Users();
				
				users.setId(rs.getInt("id"));
				users.setLogin(rs.getString("login"));
				users.setPassword(rs.getString("password"));
				users.setFisrtname(rs.getString("fisrtname"));
				users.setBirthday(rs.getString("birthday"));
				users.setLastname(rs.getString("lastname"));
				users.setIduserstatus(rs.getInt("iduserstatus"));
				users.setIdusersrole(rs.getInt("idusersrole"));
				users.setIdusers(rs.getInt("dusers"));
				users.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				users.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				
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
		
		return users;
	}

	@Override
	public Users update(Users users) {
		String req = "UPDATE users SET login = ? , password = ?, fisrtname = ?, lastname = ?, birthday = ?, idusersrole = ?,"
				+ " iduserstatus = ?, idusers = ?) WHERE id = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, users.getLogin());
			pstmt.setString(2, users.getPassword());
			pstmt.setString(3, users.getFisrtname());
			pstmt.setString(4, users.getLastname());
			pstmt.setString(5, users.getBirthday());
			pstmt.setInt(6, users.getIdusersrole());
			pstmt.setInt(7, users.getIduserstatus());
			pstmt.setInt(8, users.getIdusers());
			pstmt.setInt(9, users.getId());
			
			result =  pstmt.executeUpdate();
			if(result == 0)
				users.setId(0);
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
		
		return users;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM users WHERE id = ?";
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
	public List<Users> getAll() {
		Users users = null;
		List<Users> listUsers = new ArrayList<>();
		String req = "SELECT * FROM users";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				users = new Users();
				
				users.setId(rs.getInt("id"));
				users.setLogin(rs.getString("login"));
				users.setPassword(rs.getString("password"));
				users.setFisrtname(rs.getString("fisrtname"));
				users.setBirthday(rs.getString("birthday"));
				users.setLastname(rs.getString("lastname"));
				users.setIduserstatus(rs.getInt("iduserstatus"));
				users.setIdusersrole(rs.getInt("idusersrole"));
				users.setIdusers(rs.getInt("idusers"));
				users.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				users.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				
				listUsers.add(users);
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
		
		return listUsers;
	}

	@Override
	public Users connectUser(String login) {
		Users users = null;
		String req = "SELECT * FROM users WHERE login = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(req);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				users = new Users();
				
				users.setId(rs.getInt("id"));
				users.setLogin(rs.getString("login"));
				users.setPassword(rs.getString("password"));
				users.setFisrtname(rs.getString("fisrtname"));
				users.setBirthday(rs.getString("birthday"));
				users.setLastname(rs.getString("lastname"));
				users.setIduserstatus(rs.getInt("iduserstatus"));
				users.setIdusersrole(rs.getInt("idusersrole"));
				users.setIdusers(rs.getInt("idusers"));
				users.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				users.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				
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
		
		return users;
	}
	
}
