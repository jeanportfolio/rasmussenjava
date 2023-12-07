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

import ci.inventory.dao.interfaces.ICustomersDao;
import ci.inventory.entity.Customers;

import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.Logging;
import ci.inventory.utility.log.LoggingLog4j;

public class CustomersDao implements ICustomersDao{
	private Connection con = DbConnection.getConnection();
	//private Logger logManager = Logging.setLoggerName(CustomersDao.class.getName());
	private static Logger logManager = new LoggingLog4j().getLogger(CustomersDao.class.getName());

	@Override
	public Customers create(Customers customers) {
		
		String req = "INSERT INTO customers (customername, phone, address, idusers) VALUES (?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, customers.getCustomername());
			pstmt.setString(2, customers.getPhone());
			pstmt.setString(3, customers.getAddress());
			pstmt.setInt(4, customers.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				customers.setId(rs.getInt(1));
			}
			con.commit();
			logManager.log(Level.INFO, "Customers created");
		} catch (SQLException e) {
			System.err.println("Error "+ e.getMessage());
			logManager.log(Level.ERROR, e.getMessage(), e.getClass());
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
		
		return customers;
	}

	@Override
	public Customers getById(int id) {
		Customers customers = null;
		String req = "SELECT * FROM customers WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				customers = new Customers();
				
				customers.setId(rs.getInt("id"));
				customers.setCustomername(rs.getString("customername"));
				customers.setPhone(rs.getString("phone"));
				customers.setAddress(rs.getString("address"));
				customers.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				customers.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				customers.setIdusers(rs.getInt("idusers"));
				
			}
			con.commit();
			logManager.log(Level.INFO, "Customers with id "+ id + " retrieved");
		} catch (SQLException e) {
			System.err.println("Error "+ e.getMessage());
			logManager.log(Level.ERROR, e.getMessage(), e.getClass());
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
		
		return customers;
	}

	@Override
	public Customers update(Customers customers) {
		String req = "UPDATE customers SET customername = ?, phone = ?, address = ?, idusers = ? WHERE id = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, customers.getCustomername());
			pstmt.setString(2, customers.getPhone());
			pstmt.setString(3, customers.getAddress());
			pstmt.setInt(4, customers.getIdusers());
			pstmt.setInt(5, customers.getId());
			
			result = pstmt.executeUpdate();

			if(result == 0) {
				customers.setId(0);
			}
			con.commit();
			logManager.log(Level.INFO, "Customers saved");
		} catch (SQLException e) {
			System.err.println("Error "+ e.getMessage());
			logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			return null;
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
		}
		
		return customers;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM customers WHERE id = ?";
		PreparedStatement pstmt = null;
		int result;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, id);
			
			result = pstmt.executeUpdate();
			
			con.commit();
			logManager.log(Level.INFO, "Customers with id "+ id + " deleted");
		} catch (SQLException e) {
			System.err.println("Error "+ e.getMessage());
			logManager.log(Level.ERROR, e.getMessage(), e.getClass());
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
	public List<Customers> getAll() {
		Customers customers = null;
		List<Customers> listSuppliers = new ArrayList<>();
		String req = "SELECT * FROM customers";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				customers = new Customers();
				
				customers.setId(rs.getInt("id"));
				customers.setCustomername(rs.getString("customername"));
				customers.setPhone(rs.getString("phone"));
				customers.setAddress(rs.getString("address"));
				customers.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				customers.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				customers.setIdusers(rs.getInt("idusers"));
				
				listSuppliers.add(customers);
			}
			con.commit();
			logManager.log(Level.INFO, "Customers list retrieved");
		} catch (SQLException e) {
			System.err.println("Error "+ e.getMessage());
			logManager.log(Level.ERROR, e.getMessage(), e.getClass());
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
		
		return listSuppliers;
	}
}
