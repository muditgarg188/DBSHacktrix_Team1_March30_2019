package testPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankCustumerData {

	@SuppressWarnings("finally")
	public static BankAccountDetails getAccountDetails(String custumerUid) {

		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		BankAccountDetails accountDetails = new BankAccountDetails();

		try {
			conn = DbConnetionFactory.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT ACCOUNT_TYPE, STATUS FROM LOAN_DETIALS WHERE CUSTUMER_ID = " + custumerUid);

			while (resultSet.next()) {
				String accountType = resultSet.getString(0);
				String status = resultSet.getString(1);
				if ("DEFAULT".equalsIgnoreCase(status)) {
					accountDetails.setDefaulter(true);
					break;
				}
				if ("OPEN".equalsIgnoreCase(status)) {
					if ("CC".equalsIgnoreCase(accountType)) {
						accountDetails.setCreditCards(accountDetails.getCreditCards() + 1);
					} else if ("PL".equalsIgnoreCase(accountType)) {
						accountDetails.setPersonalLoans(accountDetails.getPersonalLoans() + 1);
					} else if ("HL".equalsIgnoreCase(accountType)) {
						accountDetails.setHomeLoans(accountDetails.getHomeLoans() + 1);
					}
				}
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			return accountDetails;
		}
	}

}
