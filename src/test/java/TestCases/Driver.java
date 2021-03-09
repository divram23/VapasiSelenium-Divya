package TestCases;

import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProductPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Listeners(Utils.ListenerTest.class)

public class Driver {
    public static WebDriver driver;
    public WebDriverWait wait;
    public HomePage homePage;
    public LoginPage loginPage;
    public ProductPage productPage;
    public CheckoutPage checkoutPage;



 /*   public Driver() {
       this.driver = driver;
    }*/

 /*   public Driver(WebDriver driver) {
        this.driver = driver;
    }*/

    public void waitForElement(WebElement locatorName){
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By) locatorName));

    }

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/Users/divyaramamurthy/IdeaProjects/VapasiSelenium-Divya/src/test/java/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://spree-vapasi.herokuapp.com/");
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
           // (ITestResult result){
       /* if(ITestResult.FAILURE==result.getStatus())
        {
            try
            {
                TakesScreenshot ts=(TakesScreenshot)driver;
                File source=ts.getScreenshotAs(OutputType.FILE);
                String timeStamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
                try{
                    FileHandler.copy(source, new File("./Screenshots/"+result.getName()+" "+timeStamp+".png"));
                    System.out.println("Screenshot taken");
                }catch (Exception e){
                    System.out.println("Exception while renaming file: "+e.getMessage());
                }
            }
            catch (Exception e)
            {
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }
        }*/
        homePage.clickLogoutButton();
        //driver.close();
    }

    @AfterClass
    public void closeTest(){
        //driver.close();
        driver.quit();
    }



}
