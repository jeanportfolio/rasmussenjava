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

import ci.inventory.dao.interfaces.IStockorderitemsDao;
import ci.inventory.entity.Stockorderitems;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.LoggingLog4j;

public class StockorderitemsDao implements IStockorderitemsDao{
	private Connection con = DbConnection.getConnection();
	//private Logger logManager = Logging.setLoggerName(StockorderitemsDao.class.getName());
	private static Logger logManager = new LoggingLog4j().getLogger(StockorderitemsDao.class.getName());

	@Override
	public Stockorderitems create(Stockorderitems stockorderitems) {
		String req = "INSERT INTO stockorderitems (idstockorder, idproduct, quantity, price, idusers) VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, stockorderitems.getIdstockorder());
			pstmt.setInt(2, stockorderitems.getIdproduct());
			pstmt.setInt(3, stockorderitems.getQuantity());
			pstmt.setBigDecimal(4, stockorderitems.getPrice());
			pstmt.setInt(5, stockorderitems.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				stockorderitems.setId(rs.getInt(1));
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
		
		return stockorderitems;
	}

	@Override
	public Stockorderitems getById(int id) {
		String req = "SELECT * FROM stockorderitems WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		Stockorderitems stockorderitems = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				stockorderitems = new Stockorderitems();
				
				stockorderitems.setId(rs.getInt("id"));
				stockorderitems.setQuantity(rs.getInt("quantity"));
				stockorderitems.setPrice(rs.getBigDecimal("price"));
				stockorderitems.setIdproduct(rs.getInt("idproduct"));
				stockorderitems.setIdstockorder(rs.getInt("idstockorder"));
				stockorderitems.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				stockorderitems.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				stockorderitems.setIdusers(rs.getInt("idusers"));
				
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
		
		return stockorderitems;
	}

	@Override
	public Stockorderitems update(Stockorderitems stockorderitems) {
		String req = "UPDATE stockorderitems SET idstockorder = ?, idproduct = ?, quantity = ?, price = ?, idusers = ?) WHERE id = ?)";
		PreparedStatement pstmt = null;
		int result;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, stockorderitems.getIdstockorder());
			pstmt.setInt(2, stockorderitems.getIdproduct());
			pstmt.setInt(3, stockorderitems.getQuantity());
			pstmt.setBigDecimal(4, stockorderitems.getPrice());
			pstmt.setInt(5, stockorderitems.getIdusers());
			pstmt.setInt(5, stockorderitems.getId());
			
			result = pstmt.executeUpdate();
			
			if(result == 0)
				stockorderitems.setId(0);
			
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
		
		return stockorderitems;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM stockorderitems WHERE id = ?";
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
	public List<Stockorderitems> getAll() {
		List<Stockorderitems> listStockorderitems = new ArrayList<>();
		String req = "SELECT * FROM stockorderitems";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			Stockorderitems stockorderitems;
			while(rs.next()) {
				stockorderitems = new Stockorderitems();
				
				stockorderitems.setId(rs.getInt("id"));
				stockorderitems.setQuantity(rs.getInt("quantity"));
				stockorderitems.setPrice(rs.getBigDecimal("price"));
				stockorderitems.setIdproduct(rs.getInt("idproduct"));
				stockorderitems.setIdstockorder(rs.getInt("idstockorder"));
				stockorderitems.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				stockorderitems.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				stockorderitems.setIdusers(rs.getInt("idusers"));
				
				listStockorderitems.add(stockorderitems);
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
		
		return listStockorderitems;
	}
	
	@Override
	public List<Stockorderitems> getAllByStockorder(int idstockorder) {
		List<Stockorderitems> listStockorderitems = new ArrayList<>();
		String req = "SELECT * FROM stockorderitems WHERE idstockorder = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, idstockorder);
			rs = pstmt.executeQuery();
			Stockorderitems stockorderitems;
			while(rs.next()) {
				stockorderitems = new Stockorderitems();
				
				stockorderitems.setId(rs.getInt("id"));
				stockorderitems.setQuantity(rs.getInt("quantity"));
				stockorderitems.setPrice(rs.getBigDecimal("price"));
				stockorderitems.setIdproduct(rs.getInt("idproduct"));
				stockorderitems.setIdstockorder(rs.getInt("idstockorder"));
				stockorderitems.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				stockorderitems.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				stockorderitems.setIdusers(rs.getInt("idusers"));
				
				listStockorderitems.add(stockorderitems);
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
		
		return listStockorderitems;
	}
}
