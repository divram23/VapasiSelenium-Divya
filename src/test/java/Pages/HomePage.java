package Pages;

import TestCases.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {
    public String price = "";

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement loginButton;


    @FindBy(className = "alert")
    WebElement alertMessage;


    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement logoutButton;

    @FindBy(xpath = "//input[@id='keywords']")
    WebElement searchCriteria;

    @FindBy(xpath = "//input[@id='keywords']")
    WebElement searchButton;

    @FindBys(@FindBy(xpath = "//h4[text()='Shop by Categories']/following-sibling::div[1]/a"))
    List<WebElement> allCategories;

    @FindBys(@FindBy(xpath = "//h4[normalize-space()='Price Range']/following-sibling::ul/child::li"))
    List<WebElement> allPrices;

    @FindBys(@FindBy(xpath ="//div[@id='products']/following-sibling::ul/child::li"))
    List<WebElement> pages;

    //ul[@class='pagination']/child::li


    @FindBy(xpath = "//input[@class='btn btn-primary']")
    WebElement filterSearchButton;

    @FindBys(@FindBy(xpath = "//div[@id='products']/child::div"))
    List<WebElement> searchResults;

    @FindBy(id = "link-to-cart")
    WebElement cartButtonHomePage;

    @FindBy(className = ".alert.alert-info")
    WebElement cartInfoMessage;

    @FindBy(className = "btn btn-default")
    WebElement continueShoppingButton;

    @FindBy(id = "btn btn-default")
    WebElement emptyCartButton;



    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public String verifyMessage() throws InterruptedException {
        String messageText = alertMessage.getText();
        return messageText;
    }

    public void enterSearchCriteria(String text1) {
        searchCriteria.sendKeys(text1);
        searchButton.click();
    }

    public void enterSearchProductName(String text1) {
        /*cartButtonHomePage.click();
        if(cartInfoMessage.getText() == "Your cart is empty")
        {
            continueShoppingButton.click();
        }
        else
        {
            emptyCartButton.click();
            continueShoppingButton.click();
        }*/
        searchCriteria.sendKeys(text1);
        searchButton.click();
    }

    public Boolean verifyAllSearchResultsContainsText(String text2) {
        Boolean flag = false;
        for (int m = 0; m < searchResults.size(); m++) {
            //System.out.println(searchResults.get(m).getText());
            if (searchResults.get(m).getText().contains(text2)) {
                flag = true;
                //     System.out.println(searchResults.get(m).getText());
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public void selectCategory(String categoryName) {
        WebElement categorySelected = null;
        for (int i = 0; i < allCategories.size(); i++) {
            if (allCategories.get(i).getText().contains(categoryName)) {
                allCategories.get(i).click();

            }
        }
    }

    public void selectPriceList(String s) {
        price = s;
        for (int i = 0; i < allPrices.size(); i++) {
            if (allPrices.get(i).getText().contains(s)) {
                allPrices.get(i).click();
            }
        }
    }

    public void clickSearchButton() {
        filterSearchButton.click();
    }


    public Boolean verifyProductsPriceMatchesSelectedPrice() {
        Boolean flag1 = false;
        String[] strings = price.split(" - ");
        String strMin = strings[0];
        String strMinNew = strMin.replace("$", "");
        String strMax = strings[1];
        String strMaxNew = strMax.replace("$", "");
        double min = Double.parseDouble(strMinNew);
        double max = Double.parseDouble(strMaxNew);
        String priceText = "";
        String[] productsText = new String[2];
        String priceValueString = "";
        String priceValueStringNew = "";
        double priceValue;


        for (int j = 0; j < searchResults.size(); j++) {
            priceText = searchResults.get(j).getText();
            //System.out.println(priceText);
            productsText = priceText.split("\\r?\\n");
            priceValueString = productsText[1];
            priceValueStringNew = priceValueString.replace("$", "");
            priceValue = Double.parseDouble(priceValueStringNew);
            if (priceValue >= min && priceValue <= max) {
                flag1 = true;
                //       System.out.println(priceValue);
            } else {
                flag1 = false;
                break;
            }
        }
        return flag1;
    }

    public void selectProduct(String text3) {
        driver.findElement(By.className("two-col")).click();

        //for(int n=1; n < pages.size(); n++){
            //pages.get(n).getText();

            for (int m = 0; m < searchResults.size(); m++) {
                if (searchResults.get(m).getText().contains(text3)) {
                    searchResults.get(m).click();
                }
                //pages.get(n).click();
            }
        }
    }









