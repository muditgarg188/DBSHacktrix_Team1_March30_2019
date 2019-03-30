package testPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnetionFactory {
	private static Connection connection = null;
	private static String HOST = "localhost";
	private static String DB_NAME = "test";
	private static String userName = "username";
	private static String password = "password";
	
	public static Connection getConnection() throws SQLException {
		if(connection == null) {
			String hostUrl = "jdbc:mysql://" + HOST + "/" + DB_NAME + "?user=" + userName + "&password=" + password; 
			//"jdbc:mysql://localhost/test?user=username&password=password"
			connection = DriverManager.getConnection(hostUrl);
		}
		return connection;
	}
}
