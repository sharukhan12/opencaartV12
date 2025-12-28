package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
		
	@Test(groups = {"regression","master"})
public void verify_account_regisrtation() {
		logger.info("******Starting TC001_AccountRegistrationTest*******");
		
	try {
		HomePage hp=new HomePage(driver);
		logger.info("clicked on My account");
		hp.ClickMyAccount();
		logger.info("clicked on register");
		hp.ClickRegister();
		
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		regpage.SetFirstName(randomString().toUpperCase());
		regpage.SetLastName(randomString().toUpperCase());
		regpage.SetEmail(randomString()+"@gmail.com");
		regpage.SetTelePhno(randomNumber());
		
		String password = randomAlphaNumeric();
		regpage.SetPass(password);
		regpage.SetCnfmPass(password);
		regpage.ClickPolicy();
		regpage.clickContinue();
		logger.info("validating expected message");
		String cnfrmMsg = regpage.getCnfrmationMsg();
		if (cnfrmMsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		} else {
	         logger.error("Test Failed");
	         logger.debug("debug logs");
	         Assert.assertTrue(false);
		}
		}catch (Exception e) {
		Assert.fail();
	}
	
	
	//Assert.assertEquals(cnfrmMsg, "Your Account Has Been Created!");
	logger.info("******finished TC001_AccountRegistrationTest*****");
		
}
}
