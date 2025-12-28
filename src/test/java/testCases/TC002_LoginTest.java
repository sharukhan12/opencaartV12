package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountpage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups = {"sanity","master"})
	public void verify_login() {
		logger.info("******Starting TC002_LoginTest******** ");
		try {
			 HomePage hp=new HomePage(driver);
	         hp.ClickMyAccount();
	         hp.clickLogin();
	         
	         LoginPage lp=new LoginPage(driver);
	         lp.SetEmail(p.getProperty("email"));
	         lp.SetPasswrd(p.getProperty("password"));
	         lp.ClkLogin();
	         
	         MyAccountpage macc=new MyAccountpage(driver);
	         boolean targetPage = macc.isMyAccountPageExist();
	         Assert.assertTrue(targetPage);
		} catch (Exception e) {
			Assert.fail();
		}
        
        logger.info("*****Finished TC002_LoginTest*******");
        
	}
	
	
}
