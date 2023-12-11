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

import ci.inventory.dao.interfaces.IOrderitemsDao;
import ci.inventory.entity.Orderitems;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.Logging;

public class OrderitemsDao implements IOrderitemsDao{
	private Connection con = DbConnection.getConnection();
	private Logger logManager = Logging.setLoggerName(OrderitemsDao.class.getName());

	@Override
	public Orderitems create(Orderitems orderitems) {
		String req = "INSERT INTO orderitems (idproduct, idcustomerorder, quantity, price, idusers) VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, orderitems.getIdproduct());
			pstmt.setInt(2, orderitems.getIdcustomerorder());
			pstmt.setInt(3, orderitems.getQuantity());
			pstmt.setBigDecimal(4, orderitems.getPrice());
			pstmt.setInt(5, orderitems.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				orderitems.setId(rs.getInt(1));
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
		
		return orderitems;
	}

	@Override
	public Orderitems getById(int id) {
		String req = "SELECT * FROM orderitems";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		Orderitems orderitems = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				orderitems = new Orderitems();
				
				orderitems.setId(rs.getInt("id"));
				orderitems.setIdcustomerorder(rs.getInt(""));
				orderitems.setIdproduct(rs.getInt(""));
				orderitems.setPrice(rs.getBigDecimal("price"));
				orderitems.setQuantity(rs.getInt("quantity"));
				orderitems.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				orderitems.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				orderitems.setIdusers(rs.getInt("idusers"));
				
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
		
		return orderitems;
	}

	@Override
	public Orderitems update(Orderitems orderitems) {
		String req = "UPDATE orderitems SET idproduct =?, idcustomerorder = ?, quantity =?, price =?, iduser =? WHERE id = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, orderitems.getIdproduct());
			pstmt.setInt(2, orderitems.getIdcustomerorder());
			pstmt.setInt(3, orderitems.getQuantity());
			pstmt.setBigDecimal(4, orderitems.getPrice());
			pstmt.setInt(5, orderitems.getIdusers());
			pstmt.setInt(6, orderitems.getId());
			
			pstmt.executeUpdate();
			
			if(result == 0)
				orderitems.setId(0);
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
		
		return orderitems;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM orderitems WHERE id = ?";
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
	public List<Orderitems> getAll() {
		List<Orderitems> listOrderitems = new ArrayList<>();
		String req = "SELECT * FROM orderitems";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			Orderitems orderitems;
			while(rs.next()) {
				orderitems = new Orderitems();
				
				orderitems.setId(rs.getInt("id"));
				orderitems.setIdcustomerorder(rs.getInt(""));
				orderitems.setIdproduct(rs.getInt(""));
				orderitems.setPrice(rs.getBigDecimal("price"));
				orderitems.setQuantity(rs.getInt("quantity"));
				orderitems.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				orderitems.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				orderitems.setIdusers(rs.getInt("idusers"));
				
				listOrderitems.add(orderitems);
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
		
		return listOrderitems;
	}

	@Override
	public List<Orderitems> getAllByCustomerOrder(int idcustomerorder) {
		List<Orderitems> listOrderitems = new ArrayList<>();
		String req = "SELECT * FROM orderitems WHERE idcustomerorder = ? ";
		PreparedStatement pstmt = null;
		
		ResultSet rs = null; 
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, idcustomerorder);
			rs = pstmt.executeQuery();
			
			Orderitems orderitems;
			while(rs.next()) {
				orderitems = new Orderitems();
				
				orderitems.setId(rs.getInt("id"));
				orderitems.setIdcustomerorder(rs.getInt(""));
				orderitems.setIdproduct(rs.getInt(""));
				orderitems.setPrice(rs.getBigDecimal("price"));
				orderitems.setQuantity(rs.getInt("quantity"));
				orderitems.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				orderitems.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				orderitems.setIdusers(rs.getInt("idusers"));
				
				listOrderitems.add(orderitems);
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
		
		return listOrderitems;
	}
}
