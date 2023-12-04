package ci.inventory.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*import java.util.Properties;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.ConnectionFactory;
import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;
import org.apache.tomcat.dbcp.dbcp2.PoolableConnection;
import org.apache.tomcat.dbcp.dbcp2.PoolableConnectionFactory;
import org.apache.tomcat.dbcp.dbcp2.PoolingDataSource;
import org.apache.tomcat.dbcp.pool2.ObjectPool;
import org.apache.tomcat.dbcp.pool2.impl.GenericObjectPool;
import org.apache.tomcat.dbcp.pool2.impl.GenericObjectPoolConfig;
*/

public class DbConnection {
	//private static DataSource datasource;
	private static Connection connection;
	private static String database = "db_inventory";
	private static String url = "jdbc:mysql://localhost:3306/"+ database;
	private static String username = "root";
	private static String password = "root";
	/*static {
		Properties properties = new Properties();
		properties.setProperty("user", username);
		properties.setProperty("password", password);
		
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:mysql://localhost:3306/empdb",
				properties);

		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);

		GenericObjectPoolConfig<PoolableConnection> config = new GenericObjectPoolConfig<>();
		config.setMaxTotal(25);
		config.setMaxIdle(10);
		config.setMinIdle(5);

		ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory, config);
		poolableConnectionFactory.setPool(connectionPool);

		datasource = new PoolingDataSource<>(connectionPool);
	}
	*/
	private DbConnection() {
		
	}
	
	public static Connection getConnection() {
		try {
			if (connection == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(url, username, password);
				
			} else if (connection.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(url, username, password);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println("Something is wrong with the DB connection String : " + ex.getMessage());
		}
		return connection;
	}
	

}
