package Pages;

import TestCases.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends Driver {


    public CheckoutPage(WebDriver driver) {
        //super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//td[@class='lead text-primary cart-item-price']")
    WebElement price;

    @FindBy(xpath = "//input[@id='order_line_items_attributes_0_quantity']")
    WebElement quantity;

    @FindBy(xpath = " //td[@class='lead']")
    WebElement total;

    public Boolean verifyTotal() {
        Boolean f1 = false;
        String priceString = price.getText().replace("$", "");
        Double priceValue = Double.parseDouble(priceString);
        String quantityString = quantity.getText();
        Double quantityValue = Double.parseDouble(quantityString);
        Double totalValue = priceValue * quantityValue;
        String totalString = Double.toString(totalValue);
        String totalStringValue = "$" + totalString;
        if (total.getText() == totalStringValue) {
            f1 = true;
        } else
            f1 = false;
        return f1;
    }

}




