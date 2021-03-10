package Pages;

import TestCases.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends Driver {
    public String price = "";

    public HomePage(WebDriver driver) {
        this.driver = driver;
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


    @FindBy(xpath = "//input[@class='btn btn-primary']")
    WebElement filterSearchButton;

    @FindBys(@FindBy(xpath = "//div[@id='products']/child::div"))
    List<WebElement> searchResults;

    public void open(){
        driver.navigate().to("https://spree-vapasi.herokuapp.com/");
    }


    public void clickLoginButton() {
        //waitForElement(loginButton);
        loginButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public String verifyMessage() throws InterruptedException {
       // waitForElement(alertMessage);
        String messageText = alertMessage.getText();
        return messageText;
    }

    public void enterSearchCriteria(String text1) {
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
        //for(WebElement category: allCategories)
        for (int i = 0; i < allCategories.size(); i++) {
            if (allCategories.get(i).getText().contains(categoryName)) {
                //categorySelected = category;
                allCategories.get(i).click();
                //categorySelected.click();
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
        //for(int n = 0; n < searchResults.size(); n++){
            //if(searchResults.get(n).getText().equalsIgnoreCase(text3)){
                WebElement productElement = driver.findElement(By.linkText("Ruby on Rails Mug"));
        System.out.println(productElement.getText());
                //System.out.println(searchResults.get(n).getText());
               //searchResults.get(n).click();
        productElement.click();
            }
        }













