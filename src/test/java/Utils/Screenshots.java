package Utils;

import TestCases.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Screenshots extends Driver {

    public String testName;

    public Screenshots(String testName){
        this.testName = testName;
    }

    public Screenshots() {
        this.testName = testName;
    }

    public void takeScreen(String text, Boolean addReport) throws IOException {

        String testName = text;
        String text1 = text;
        log(text1, true);

        // set file name and destination for screen shot
        File scrFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat(
                "dd_MMM_yyyy__hh_mm_ssaa");
        String destDir = "./surefire-reports/html/screenshots/";
        new File(destDir).mkdirs();
        String destFile = testName + "_" + dateFormat.format(new Date())
                + ".png";

        // copy screen shot to directory for jenkins
        try {
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        log("screenShot: " + destDir + "/" + destFile, true);
        // Display screenshot to ReportNG
        if (addReport) {
            //String dest = destFile;
            String userDirector = "./screenshots/";
            String inputFileName = destDir+destFile;
            File destination=new File(inputFileName);
            InputStream is=new FileInputStream(inputFileName);
            byte[] imageBytes= FileUtils.readFileToByteArray(destination);
            String base64= "data:image/png;base64,"+ Base64.getEncoder().encodeToString(imageBytes);



            Reporter.log("<a href='"+ destination.getAbsolutePath() + "'>" +
                    " <img src='"+ destination.getAbsolutePath() + "' height='100' width='100'/> </a>");


        }

    }


    // writes to console or/and report log
    // boolean controls whether report log is written to
    public void log(String text, Boolean addReport) {
        String newLine = System.getProperty("line.separator");
        String testName = text;
        if (addReport) {
            final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
            System.setProperty(ESCAPE_PROPERTY, "false");
            Reporter.log(text.replace("<u><b>||||||", "<u><b>" + testName));
        } else {
            System.out.println(testName + "_"  + text + newLine);
        }
    }

    // Calls downloadreport, copys the perfecto report to the screen directory
    // boolean will add the report to the testNG report
    public void downloadReportDisplay(Boolean addReport) throws IOException {

        if (isDevice()) {
            // set file format and destination for report
            DateFormat dateFormat = new SimpleDateFormat(
                    "dd_MMM_yyyy__hh_mm_ssaa");
            String destDir = "./surefire-reports/html/screenshots/";
            new File(destDir).mkdirs();
            String destFile = dateFormat.format(new Date());

            // download report
            downloadReport("pdf", destDir , destFile);
            // Display screenshot to ReportNG
            String userDirector = "./screenshots/";

            String destFileNew = destFile + ".pdf";

            log("perfectoReport: " + userDirector + destFileNew, false);
            if (addReport) {
                log("<a href=\"" + userDirector + destFileNew
                        + "\">Perfecto Report</a><br />", addReport);
            }
        }
    }

    private boolean isDevice() {
        boolean isDevice = true;
        return isDevice;
    }


    // download report from perfecto
    private void downloadReport(String type, String fileLocation, String file)
            throws IOException {
        if (isDevice()) {
            // downloads report for remote web driver
            String command = "mobile:report:download";
            Map<String, Object> params = new HashMap<>();
            params.put("type", type);
            String report;
            report = (String) getDriver().executeScript(
                    command, params);

            //download to directory for jenkins
            File reportFile = new File(fileLocation + "/" + file + "." + type);
            BufferedOutputStream output = new BufferedOutputStream(
                    new FileOutputStream(reportFile));
            byte[] reportBytes = OutputType.BYTES
                    .convertFromBase64Png(report);
            output.write(reportBytes);
            output.close();
        }
    }

    private JavascriptExecutor getDriver() {
        return (JavascriptExecutor) driver;
    }
}
