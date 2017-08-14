/**
 * 
 */
package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
		insertDataToTables();
		showAllLogisticProvider();
		closeConnection();
	}

	private static void showAllLogisticProvider() {
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM LOGISTIC_PROVIDER;" );
	         while ( rs.next() ) {
	            int id = rs.getInt("id");
	            String  code = rs.getString("logistic_code");
	            String name  = rs.getString("logistic_name");
	            float fee = rs.getFloat("fee");
	            float discount = rs.getFloat("discount");
	            System.out.println( "ID = " + id );
	            System.out.println( "CODE = " + code );
	            System.out.println( "NAME = " + name );
	            System.out.println( "FEE = " + fee );
	            System.out.println( "discount = " + discount );
	            System.out.println();
	         }
	         rs.close();
	         stmt.close();
		} catch(Exception e) {
			System.out.println("Failure on selecting data from tables : " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void insertDataToTables() {
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			String sql = "INSERT INTO LOGISTIC_PROVIDER " +
		            "(ID, LOGISTIC_CODE, LOGISTIC_NAME, FEE, DISCOUNT) "
		            + "VALUES (1, 'JNE', 'PT. Tiki Jalur Nugraha Ekakurir', 15000, 0.02)";
			String sql2 = "INSERT INTO LOGISTIC_PROVIDER " +
		            "(ID, LOGISTIC_CODE, LOGISTIC_NAME, FEE, DISCOUNT) "
		            + "VALUES (2, 'POS', 'PT. POS INDONESIA', 13500, 0.015)";
	         stmt.executeUpdate(sql);
	         stmt.executeUpdate(sql2);
	         stmt.close();
	         System.out.println("Finish inserting data to tables");
		} catch(Exception e) {
			System.out.println("Failure on inserting data to tables : " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void closeConnection() {
        try {
			c.close();
			System.out.println("Close db connection successfully");
		} catch (SQLException e) {
			System.out.println("Failure on closing db connection : " + e.getMessage());
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
