package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import test.baseTest.BaseTest;

import org.testng.Reporter;
import java.util.HashMap;
import java.util.Map;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ExtentTestManager extends BaseTest {

   static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
   public static ExtentReports extent = ExtentManager.getInstance();
   private static final ThreadLocal<String> categoryName = new ThreadLocal<String>();

   public static synchronized ExtentTest getTest() {
       return extentTestMap.get((int) Thread.currentThread().getId());
       
   }
   public static synchronized void endTest() {
       extent.flush();
       
   }
   public synchronized static void createTest(String testName, String description) {
       extentTestMap.put((int) Thread.currentThread().getId(), extent.createTest(testName, description));
       
   }
   public static ThreadLocal<String> getCategoryName() {
       return categoryName;
       
   }
   public static void setCategoryName(String categoryName) {
       getCategoryName().set(categoryName);
       
   }
   public synchronized static void reporterLog(String log) {
           if (ExtentTestManager.getTest() != null) {
               ExtentTestManager.getTest().log(Status.INFO, log);
               Reporter.log(log + "<br/>");
               
           }
           
       }
   public synchronized static void reporterJsonLog(String aLog, String aNodeTitle) {
	   ExtentTestManager.getTest().createNode(aNodeTitle).info(aLog);
       
   }
   public synchronized static void takeScreenShot() {
   	   String filePathExtent = "Screenshots/" + System.currentTimeMillis() + ".jpg";
       String filePath = ExtentManager.getReportBaseDirectory() + filePathExtent;
   	   ExtentTestManager.getTest().addScreenCaptureFromPath(filePath)
   	   					.pass(MediaEntityBuilder.createScreenCaptureFromPath(filePath)
						.build());
   	
   }
   
}