package TestCases;

import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Utils.ListenerTest.class)

public class TestSet1 extends Driver{

    public void loginAccount() throws InterruptedException {
        homePage.clickLoginButton();
        loginPage.enterUserEmail("Divya@gmail.com");
        loginPage.enterPassword("123456");
        loginPage.clickLoginButton();
    }

    @DataProvider(name = "SearchPhrases")
    protected Object[][] searchPhrasesDataProvider(){
        return new Object[][] {{"Ruby"},{"Apache"}};
    }

    @Test(priority = 0)
    public void verifyLoginAndLogout() throws InterruptedException {
        homePage.clickLoginButton();
        loginPage.enterUsernameAndPassword("Divya@gmail.com", "123456");
        loginPage.clickLoginButton();
        //Thread.sleep(3000);
        String actual1 = homePage.verifyMessage();
        Assert.assertEquals(actual1, "Logged in successfully");

        homePage.clickLogoutButton();
        String actual2 = homePage.verifyMessage();
        Assert.assertEquals(actual2, "Signed out successfully.");
        loginAccount();
    }

  @Test(priority = 1)
          public void SearchProduct() throws InterruptedException {
        loginAccount();
        homePage.enterSearchCriteria("Ruby");
        Boolean actual1 = homePage.verifyAllSearchResultsContainsText("Ruby");
        Boolean expected1 = true;
        Assert.assertEquals(actual1, expected1);

       /*homePage.enterSearchCriteria("Apache");
       //homePage.verifyAllSearchResultsContainsText("Apache");
       Boolean actual2 = homePage.verifyAllSearchResultsContainsText("Apache");
       Boolean expected2 = true;
       Assert.assertEquals(actual2, expected2);*/

    }

    @Test(priority = 2)
    public void verifyProductsFilter() throws InterruptedException {
        loginAccount();
        homePage.selectCategory("Bags");
        homePage.selectPriceList("$15.00 - $18.00");
        homePage.clickSearchButton();
        //homePage.verifyProductsPriceMatchesSelectedPrice();
        Boolean actual3 = homePage.verifyProductsPriceMatchesSelectedPrice();
        Boolean expected3 = true;
        Assert.assertEquals(actual3, expected3);
    }

  /* @Test(priority = 3)
    public void verifyAddCart() throws InterruptedException {
        loginAccount();
        homePage.enterSearchCriteria("Ruby on Rails Mug");
        //Boolean actual4 =homePage.verifyAllSearchResultsContainsText("Ruby");
        //System.out.println(actual4);
       homePage.selectProduct("Ruby on Rails Mug");
       //Thread.sleep(3000);
       //productPage.open();
       productPage.addProductToCart();
       Boolean actual4 = checkoutPage.verifyTotal();
       Boolean expected4 = true;
       Assert.assertEquals(actual4, expected4);

    }

    /*@Test(priority = 4, dependsOnMethods = { "verifyLogin" })
    public void verifyClearCart(){

    }*/
}
