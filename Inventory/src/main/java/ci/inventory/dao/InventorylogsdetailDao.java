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

import ci.inventory.dao.interfaces.IInventorylogsdetailDao;
import ci.inventory.entity.Inventorylogsdetail;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.Logging;

public class InventorylogsdetailDao implements IInventorylogsdetailDao{
	private Connection con = DbConnection.getConnection();
	private Logger logManager = Logging.setLoggerName(InventorylogsdetailDao.class.getName());

	@Override
	public Inventorylogsdetail create(Inventorylogsdetail inventorylogsdetail) {
		String req = "INSERT INTO inventorylogsdetail (description, idstockmovement, idinventorylogs, idorderitem,"
				+ " idstockorderitem, idusers, oldstocklevel, newstocklevel) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, inventorylogsdetail.getDescription());
			pstmt.setInt(2, inventorylogsdetail.getIdstockmovement());
			pstmt.setInt(3, inventorylogsdetail.getIdinventorylogs());
			pstmt.setInt(4, inventorylogsdetail.getIdorderitem());
			pstmt.setInt(5, inventorylogsdetail.getIdstockorderitem());
			pstmt.setInt(6, inventorylogsdetail.getIdusers());
			pstmt.setInt(7, inventorylogsdetail.getOldstocklevel());
			pstmt.setInt(8, inventorylogsdetail.getNewstocklevel());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				inventorylogsdetail.setId(rs.getInt(1));
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
		
		return inventorylogsdetail;
	}

	@Override
	public Inventorylogsdetail getById(int id) {
		String req = "SELECT * FROM inventorylogsdetail";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		Inventorylogsdetail inventorylogsdetail = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				inventorylogsdetail = new Inventorylogsdetail();
				
				inventorylogsdetail.setId(rs.getInt("id"));
				inventorylogsdetail.setIdinventorylogs(rs.getInt("idinventorylogs"));
				inventorylogsdetail.setDescription(rs.getString("description"));
				inventorylogsdetail.setIdorderitem(rs.getInt("idorderitem"));
				inventorylogsdetail.setIdstockmovement(rs.getInt("idstockmovement"));
				inventorylogsdetail.setIdstockorderitem(rs.getInt("idstockorderitem"));
				inventorylogsdetail.setNewstocklevel(rs.getInt("newstocklevel"));
				inventorylogsdetail.setOldstocklevel(rs.getInt("oldstocklevel"));
				inventorylogsdetail.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				inventorylogsdetail.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				inventorylogsdetail.setIdusers(rs.getInt("idusers"));
				
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
		
		return inventorylogsdetail;
	}

	@Override
	public Inventorylogsdetail update(Inventorylogsdetail inventorylogsdetail) {
		String req = "UPDATE inventorylogsdetail (description =?, idstockmovement =?, idinventorylogs =?, idorderitem =?,"
				+ " idstockorderitem = ?, idusers =?, oldstocklevel =?, newstocklevel =? WHERE id = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setString(1, inventorylogsdetail.getDescription());
			pstmt.setInt(2, inventorylogsdetail.getIdstockmovement());
			pstmt.setInt(3, inventorylogsdetail.getIdinventorylogs());
			pstmt.setInt(4, inventorylogsdetail.getIdorderitem());
			pstmt.setInt(5, inventorylogsdetail.getIdstockorderitem());
			pstmt.setInt(6, inventorylogsdetail.getIdusers());
			pstmt.setInt(7, inventorylogsdetail.getOldstocklevel());
			pstmt.setInt(8, inventorylogsdetail.getNewstocklevel());
			pstmt.setInt(9, inventorylogsdetail.getId());
			
			pstmt.executeUpdate();
			
			if(result == 0)
				inventorylogsdetail.setId(0);
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
		
		return inventorylogsdetail;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM inventorylogsdetail WHERE id = ?";
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
	public List<Inventorylogsdetail> getAll() {
		List<Inventorylogsdetail> listInventorylogsdetail = new ArrayList<>();
		String req = "SELECT * FROM inventorylogsdetail";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			Inventorylogsdetail inventorylogsdetail;
			while(rs.next()) {
				inventorylogsdetail = new Inventorylogsdetail();
				
				inventorylogsdetail.setId(rs.getInt("id"));
				inventorylogsdetail.setIdinventorylogs(rs.getInt("idinventorylogs"));
				inventorylogsdetail.setDescription(rs.getString("description"));
				inventorylogsdetail.setIdorderitem(rs.getInt("idorderitem"));
				inventorylogsdetail.setIdstockmovement(rs.getInt("idstockmovement"));
				inventorylogsdetail.setIdstockorderitem(rs.getInt("idstockorderitem"));
				inventorylogsdetail.setNewstocklevel(rs.getInt("newstocklevel"));
				inventorylogsdetail.setOldstocklevel(rs.getInt("oldstocklevel"));
				inventorylogsdetail.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				inventorylogsdetail.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				inventorylogsdetail.setIdusers(rs.getInt("idusers"));
				
				listInventorylogsdetail.add(inventorylogsdetail);
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
		
		return listInventorylogsdetail;
	}
}
