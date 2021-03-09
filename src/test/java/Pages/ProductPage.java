package Pages;

import TestCases.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends Driver {


    public ProductPage(WebDriver driver){
        //super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//button[normalize-space()='Add To Cart']")
    WebElement addToCartButton;
    //button[normalize-space()='Add To Cart']


    public void addProductToCart() throws InterruptedException {
        //waitForElement(addToCartButton);
        //productPage.wait(4000);
        wait.until(ExpectedConditions.presenceOfElementLocated((By) addToCartButton)).click();
        //addToCartButton.click();
    }

    public void open() {

    }
}
