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

import ci.inventory.dao.interfaces.IStockinventoryDao;
import ci.inventory.entity.Stockinventory;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.LoggingLog4j;

/**
 * {@summary This class is for managing the stock of products movements} 
 *
 */
public class StockinventoryDao implements IStockinventoryDao{
	private Connection con = DbConnection.getConnection();
	private static Logger logManager = new LoggingLog4j().getLogger(StockinventoryDao.class.getName());

	//Method to create an occurrence of a stock
	@Override
	public Stockinventory create(Stockinventory stockinventory) {
		String req = "INSERT INTO stockinventory (idproduct, availablequantity, title, minstocklevel, maxstocklevel, iduser) "
				+ "VALUES (?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//Disable the automatic validation of the SQL transaction 
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, stockinventory.getIdproduct());
			pstmt.setInt(2, stockinventory.getAvailablequantity());
			pstmt.setString(3, stockinventory.getTitle());
			pstmt.setInt(4, stockinventory.getMinstocklevel());
			pstmt.setInt(5, stockinventory.getMaxstocklevel());
			pstmt.setInt(6, stockinventory.getIduser());
			
			pstmt.executeUpdate();
			//Get the id of the newly created entity
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				stockinventory.setId(rs.getInt(1));
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
		
		return stockinventory;
	}

	//Method to get an occurrence of a stock
	@Override
	public Stockinventory getById(int id) {
		String req = "SELECT * FROM stockinventory WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		Stockinventory stockinventory = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				stockinventory = new Stockinventory();
				
				stockinventory.setId(rs.getInt("id"));
				stockinventory.setAvailablequantity(rs.getInt("availablequantity"));
				stockinventory.setTitle(rs.getString("title"));
				stockinventory.setMaxstocklevel(rs.getInt("maxstocklevel"));
				stockinventory.setMinstocklevel(rs.getInt("minstocklevel"));
				stockinventory.setIdproduct(rs.getInt("idproduct"));
				stockinventory.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				stockinventory.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				stockinventory.setIduser(rs.getInt("iduser"));
				
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
		
		return stockinventory;
	}

	//Method to update an occurrence of a stock
	@Override
	public Stockinventory update(Stockinventory stockinventory) {
		String req = "UPDATE stockinventory SET idproduct = ?, availablequantity = ?, title = ?, minstocklevel =?, maxstocklevel =?, "
				+ "iduser =? WHERE id = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, stockinventory.getIdproduct());
			pstmt.setInt(2, stockinventory.getAvailablequantity());
			pstmt.setString(3, stockinventory.getTitle());
			pstmt.setInt(4, stockinventory.getMinstocklevel());
			pstmt.setInt(5, stockinventory.getMaxstocklevel());
			pstmt.setInt(6, stockinventory.getIduser());
			pstmt.setInt(7, stockinventory.getId());
			
			pstmt.executeUpdate();
			
			if(result == 0)
				stockinventory.setId(0);
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
		
		return stockinventory;
	}

	//Method to delete an occurrence of a stock
	@Override
	public int delete(int id) {
		String req = "DELETE FROM stockinventory WHERE id = ?";
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

	//Method to read all occurrences of a stock
	@Override
	public List<Stockinventory> getAll() {
		List<Stockinventory> listStockinventory = new ArrayList<>();
		String req = "SELECT * FROM stockinventory";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			Stockinventory stockinventory;
			while(rs.next()) {
				stockinventory = new Stockinventory();
				
				stockinventory.setId(rs.getInt("id"));
				stockinventory.setAvailablequantity(rs.getInt("availablequantity"));
				stockinventory.setTitle(rs.getString("title"));
				stockinventory.setMaxstocklevel(rs.getInt("maxstocklevel"));
				stockinventory.setMinstocklevel(rs.getInt("minstocklevel"));
				stockinventory.setIdproduct(rs.getInt("idproduct"));
				stockinventory.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				stockinventory.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				stockinventory.setIduser(rs.getInt("iduser"));
				
				listStockinventory.add(stockinventory);
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
		
		return listStockinventory;
	}
}
