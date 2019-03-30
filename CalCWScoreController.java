package testPackage;

import java.sql.SQLException;

public class CalCWScoreController {
    public static CalCWScoreService service = CalCWScoreService.getInstance(); 
    
    public void isRideEligible() throws SQLException{
        String mobile = "121211";
        String emailId = "tabish@gmail.com";
        String pass = "12345";
        String typeOfLoan = "home";
        int amount = 1213;
        CustomerValue val = CalCWScoreService.getCustomerInfo(mobile, emailId, pass);
        if(service.isEligible(typeOfLoan, amount, val)){
            
        } else {
            
        }
    }
    
    public void isBankEligible() throws SQLException {
        String mobile = "32322121";
        String emailId = "tabish@gmail.com";
        String pass = "12345";
        String typeOfLoan = "home";
        int amount = 1213;
        CustomerValue val = CalCWScoreService.getCustomerInfo(mobile, emailId, pass);
        if(BankLoanEligibility.isCustomerEligible(val.getPanNum())){
            
        } else {
            
        }
    }
    
    public void getStatus() throws SQLException{
        String mobile = "121211";
        String emailId = "tabish@gmail.com";
        String pass = "12345";
        String typeOfLoan = "home";
        int amount = 1213;
        CustomerValue val = CalCWScoreService.getCustomerInfo(mobile, emailId, pass);
        int res = CalCWScoreService.getLoanStatus(val.getCustId());
        if(res == 1){
            
        } else if(res == 2){
            
        }
    }
}
