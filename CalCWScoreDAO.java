package testPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalCWScoreDAO {

	public static Connection conObject;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hacktrix", "root", "PASSWORD");
			conObject = con;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*private static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hacktrix", "root", "PASSWORD");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/

	public static CustomerValue getCustomerInfo(String mobile, String email, String pass) throws SQLException{
		CustomerValue val = new CustomerValue();
		System.out.println(conObject);
		Connection con = conObject;
		try {
			String sql = "select * from customers where mobile = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mobile);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				val.setCustId(rs.getInt(1));
				val.setFullName(rs.getString(2));
				val.setMobile(rs.getString(3));
				val.setEmailId(rs.getString(4));
				val.setPanNum(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return val;
	}

	public static CreditWorthinessScoreValue getCWScore(CustomerValue cust) {
		CreditWorthinessScoreValue val = new CreditWorthinessScoreValue();
		Connection con = conObject;
		try {
			String sql = "select a.ridecount, a.rating, b.foodcount, b.rating, c.medscount, c.medsrating from rides a, foodorders b, medsorders c where "+
					"a.customerid = b.customerid and b.customerid = c.customerid and c.customerid = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cust.getCustId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				val.setRidesNum(rs.getInt(1));
				val.setRideRating(rs.getDouble(2));
				val.setFoodNum(rs.getInt(3));
				val.setFoodRating(rs.getDouble(4));
				val.setMedNum(rs.getInt(5));
				val.setMedRating(rs.getDouble(6));
			}
			return val;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static int getLoanData(int customerId) {
		Connection con = conObject;
		try {
			String sql = "select loanstatus from loans where customerid = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
}
