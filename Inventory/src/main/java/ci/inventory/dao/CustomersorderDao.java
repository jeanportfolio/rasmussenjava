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

import ci.inventory.dao.interfaces.ICustomersorderDao;
import ci.inventory.entity.Customersorder;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.Logging;

public class CustomersorderDao implements ICustomersorderDao{
	private Connection con = DbConnection.getConnection();
	private Logger logManager = Logging.setLoggerName(CustomersorderDao.class.getName());

	@Override
	public Customersorder create(Customersorder customerOrder) {
		String req = "INSERT INTO customerOrder (totalamount, idcustomers, customerordernumber, idusers) VALUES (?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setBigDecimal(1, customerOrder.getTotalamount());
			pstmt.setInt(2, customerOrder.getIdcustomers());
			pstmt.setString(3, customerOrder.getCustomerordernumber());
			pstmt.setInt(4, customerOrder.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				customerOrder.setId(rs.getInt(1));
			}
			con.commit();
		} catch (SQLException e) {
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
		
		return customerOrder;
	}

	@Override
	public Customersorder getById(int id) {
		String req = "SELECT * FROM customerOrder WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		Customersorder customerOrder = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				customerOrder = new Customersorder();
				
				customerOrder.setId(rs.getInt("id"));
				customerOrder.setTotalamount(rs.getBigDecimal("totalamount"));
				customerOrder.setIdcustomers(rs.getInt("idcustomers"));
				customerOrder.setCustomerordernumber(rs.getString("customerordernumber"));
				customerOrder.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				customerOrder.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				customerOrder.setIdusers(rs.getInt("idusers"));
				
			}
			con.commit();
		} catch (SQLException e) {
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
		
		return customerOrder;
	}

	@Override
	public Customersorder update(Customersorder customerOrder) {
		String req = "UPDATE customerOrder SET totalamount = ?, idcustomers=?, customerordernumber = ?, idusers=?) WHERE id = ?";
		PreparedStatement pstmt = null;
		int result= 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setBigDecimal(1, customerOrder.getTotalamount());
			pstmt.setInt(2, customerOrder.getIdcustomers());
			pstmt.setString(3, customerOrder.getCustomerordernumber());
			pstmt.setInt(4, customerOrder.getIdusers());
			pstmt.setInt(5, customerOrder.getId());
			
			pstmt.executeUpdate();
			if(result == 0)
				customerOrder.setId(0);
			con.commit();
		} catch (SQLException e) {
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
		
		return customerOrder;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM customerOrder WHERE id = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, id);
			
			result = pstmt.executeUpdate();
			
			con.commit();
		} catch (SQLException e) {
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
	public List<Customersorder> getAll() {
		List<Customersorder> listCustomersorder = new ArrayList<>();
		String req = "SELECT * FROM customerOrder";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			Customersorder customerOrder;
			while(rs.next()) {
				customerOrder = new Customersorder();
				
				customerOrder.setId(rs.getInt("id"));
				customerOrder.setTotalamount(rs.getBigDecimal("totalamount"));
				customerOrder.setIdcustomers(rs.getInt("idcustomers"));
				customerOrder.setCustomerordernumber(rs.getString("customerordernumber"));
				customerOrder.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				customerOrder.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				customerOrder.setIdusers(rs.getInt("idusers"));
				
				listCustomersorder.add(customerOrder);
			}
			con.commit();
		} catch (SQLException e) {
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
		
		return listCustomersorder;
	}
}
