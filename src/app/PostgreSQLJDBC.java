/**
 * 
 */
package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author reza.lesmana
 *
 */
public class PostgreSQLJDBC {
	
	private static Connection c = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		openConnection();
		createTables();
		closeConnection();
	}

	private static void closeConnection() {
        try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createTables() {
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE LOGISTIC_PROVIDER " +
		            "(ID INT PRIMARY KEY     NOT NULL," +
		            " LOGISTIC_CODE           TEXT    NOT NULL, " +
		            " LOGISTIC_NAME           TEXT     NOT NULL, " +
		            " FEE        REAL, " +
		            " DISCOUNT REAL)";
	         stmt.executeUpdate(sql);
	         stmt.close();
	         System.out.println("Finish creating tables");
		} catch(Exception e) {
			System.out.println("Failure on creating tables : " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void openConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/trainingjdbc", 
					"postgres", 
					"admin");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
}
