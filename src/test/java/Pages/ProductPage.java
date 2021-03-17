package Pages;

import TestCases.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {


    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//button[normalize-space()='Add To Cart']")
    WebElement addToCartButton;



    public void addProductToCart() throws InterruptedException {
        addToCartButton.click();
    }


}
