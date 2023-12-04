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

import ci.inventory.dao.interfaces.IStockorderDao;
import ci.inventory.entity.Stockorder;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.Logging;

public class StockorderDao implements IStockorderDao{
	private Connection con = DbConnection.getConnection();
	private Logger logManager = Logging.setLoggerName(StockorderDao.class.getName());

	@Override
	public Stockorder create(Stockorder stockorder) {
		String req = "INSERT INTO stockorder (totalamount, idsuppliers, idusers) VALUES (?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setBigDecimal(1, stockorder.getTotalamount());
			pstmt.setInt(2, stockorder.getIdsuppliers());
			pstmt.setInt(3, stockorder.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				stockorder.setId(rs.getInt(1));
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
		
		return stockorder;
	}

	@Override
	public Stockorder getById(int id) {
		String req = "SELECT * FROM stockorder WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		Stockorder stockorder = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				stockorder = new Stockorder();
				
				stockorder.setId(rs.getInt("id"));
				stockorder.setTotalamount(rs.getBigDecimal("totalamount"));
				stockorder.setIdsuppliers(rs.getInt("idsuppliers"));
				stockorder.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				stockorder.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				stockorder.setIdusers(rs.getInt("idusers"));
				
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
		
		return stockorder;
	}

	@Override
	public Stockorder update(Stockorder stockorder) {
		String req = "UPDATE stockorder SET totalamount = ?, idsuppliers=?, idusers=?) WHERE id = ?";
		PreparedStatement pstmt = null;
		int result= 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setBigDecimal(1, stockorder.getTotalamount());
			pstmt.setInt(2, stockorder.getIdsuppliers());
			pstmt.setInt(3, stockorder.getIdusers());
			pstmt.setInt(4, stockorder.getId());
			
			pstmt.executeUpdate();
			if(result == 0)
				stockorder.setId(0);
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
		
		return stockorder;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM stockorder WHERE id = ?";
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
	public List<Stockorder> getAll() {
		List<Stockorder> listStockorder = new ArrayList<>();
		String req = "SELECT * FROM stockorder";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			Stockorder stockorder;
			while(rs.next()) {
				stockorder = new Stockorder();
				
				stockorder.setId(rs.getInt("id"));
				stockorder.setTotalamount(rs.getBigDecimal("totalamount"));
				stockorder.setIdsuppliers(rs.getInt("idsuppliers"));
				stockorder.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				stockorder.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				stockorder.setIdusers(rs.getInt("idusers"));
				
				listStockorder.add(stockorder);
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
		
		return listStockorder;
	}
}
