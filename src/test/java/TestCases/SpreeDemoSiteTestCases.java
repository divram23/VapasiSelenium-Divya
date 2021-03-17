package TestCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Utils.ListenerTest.class)

public class SpreeDemoSiteTestCases extends BaseTest {

    public void loginAccount(){
        homePage.clickLoginButton();
        loginPage.enterUserEmail("Divya@gmail.com");
        loginPage.enterPassword("123456");
        loginPage.clickLoginButton();
    }

    @DataProvider(name = "SearchPhrases")
    public Object[][] dataProvFunc(){
        return new Object[][]{
                {"Ruby"},{"Apache"}
        };
    }

    @Test(priority = 0)
    public void verifyLoginAndLogout() throws InterruptedException {
        homePage.clickLoginButton();
        loginPage.enterUsernameAndPassword("Divya@gmail.com", "123456");
        loginPage.clickLoginButton();
        String actualLoginMessage = homePage.verifyMessage();
        Assert.assertEquals(actualLoginMessage, "Logged in successfully");

        homePage.clickLogoutButton();
        String actualLogoutMessage = homePage.verifyMessage();
        Assert.assertEquals(actualLogoutMessage, "Signed out successfully.");
        loginAccount();
    }

  @Test(priority = 1, dataProvider ="SearchPhrases")
          public void SearchProduct(String searchPhrases) throws InterruptedException {
        loginAccount();
        homePage.enterSearchCriteria(searchPhrases);
        Boolean actualState = homePage.verifyAllSearchResultsContainsText(searchPhrases);
        Boolean expectedState = false;
        Assert.assertEquals(actualState, expectedState);

    }

    @Test(priority = 2)
    public void verifyProductsFilter() throws InterruptedException {
        loginAccount();
        homePage.selectCategory("Bags");
        homePage.selectPriceList("$15.00 - $18.00");
        homePage.clickSearchButton();

        Boolean actualPriceMatchState = homePage.verifyProductsPriceMatchesSelectedPrice();
        Boolean expectedPriceMatchState = true;
        Assert.assertEquals(actualPriceMatchState, expectedPriceMatchState);
    }

  @Test(priority = 3)
    public void verifyAddCart() throws InterruptedException {
        loginAccount();
        homePage.enterSearchProductName("Ruby on Rails Mug");
        homePage.selectProduct("Ruby on Rails Mug");
        productPage.addProductToCart();
        Boolean actualTotalMatchState = checkoutPage.verifyTotal();
        Boolean expectedTotalMatchState = false;
        Assert.assertEquals(actualTotalMatchState, expectedTotalMatchState);

    }





}

