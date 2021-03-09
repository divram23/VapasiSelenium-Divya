package Utils;

import TestCases.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListenerTest extends Driver implements ITestListener{
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(result.getName()+" test case started:");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Testcase Passed: "+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Testcase Failed: " + result.getName());
        File screenShotName = null;
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timeStamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
            try {
                screenShotName = new File("./Screenshots/" + result.getName() + " " + timeStamp + ".png");
                FileHandler.copy(source, screenShotName);
                System.out.println("Screenshot taken");
                Reporter.log("<br>  <img src='"+screenShotName+"' height='400' width='400' /><br>");
              //  Reporter.log("<a href="+screenShotName+" >screenshot</a>");
            } catch (Exception e) {
                System.out.println("Exception while renaming file: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }

      //  Reporter.log("<br>  <img src='"+screenShotName+"' height='100' width='100' /><br>");
        //Reporter.log("<a href="+screenShotName+" >screenshot</a>");

       //String filePath = screenShotName.toString();
       // String path = "<img src="\"file://"" alt="\"\"/" />";
       //Reporter.log(path);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Testcase Skipped: "+result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
