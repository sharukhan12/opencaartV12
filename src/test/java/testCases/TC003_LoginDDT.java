package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountpage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	@Test(dataProvider ="LoginData",dataProviderClass = DataProviders.class,groups = "datadriven")
	public void verify_loginDDT(String email,String pwd,String exp)  {
	logger.info("******Starting TC003_LoginDDT******");	
	try { 
	HomePage hp=new HomePage(driver);
	hp.ClickMyAccount();
	hp.clickLogin();
	
	LoginPage lp=new LoginPage(driver);
	lp.SetEmail(email);
    lp.SetPasswrd(pwd);
    lp.ClkLogin();
    
    MyAccountpage myacc=new MyAccountpage(driver);
    boolean targetPage = myacc.isMyAccountPageExist();
    
    if (exp.equalsIgnoreCase("valid")) {
    	if (targetPage==true) {
    		Assert.assertTrue(true);
			myacc.clickLogout();
		}else {
			Assert.assertTrue(false);
		}
	}
   if (exp.equalsIgnoreCase("invalid")) {
	if (targetPage==true) {
		myacc.clickLogout();
		Assert.assertTrue(false);
	}else {
		Assert.assertTrue(true);
	}
	
		
	} 
	} catch (Exception e) {
		Assert.fail();
	}
		
	
       logger.info("******Ending TC003_LoginDDT******");	 
        
        
	}
	

}
