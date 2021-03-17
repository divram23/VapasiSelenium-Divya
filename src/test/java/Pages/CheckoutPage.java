package Pages;

import TestCases.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {


    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//td[@class='lead text-primary cart-item-price']")
    WebElement price;

    @FindBy(name = "order[line_items_attributes][0][quantity]")
    WebElement quantity;

    @FindBy(xpath = "//td[@class='cart-item-quantity']/following-sibling::td")
    WebElement total;

    @FindBy(xpath = "//input[@name='commit']")
    WebElement emptyCartButton;

    @FindBy(xpath = "//a[normalize-space()='Continue shopping']")
    WebElement continueShoppingButton;

    public Boolean verifyTotal() {
        Boolean f1 = false;
        String priceString = price.getText().replace("$", "");
        Double priceValue = Double.parseDouble(priceString);
        String quantityString = quantity.getAttribute("value");
        System.out.println(quantityString);
        Integer quantityValue = Integer.parseInt(quantityString);
        Double totalValue = priceValue * quantityValue;
        String totalString = Double.toString(totalValue);
        String totalStringValue = "$" + totalString;
        if (total.getText() == totalStringValue) {
            f1 = true;
        } else
            f1 = false;
        return f1;
    }

    public void makeCartEmpty(){
        emptyCartButton.click();
        continueShoppingButton.click();
    }

}




