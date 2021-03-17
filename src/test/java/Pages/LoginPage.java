package Pages;

import TestCases.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }



    @FindBy(xpath = "//input[@id='spree_user_email']")
    WebElement userEmail;

    @FindBy(xpath = "//input[@id='spree_user_password']")
    WebElement userPassword;

    @FindBy(xpath = "//input[@name='commit']")
    WebElement loginButton;

    public void clickLoginButton(){
        loginButton.click();
    }

    public void enterUserEmail(String email){
        userEmail.sendKeys(email);
    }

    public void enterPassword(String password){
        userPassword.sendKeys(password);
    }

    public void enterUsernameAndPassword(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
    }

    public void verifyLoginSuccess(){

    }
}
