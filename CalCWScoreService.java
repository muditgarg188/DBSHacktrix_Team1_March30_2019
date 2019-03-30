package testPackage;

import java.sql.SQLException;

public class CalCWScoreService {
	private static CalCWScoreService service;

	private CalCWScoreService() {

	}

	public static CalCWScoreService getInstance() {
		if (service == null) {
			service = new CalCWScoreService();
		}
		return service;
	}

	public static CustomerValue getCustomerInfo(String mobile, String emailId, String pass) throws SQLException {
		return CalCWScoreDAO.getCustomerInfo(mobile, emailId, pass);
	}

	public static boolean isEligible(String typeOfLoan, int amount, CustomerValue cust) {
		if (cust.getPanNum() == null) {
			return false;
		}
		CreditWorthinessScoreValue val = CalCWScoreDAO.getCWScore(cust);
		double rideScore = CalCWScoreService.calRideCWScore(val);
		double foodScore = CalCWScoreService.calFoodCWScore(val);
		double medScore = CalCWScoreService.calMedCWScore(val);
		return checkLoanEligibility(rideScore, foodScore, medScore, typeOfLoan, amount);
	}

	private static boolean checkLoanEligibility(double rideScore, double foodScore, double medScore, String typeOfLoan,
			int amount) {
		if (rideScore != 0 && foodScore != 0 && medScore != 0) {
			double total = (2 * rideScore + foodScore + medScore) / 4.0;
			double eligibleScore = CalCWScoreService.getEligibleCWScore(typeOfLoan, amount);
			if (total >= eligibleScore) {
				return true;
			} else {
				return false;
			}
		} else if (rideScore == 0 && (foodScore != 0 || medScore != 0)) {
			double den = 2;
			if (foodScore == 0 || medScore == 0) {
				den = 1;
			}
			double total = (foodScore + medScore) / den;
			double eligibleScore = CalCWScoreService.getEligibleCWScore(typeOfLoan, amount);
			if (total >= eligibleScore) {
				return true;
			} else {
				return false;
			}
		} else if (rideScore != 0 && foodScore == 0 && medScore == 0) {
			double total = rideScore;
			double eligibleScore = CalCWScoreService.getEligibleCWScore(typeOfLoan, amount);
			if (total >= eligibleScore) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	private static double getEligibleCWScore(String typeOfLoan, int amount) {
		double minCW = 0;
		if ("home".equalsIgnoreCase(typeOfLoan)) {
			if (amount <= 150000) {
				minCW = 6;
			} else if (amount > 150000 && amount <= 500000) {
				minCW = 7;
			} else {
				minCW = 8;
			}
		} else if ("education".equalsIgnoreCase(typeOfLoan)) {
			if (amount <= 100000) {
				minCW = 6;
			} else if (amount > 100000 && amount <= 200000) {
				minCW = 7;
			} else {
				minCW = 8;
			}
		} else {
			if (amount <= 50000) {
				minCW = 6;
			} else if (amount > 50000 && amount <= 100000) {
				minCW = 7;
			} else {
				minCW = 8;
			}
		}
		return minCW;
	}

	private static double calRideCWScore(CreditWorthinessScoreValue val) {
		return val.getRideRating();
	}

	private static double calFoodCWScore(CreditWorthinessScoreValue val) {
		return val.getFoodRating();
	}

	private static double calMedCWScore(CreditWorthinessScoreValue val) {
		return val.getMedRating();
	}
	
	public static int getLoanStatus(int custId){
		return CalCWScoreDAO.getLoanData(custId);
	}
}
