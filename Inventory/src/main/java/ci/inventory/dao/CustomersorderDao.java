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

import ci.inventory.dao.interfaces.ICustomersorderDao;
import ci.inventory.entity.Customersorder;
import ci.inventory.entity.Orderitems;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.LoggingLog4j;

public class CustomersorderDao implements ICustomersorderDao{
	private Connection con = DbConnection.getConnection();
	//private Logger logManager = Logging.setLoggerName(CustomersorderDao.class.getName());
	private static Logger logManager = new LoggingLog4j().getLogger(CustomersorderDao.class.getName());

	@Override
	public Customersorder create(Customersorder customerOrder) {
		String req = "INSERT INTO customersorder (totalamount, idcustomers, customerordernumber, idusers) VALUES (?,?,?,?)";
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
		
		return customerOrder;
	}

	@Override
	public Customersorder getById(int id) {
		String req = "SELECT * FROM customersorder WHERE id = ?";
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
		
		return customerOrder;
	}

	@Override
	public Customersorder update(Customersorder customerOrder) {
		String req = "UPDATE customersorder SET totalamount = ?, idcustomers=?, customerordernumber = ?, idusers=?) WHERE id = ?";
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
		
		return customerOrder;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM customersorder WHERE id = ?";
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
	public List<Customersorder> getAll() {
		List<Customersorder> listCustomersorder = new ArrayList<>();
		String req = "SELECT * FROM customersorder";
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
		
		return listCustomersorder;
	}

	@Override
	public Customersorder create(Customersorder customerorder, List<Orderitems> listorderitem) {
		String req = "INSERT INTO customersorder (totalamount, idcustomers, customerordernumber, idusers) VALUES (?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setBigDecimal(1, customerorder.getTotalamount());
			pstmt.setInt(2, customerorder.getIdcustomers());
			pstmt.setString(3, customerorder.getCustomerordernumber());
			pstmt.setInt(4, customerorder.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				customerorder.setId(rs.getInt(1));
			}
			
			String req2;
			PreparedStatement pstmt2 = null;
			for(int i = 0; i < listorderitem.size(); i++) {
				Orderitems orderitem = listorderitem.get(i);
				if(orderitem.getId() <= 0)
                {
					req2 = "INSERT INTO orderitems (idproduct, idcustomerorder, quantity, price, idusers) VALUES (?,?,?,?,?)";
					pstmt2 = con.prepareStatement(req2);
					pstmt2.setInt(1, orderitem.getIdproduct());
        			pstmt2.setInt(2, orderitem.getIdcustomerorder());
        			pstmt2.setInt(3, orderitem.getQuantity());
        			pstmt2.setBigDecimal(4, orderitem.getPrice());
        			pstmt2.setInt(5, orderitem.getIdusers());
					
					pstmt2.executeUpdate();
                }else{
                	req2 = "UPDATE orderitems SET idproduct =?, idcustomerorder = ?, quantity =?, price =?, iduser =? WHERE id = ?";
            		
                	pstmt2 = con.prepareStatement(req2);
                	pstmt2.setInt(1, orderitem.getIdproduct());
        			pstmt2.setInt(2, orderitem.getIdcustomerorder());
        			pstmt2.setInt(3, orderitem.getQuantity());
        			pstmt2.setBigDecimal(4, orderitem.getPrice());
        			pstmt2.setInt(5, orderitem.getIdusers());
        			pstmt2.setInt(6, orderitem.getId());
            			
            		pstmt2.executeUpdate();
				}
			}
			
			con.commit();
			System.out.println("Customer order save successfully");
			logManager.log(Level.INFO, "Customer order save successfully");
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
		
		return customerorder;
	}
	
	@Override
	public Customersorder update(Customersorder customerorder, List<Orderitems> listorderitem) {
		String req = "UPDATE customersorder SET totalamount = ?, idcustomers=?, customerordernumber = ?, idusers=?) WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setBigDecimal(1, customerorder.getTotalamount());
			pstmt.setInt(2, customerorder.getIdcustomers());
			pstmt.setString(3, customerorder.getCustomerordernumber());
			pstmt.setInt(4, customerorder.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				customerorder.setId(rs.getInt(1));
			}
			
			String req2;
			PreparedStatement pstmt2 = null;
			for(int i = 0; i < listorderitem.size(); i++) {
				Orderitems orderitem = listorderitem.get(i);
				if(orderitem.getId() <= 0)
                {
					req2 = "INSERT INTO orderitems (idproduct, idcustomerorder, quantity, price, idusers) VALUES (?,?,?,?,?)";
					pstmt2 = con.prepareStatement(req2);
					pstmt2.setInt(1, orderitem.getIdproduct());
        			pstmt2.setInt(2, orderitem.getIdcustomerorder());
        			pstmt2.setInt(3, orderitem.getQuantity());
        			pstmt2.setBigDecimal(4, orderitem.getPrice());
        			pstmt2.setInt(5, orderitem.getIdusers());
					
					pstmt2.executeUpdate();
                }else{
                	req2 = "UPDATE orderitems SET idproduct =?, idcustomerorder = ?, quantity =?, price =?, iduser =? WHERE id = ?";
            		
                	pstmt2 = con.prepareStatement(req2);
                	pstmt2.setInt(1, orderitem.getIdproduct());
        			pstmt2.setInt(2, orderitem.getIdcustomerorder());
        			pstmt2.setInt(3, orderitem.getQuantity());
        			pstmt2.setBigDecimal(4, orderitem.getPrice());
        			pstmt2.setInt(5, orderitem.getIdusers());
        			pstmt2.setInt(6, orderitem.getId());
            			
            		pstmt2.executeUpdate();
				}
			}
			
			con.commit();
			System.out.println("Customer order save successfully");
			logManager.log(Level.INFO, "Customer order save successfully");
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
		
		return customerorder;
	}
}
