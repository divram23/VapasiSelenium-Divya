package Utils;

import TestCases.Driver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.IExtentTestClass;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import static org.testng.Reporter.log;

public class ListenerTest extends Driver implements ITestListener{

    Screenshots screenshots = new Screenshots();

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(result.getName()+": TestCase execution started");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Testcase Passed: "+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Testcase Failed: " + result.getName());
        try {
            screenshots.takeScreen(result.getName(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Testcase Skipped: "+result.getName());
    }



    @Override
    public void onStart(ITestContext context) {
        System.out.println("TEST EXECUTION STARTED");

    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("TESTING FINISHED");

    }
}
