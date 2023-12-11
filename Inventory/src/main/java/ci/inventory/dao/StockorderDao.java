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

import ci.inventory.dao.interfaces.IStockorderDao;
import ci.inventory.entity.Stockorder;
import ci.inventory.entity.Stockorderitems;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.LoggingLog4j;

public class StockorderDao implements IStockorderDao{
	private Connection con = DbConnection.getConnection();
	//private Logger logManager = Logging.setLoggerName(StockorderDao.class.getName());
	private static Logger logManager = new LoggingLog4j().getLogger(StockorderDao.class.getName());

	@Override
	public Stockorder create(Stockorder stockorder) {
		String req = "INSERT INTO stockorder (totalamount, idsuppliers, stockordernumber, idusers) VALUES (?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setBigDecimal(1, stockorder.getTotalamount());
			pstmt.setInt(2, stockorder.getIdsuppliers());
			pstmt.setString(3, stockorder.getStockordernumber());
			pstmt.setInt(4, stockorder.getIdusers());
			
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
		
		return stockorder;
	}
	
	@Override
	public Stockorder create(Stockorder stockorder, List<Stockorderitems> liststockorderitem) {
		String req = "INSERT INTO stockorder (totalamount, idsuppliers, stockordernumber, idusers) VALUES (?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setBigDecimal(1, stockorder.getTotalamount());
			pstmt.setInt(2, stockorder.getIdsuppliers());
			pstmt.setString(3, stockorder.getStockordernumber());
			pstmt.setInt(4, stockorder.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				stockorder.setId(rs.getInt(1));
			}
			
			String req2;
			PreparedStatement pstmt2 = null;
			for(int i = 0; i < liststockorderitem.size(); i++) {
				Stockorderitems stockorderitems = liststockorderitem.get(i);
				if(stockorderitems.getId() <= 0)
                {
					req2 = "INSERT INTO stockorderitems (idstockorder, idproduct, quantity, price, idusers) VALUES (?,?,?,?,?)";
					pstmt2 = con.prepareStatement(req2);
					pstmt2.setInt(1, stockorder.getId());
					pstmt2.setInt(2, stockorderitems.getIdproduct());
					pstmt2.setInt(3, stockorderitems.getQuantity());
					pstmt2.setBigDecimal(4, stockorderitems.getPrice());
					pstmt2.setInt(5, stockorderitems.getIdusers());
					
					pstmt2.executeUpdate();
                }else{
                	req2 = "UPDATE stockorderitems SET idstockorder = ?, idproduct = ?, quantity = ?, price = ?, idusers = ? WHERE id = ?";
            		
                	pstmt2 = con.prepareStatement(req2);
                	pstmt2.setInt(1, stockorderitems.getIdstockorder());
                	pstmt2.setInt(2, stockorderitems.getIdproduct());
                	pstmt2.setInt(3, stockorderitems.getQuantity());
                	pstmt2.setBigDecimal(4, stockorderitems.getPrice());
                	pstmt2.setInt(5, stockorderitems.getIdusers());
                	pstmt2.setInt(6, stockorderitems.getId());
            			
            		pstmt2.executeUpdate();
				}
			}
			
			con.commit();
			System.out.println("Stock order save successfully");
			logManager.log(Level.INFO, "Stock order save successfully");
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
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				stockorder = new Stockorder();
				
				stockorder.setId(rs.getInt("id"));
				stockorder.setTotalamount(rs.getBigDecimal("totalamount"));
				stockorder.setStockordernumber(rs.getString("stockordernumber"));
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
		
		return stockorder;
	}
	@Override
	public Stockorder update(Stockorder stockorder) {
		String req = "UPDATE stockorder SET totalamount = ?, idsuppliers=?, stockordernumber=?, idusers=? WHERE id = ?";
		PreparedStatement pstmt = null;
		int result= 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setBigDecimal(1, stockorder.getTotalamount());
			pstmt.setInt(2, stockorder.getIdsuppliers());
			pstmt.setString(3, stockorder.getStockordernumber());
			pstmt.setInt(4, stockorder.getIdusers());
			pstmt.setInt(5, stockorder.getId());
			
			pstmt.executeUpdate();
			if(result == 0)
				stockorder.setId(0);
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
		
		return stockorder;
	}
	@Override
	public Stockorder update(Stockorder stockorder, List<Stockorderitems> liststockorderitem) {
		String req = "UPDATE stockorder SET totalamount = ?, idsuppliers=?, stockordernumber=?, idusers=? WHERE id = ?";
		PreparedStatement pstmt = null;
		int result= 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setBigDecimal(1, stockorder.getTotalamount());
			pstmt.setInt(2, stockorder.getIdsuppliers());
			pstmt.setString(3, stockorder.getStockordernumber());
			pstmt.setInt(4, stockorder.getIdusers());
			pstmt.setInt(5, stockorder.getId());
			
			pstmt.executeUpdate();
			if(result == 0) {
				stockorder.setId(0);
				throw new Exception("Stock Order inexistant");
			}
			String req2;
			PreparedStatement pstmt2 = null;
			for(int i = 0; i < liststockorderitem.size(); i++) {
				Stockorderitems stockorderitems = liststockorderitem.get(i);
				if(stockorderitems.getId() <= 0)
                {
					req2 = "INSERT INTO stockorderitems (idstockorder, idproduct, quantity, price, idusers) VALUES (?,?,?,?,?)";
					pstmt2 = con.prepareStatement(req2);
					pstmt2.setInt(1, stockorder.getId());
					pstmt2.setInt(2, stockorderitems.getIdproduct());
					pstmt2.setInt(3, stockorderitems.getQuantity());
					pstmt2.setBigDecimal(4, stockorderitems.getPrice());
					pstmt2.setInt(5, stockorderitems.getIdusers());
					
					pstmt2.executeUpdate();
                }else{
                	req2 = "UPDATE stockorderitems SET idstockorder = ?, idproduct = ?, quantity = ?, price = ?, idusers = ? WHERE id = ?";
            		
                	pstmt2 = con.prepareStatement(req2);
                	pstmt2.setInt(1, stockorder.getId());
                	pstmt2.setInt(2, stockorderitems.getIdproduct());
                	pstmt2.setInt(3, stockorderitems.getQuantity());
                	pstmt2.setBigDecimal(4, stockorderitems.getPrice());
                	pstmt2.setInt(5, stockorderitems.getIdusers());
                	pstmt2.setInt(5, stockorderitems.getId());
            			
            		pstmt2.executeUpdate();
				}
			}
			
			con.commit();
			System.out.println("Stock order save successfully");
			logManager.log(Level.INFO, "Stock order save successfully");
		} catch (Exception e) {
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
				stockorder.setStockordernumber(rs.getString("stockordernumber"));
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
		
		return listStockorder;
	}
}
