package TestCases;

import Pages.*;
import org.jsoup.Connection;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

@Listeners(Utils.ListenerTest.class)

public class BaseTest {
    public static WebDriver driver;
    public WebDriverWait wait;
    public HomePage homePage;
    public LoginPage loginPage;
    public ProductPage productPage;
    public CheckoutPage checkoutPage;


    /*public void waitForElement(WebElement locatorName){
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By) locatorName));

    }*/

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/Users/divyaramamurthy/IdeaProjects/VapasiSelenium-Divya/src/test/java/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://spree-vapasi.herokuapp.com/");
        BasePage basePage = new BasePage(driver);
        homePage = new HomePage(driver);
         loginPage = new LoginPage(driver);
         productPage = new ProductPage(driver);
         checkoutPage = new CheckoutPage(driver);

    }



    @BeforeMethod
    public void startTest(){

        driver.navigate().to("https://spree-vapasi.herokuapp.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();


    }

    @AfterMethod

    public void afterTest(){
        homePage.clickLogoutButton();
    }

    @AfterClass
    public void closeTest(){
        driver.quit();
    }
}
