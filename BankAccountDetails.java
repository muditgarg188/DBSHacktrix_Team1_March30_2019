package testPackage;

public class BankAccountDetails {
	private int creditCards;
	private int personalLoans;
	private int homeLoans;
	private boolean isDefaulter;
	
	public BankAccountDetails( ) {
		creditCards = 0;
		personalLoans = 0;
		homeLoans = 0;
		isDefaulter = false;
	}
	
	public int getCreditCards() {
		return creditCards;
	}
	public void setCreditCards(int creditCards) {
		this.creditCards = creditCards;
	}
	public int getPersonalLoans() {
		return personalLoans;
	}
	public void setPersonalLoans(int personalLoans) {
		this.personalLoans = personalLoans;
	}
	public int getHomeLoans() {
		return homeLoans;
	}
	public void setHomeLoans(int homeLoans) {
		this.homeLoans = homeLoans;
	}
	public boolean isDefaulter() {
		return isDefaulter;
	}
	public void setDefaulter(boolean isDefaulter) {
		this.isDefaulter = isDefaulter;
	}
	
	
}
