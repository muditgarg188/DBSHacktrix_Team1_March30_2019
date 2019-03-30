package testPackage;

public class BankLoanEligibility {
    private final static int CREDIT_CARDS_LIMIT = 4;
    private final static int PERSONAL_LOANS_LIMIT = 1;
    private final static int HOME_LOANS_LIMIT = 1;

    public static boolean isCustomerEligible(String custumerUid) {
        BankAccountDetails accountDetails = BankCustumerData.getAccountDetails(custumerUid);
        return checkEligiblity(accountDetails);
    }

    public static boolean checkEligiblity(BankAccountDetails accountDetails) {
        if (accountDetails == null) {
            return true;
        }

        if (accountDetails.isDefaulter()) {
            return false;
        }

        if (accountDetails.getCreditCards() >= CREDIT_CARDS_LIMIT) {
            return false;
        }

        if (accountDetails.getHomeLoans() >= HOME_LOANS_LIMIT) {
            return false;
        }

        if (accountDetails.getPersonalLoans() >= PERSONAL_LOANS_LIMIT) {
            return false;
        }

        return true;
    }

}
