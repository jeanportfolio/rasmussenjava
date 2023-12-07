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

import ci.inventory.dao.interfaces.ICategoryproductDao;
import ci.inventory.entity.Categoryproduct;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.LoggingLog4j;

public class CategoryproductDao implements ICategoryproductDao{
	private Connection con = DbConnection.getConnection();
	//private Logger logManager = Logging.setLoggerName(CategoryproductDao.class.getName());
	private static Logger logManager = new LoggingLog4j().getLogger(UsersDao.class.getName());

	/**
	 * Create CategoryProduct
	 * @param Categoryproduct category of product
     * @return CategoryProduct created or null
	 */
	@Override
	public Categoryproduct create(Categoryproduct category) {
		String req = "INSERT INTO categoryproduct (title, description, idusers) VALUES (?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, category.getTitle());
			pstmt.setString(2, category.getDescription());
			pstmt.setInt(3, category.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				category.setId(rs.getInt(1));
			}
			con.commit();
			logManager.log(Level.INFO, "Category product created");
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
		
		return category;
	}

	/**
	 * Get CategoryProduct by id
	 * @param id identifier category of product
     * @return CategoryProduct created or null
	 */
	@Override
	public Categoryproduct getById(int id) {
		Categoryproduct category = null;
		String req = "SELECT * FROM categoryproduct WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				category = new Categoryproduct();
				
				category.setId(rs.getInt("id"));
				category.setTitle(rs.getString("title"));
				category.setDescription(rs.getString("description"));
				category.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				category.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				category.setIdusers(rs.getInt("idusers"));
			}
			con.commit();
			logManager.log(Level.INFO, "Category product with id "+ id + " retrieved");
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
		
		return category;
	}

	@Override
	public Categoryproduct update(Categoryproduct category) {
		String req = "UPDATE categoryproduct SET title = ?, description = ?, idusers = ? WHERE id = ?";
		PreparedStatement pstmt = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setString(1, category.getTitle());
			pstmt.setString(2, category.getDescription());
			pstmt.setInt(3, category.getIdusers());
			pstmt.setInt(4, category.getId());
			
			int result = pstmt.executeUpdate();
			if(result == 0)
				category.setId(0);
			con.commit();
			logManager.log(Level.INFO, "Category product saved");
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
		
		return category;
	}

	@Override
	public int delete(int id) {
		String req = "DELETE FROM categoryproduct WHERE id = ?";
		PreparedStatement pstmt = null;
		int result;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, id);
			
			result = pstmt.executeUpdate();
			
			con.commit();
			logManager.log(Level.INFO, "Category product with id "+ id + " deleted");
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
	public List<Categoryproduct> getAll() {
		Categoryproduct category = null;
		List<Categoryproduct> listRole = new ArrayList<>();
		String req = "SELECT * FROM categoryproduct";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				category = new Categoryproduct();
				
				category.setId(rs.getInt("id"));
				category.setTitle(rs.getString("title"));
				category.setDescription(rs.getString("description"));
				category.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				category.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				category.setIdusers(rs.getInt("idusers"));
				
				listRole.add(category);
			}
			con.commit();
			logManager.log(Level.INFO, "Category product list retrieved");
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
		
		return listRole;
	}
}
