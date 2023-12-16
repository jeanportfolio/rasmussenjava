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

import ci.inventory.dao.interfaces.IProductsDao;
import ci.inventory.entity.Products;
import ci.inventory.entity.Stockinventory;
import ci.inventory.utility.DbConnection;
import ci.inventory.utility.log.LoggingLog4j;

/**
 * {@summary This class is for managing the products operations} 
 *
 */
public class ProductsDao implements IProductsDao{
	private Connection con = DbConnection.getConnection();
	//private static Logger logManager = Logging.setLoggerName(ProductsDao.class.getName());
	private static Logger logManager = new LoggingLog4j().getLogger(UsersDao.class.getName());

	//Method to create an occurrence of a Product
	@Override
	public Products create(Products products, Stockinventory stockinventory) {
		String req = "INSERT INTO products (designation, description, price, saleprice, idcategory, idusers) VALUES (?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String req1 = "INSERT INTO stockinventory (idproduct, availablequantity, title, minstocklevel, maxstocklevel, iduser) "
				+ "VALUES (?,?,?,?,?,?)";
		PreparedStatement pstmt1 = null;
			
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, products.getDesignation());
			pstmt.setString(2, products.getDescription());
			pstmt.setBigDecimal(3, products.getPrice());
			pstmt.setBigDecimal(4, products.getSaleprice());
			pstmt.setInt(5, products.getIdcategory());
			pstmt.setInt(6, products.getIdusers());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				products.setId(rs.getInt(1));
			}
			//Create a Stock inventory every time we are creating a new product
			pstmt1 = con.prepareStatement(req1);
			pstmt1.setInt(1, products.getId());
			pstmt1.setInt(2, stockinventory.getAvailablequantity());
			pstmt1.setString(3, stockinventory.getTitle());
			pstmt1.setInt(4, stockinventory.getMinstocklevel());
			pstmt1.setInt(5, stockinventory.getMaxstocklevel());
			pstmt1.setInt(6, stockinventory.getIduser());
			
			pstmt1.executeUpdate();
			
			con.commit();
			logManager.log(Level.INFO, "Products created");
		} catch (SQLException e) {
			System.err.println("Error "+ e.getMessage());
			logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			return null;
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e) {
				System.err.println("Error "+ e.getMessage());
				logManager.log(Level.ERROR, e.getMessage(), e.getClass());
			}
		}
		
		return products;
	}

	//Method to get an occurrence of a Product
	@Override
	public Products getById(int id) {
		String req = "SELECT * FROM products WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		Products products = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				products = new Products();
				
				products.setId(rs.getInt("id"));
				products.setDescription(rs.getString("description"));
				products.setDesignation(rs.getString("designation"));
				products.setPrice(rs.getBigDecimal("price"));
				products.setSaleprice(rs.getBigDecimal("saleprice"));
				products.setIdcategory(rs.getInt("idcategory"));
				products.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				products.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				products.setIdusers(rs.getInt("idusers"));
				
			}
			con.commit();
			logManager.log(Level.INFO, "Products with id "+ id + " retrieved");
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
		
		return products;
	}

	//Method to update an occurrence of a Product
	@Override
	public Products update(Products products) {
		String req = "UPDATE products SET designation = ?, description = ?, price = ?, saleprice = ?, idcategory = ?,"
				+ "idusers =? WHERE id = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setString(1, products.getDesignation());
			pstmt.setString(2, products.getDescription());
			pstmt.setBigDecimal(3, products.getPrice());
			pstmt.setBigDecimal(4, products.getSaleprice());
			pstmt.setInt(5, products.getIdcategory());
			pstmt.setInt(6, products.getIdusers());
			pstmt.setInt(7, products.getId());
			
			pstmt.executeUpdate();
			
			if(result == 0)
				products.setId(0);
			con.commit();
			logManager.log(Level.INFO, "Products saved");
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
		
		return products;
	}

	//Method to delete an occurrence of a Product
	@Override
	public int delete(int id) {
		String req = "DELETE FROM products WHERE id = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			pstmt.setInt(1, id);
			
			result = pstmt.executeUpdate();
			
			con.commit();
			logManager.log(Level.INFO, "Products with id "+ id + " deleted");
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

	//Method to get all occurrence of a Product
	@Override
	public List<Products> getAll() {
		List<Products> listProducts = new ArrayList<>();
		String req = "SELECT * FROM products";
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(req);
			
			rs = pstmt.executeQuery();
			Products products;
			while(rs.next()) {
				products = new Products();
				
				products.setId(rs.getInt("id"));
				products.setDescription(rs.getString("description"));
				products.setDesignation(rs.getString("designation"));
				products.setPrice(rs.getBigDecimal("price"));
				products.setSaleprice(rs.getBigDecimal("saleprice"));
				products.setIdcategory(rs.getInt("idcategory"));
				products.setCreatedate(rs.getTimestamp("createdate").toLocalDateTime());
				products.setModifydate(rs.getTimestamp("modifydate").toLocalDateTime());
				products.setIdusers(rs.getInt("idusers"));
				
				listProducts.add(products);
			}
			con.commit();
			logManager.log(Level.INFO, "Products list retrieved");
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
		
		return listProducts;
	}
}
