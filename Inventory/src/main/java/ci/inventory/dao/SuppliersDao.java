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

import ci.inventory.dao.interfaces.ISuppliersDao;
import ci.inventory.entity.Suppliers;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.Logging;

public class SuppliersDao implements ISuppliersDao{

	private Connection con = DbConnection.getConnection();
	private Logger logManager = Logging.setLoggerName(SuppliersDao.class.getName());
	
	@Override
	public Suppliers create(Suppliers suppliers) {
		String req = "INSERT INTO suppliers (suppliersname, phone, address, idusers) VALUES (?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, suppliers.getSuppliersname());
			pstmt.setString(2, suppliers.getPhone());
			pstmt.setString(3, suppliers.getAddress());
			pstmt.setInt(4, suppliers.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				suppliers.setId(rs.getInt(1));
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
		
		return suppliers;
	}

	@Override
	public Suppliers getById(int id) {
		Suppliers suppliers = null;
		String req = "SELECT * FROM suppliers WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				suppliers = new Suppliers();
				
				suppliers.setId(rs.getInt("id"));
				suppliers.setSuppliersname(rs.getString("suppliersname"));
				suppliers.setPhone(rs.getString("phone"));
				suppliers.setAddress(rs.getString("address"));
				suppliers.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				suppliers.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				suppliers.setIdusers(rs.getInt("idusers"));
				
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
		
		return suppliers;
	}

	@Override
	public Suppliers update(Suppliers suppliers) {
		String req = "UPDATE suppliers SET suppliersname = ?, phone = ?, address = ?, idusers = ? WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, suppliers.getSuppliersname());
			pstmt.setString(2, suppliers.getPhone());
			pstmt.setString(3, suppliers.getAddress());
			pstmt.setInt(4, suppliers.getIdusers());
			pstmt.setInt(5, suppliers.getId());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				suppliers.setId(rs.getInt(1));
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
		
		return suppliers;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM suppliers WHERE id = ?";
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
	public List<Suppliers> getAll() {
		
		Suppliers suppliers = null;
		List<Suppliers> listSuppliers = new ArrayList<>();
		String req = "SELECT * FROM suppliers";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				suppliers = new Suppliers();
				
				suppliers.setId(rs.getInt("id"));
				suppliers.setSuppliersname(rs.getString("suppliersname"));
				suppliers.setPhone(rs.getString("phone"));
				suppliers.setAddress(rs.getString("address"));
				suppliers.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				suppliers.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				suppliers.setIdusers(rs.getInt("idusers"));
				
				listSuppliers.add(suppliers);
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
		
		return listSuppliers;
	}
	
}
