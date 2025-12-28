package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
@FindBy(xpath="//input[@id='input-firstname']")
WebElement txtFirstname;
@FindBy(xpath="//input[@id='input-lastname']")
WebElement txtLastname;
@FindBy(xpath="//input[@id='input-email']")
WebElement txtEmail;
@FindBy(xpath="//input[@id='input-telephone']")
WebElement txtTele;
@FindBy(xpath="//input[@id='input-password']")
WebElement txtPass;
@FindBy(xpath="//input[@id='input-confirm']")
WebElement txtCnfrmPass;
@FindBy(xpath="//input[@name='agree']")
WebElement chkPolicy;
@FindBy(xpath="//input[@value='Continue']")
WebElement btnCntnue;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgCnfrmation;

public void SetFirstName(String first) {
	txtFirstname.sendKeys(first);
}
public void SetLastName(String last) {
	txtLastname.sendKeys(last);
}
public void SetEmail(String email) {
	txtEmail.sendKeys(email);

}
public void SetPass(String Passwrd) {
	txtPass.sendKeys(Passwrd);

}
public void SetCnfmPass(String cnfrmPasswrd) {
	txtCnfrmPass.sendKeys(cnfrmPasswrd);

}
public void SetTelePhno(String Telephno) {
	txtTele.sendKeys(Telephno);;

}
public void ClickPolicy() {
	chkPolicy.click();

}
public void clickContinue() {
	btnCntnue.click();

}

public String getCnfrmationMsg() {
	
try {
	return(msgCnfrmation.getText());
} catch (Exception e) {
	return(e.getMessage());
}


}









	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
