package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
public LoginPage(WebDriver driver) {
	super(driver);
}

@FindBy(xpath="//input[@id='input-email']")
WebElement txtEmail;

@FindBy(xpath="//input[@id='input-password']")
WebElement txtPasswd;

@FindBy(xpath="//input[@value='Login']")
WebElement lnkLogin;


public void SetEmail(String email) {
	txtEmail.sendKeys(email);

}
public void SetPasswrd(String passwrd) {
	txtPasswd.sendKeys(passwrd);

}
public void ClkLogin() {
	lnkLogin.click();

}







}
