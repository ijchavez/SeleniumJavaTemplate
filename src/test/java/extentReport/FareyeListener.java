package extentReport;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.Constants;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static extentReport.ExtentManager.*;

public class FareyeListener implements ITestListener {
    private static long endTime;
    private static void setStartTime(long startTime) {						
    	
    }
    private static void setEndTime(long endTime) {
        FareyeListener.endTime = endTime;
        
    }
    public synchronized static long getReportName() {
        return endTime;
        
    }
    public synchronized void onStart(ITestContext context) {
    	String outPutDirectory = context.getOutputDirectory();
    	System.out.println("Test suite started " + outPutDirectory + " --- " + context.getName());
    	setReportName(outPutDirectory.substring(58) + "_" + context.getName());
    	
    }
    public synchronized void onFinish(ITestContext context) {
        setStartTime(context.getStartDate().getTime());
        setEndTime(context.getEndDate().getTime());
        System.out.println("Test suite finished " + context.getOutputDirectory() + " --- " + context.getName());
        
        
    }
    public synchronized void onTestStart(ITestResult result) {
        System.out.println("===== Executing : " + getSimpleMethodName(result) + " =====");
        ExtentTestManager.createTest(result.getName(),result.getMethod().getDescription());
        ExtentTestManager.setCategoryName(getSimpleClassName(result));
        
    }
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println("===== Finish sucessfully: " + getSimpleMethodName(result) + " =====");
        ExtentTestManager.getTest().assignCategory(getSimpleClassName(result));
        ExtentTestManager.getTest().pass("<br><font color= green>"+"Image: "+"</font></b>",
		
        MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(getSimpleMethodName(result), result)).build());
        addExtentLabelToTest(result);
        ExtentTestManager.endTest();
        
    }
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println("===== Finish with errors: " + getSimpleMethodName(result) + " =====");
        ExtentTestManager.getTest().assignCategory(getSimpleClassName(result));
        ExtentTestManager.getTest().log(Status.FAIL, result.getName() + " Test Failed: " + result.getThrowable());
        ExtentTestManager.getTest().fail("<br><font color= red>"+"Image: "+"</font></b>",
		
        MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(getSimpleMethodName(result), result)).build());
        addExtentLabelToTest(result);
        ExtentTestManager.endTest();
        
    }
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println("===== This test was skipped: " + getSimpleMethodName(result) + " =====");
        ExtentTestManager.getTest().log(Status.SKIP, result.getName() + " Test Skipped" +  result.getThrowable());
        
    }
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    	
    }
    private synchronized String getSimpleClassName(ITestResult result) {
        return result.getMethod().getRealClass().getSimpleName();
        
    }
    private synchronized String getSimpleMethodName(ITestResult result) {
        return result.getName();
        
    }
    private synchronized void addExtentLabelToTest(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS)
            ExtentTestManager.getTest().pass(MarkupHelper.createLabel("Test finished successfully", ExtentColor.GREEN));
        
        else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().fail(MarkupHelper.createLabel("Test has failed", ExtentColor.RED));
            
        } else
            ExtentTestManager.getTest().skip(MarkupHelper.createLabel("Test was skipped", ExtentColor.ORANGE));
        
    }
    private static synchronized String takeScreenshot(String methodName, ITestResult result) {

        DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_ss_SSS");
        Date date = new Date();
        String dateName = dateFormat.format(date);
        String filePathExtent = OUTPUT_FOLDER_SCREENSHOTS + methodName + "_" + dateName + ".png";
        String filePath = ExtentManager.getReportBaseDirectory() + filePathExtent;
        String encodedBase64=null;
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute(Constants.WEBDRIVER_ATTRIBUTE_VALUE);
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileInputStream fileInputStreamReader = new FileInputStream(screenshotFile); 
            
            byte[] bytes = new byte[(int) screenshotFile.length()];
            fileInputStreamReader.read(bytes);
            
            encodedBase64 = Base64.encodeBase64String(bytes);
            FileUtils.copyFile(screenshotFile, new File(filePath));
            
            fileInputStreamReader.close();
            
        }catch (IOException e){
            e.getStackTrace();
            Reporter.log("Failed to take screenshot " + e, true);
            
        }
        return encodedBase64;
        
    }


}