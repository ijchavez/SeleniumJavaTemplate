package test.baseTest;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import constructor.User;
import export.CSV_Helper;
import pages.BasePage;
import utilities.Constants;
import utilities.Utilities;

public class BaseTest extends BasePage{
	ChromeOptions chromeOptions;
	protected Utilities utils;
	protected User user;
	List<String> usersList = new ArrayList<String>();
	
	@BeforeTest
	public void setUpBrowser() throws IOException {
		System.setProperty(Constants.WEBDRIVER_CHROME, Constants.CHROMEDRIVER_FILE);
 		//usersList = CSV_Helper.getDataFromCSV(Constants.DATALOADER + "users.csv");
	
	}
	@AfterMethod
	public void closeDriver() throws InterruptedException {
		Thread.sleep(100);
		if (driver != null) {
			driver.quit();
			
		}
		
	}
 	public void addOptionsArgumentsForBrowser(ChromeOptions aChromeDriverOptions) {
		aChromeDriverOptions.addArguments(Constants.START_MAXIMIZED, Constants.DISABLE_INFOBARS, Constants.DISABLE_EXTENSIONS, Constants.DISABLE_NOTIFICATIONS);
		aChromeDriverOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
		
	}
 	public void setExperimentalOptions(ChromeOptions aChromeDriverOptions) {
 		Map<String, Object> preferences = setHashMapExperimentalOptions();
		
 		aChromeDriverOptions.setExperimentalOption(Constants.EXCLUDE_SWITCHES, Collections.singletonList(Constants.ENABLE_AUTOMATION));
 		aChromeDriverOptions.setExperimentalOption(Constants.HASHMAP_PREFERENCES, preferences);
 		aChromeDriverOptions.setAcceptInsecureCerts(true);
    	
 	}
 	public Map<String, Object> setHashMapExperimentalOptions(){
 		Map<String, Object> prefs = new HashMap<String, Object>();
 		
 		prefs.put(Constants.CREDENTIALS_ENABLE_SERVICE, false);
 		prefs.put(Constants.PROFILE_PASSWORD_MANAGER_ENABLED, false);

 		return prefs;
 		
 	}
	public void addOptionsArgumentsForHeadlessRun(ChromeOptions aChromeDriverOptions) {
		aChromeDriverOptions.addArguments(Constants.HEADLESS, Constants.DISABLE_GPU, Constants.WINDOW_SIZE_FULLSCREEN, Constants.IGNORE_CERTIFICATE_ERROR,
										 Constants.DISABLE_EXTENSIONS, Constants.NO_SANDBOX, Constants.DISABLE_DEV_SHM_USAGE);
		aChromeDriverOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
			 	 
	}
    public void refreshPage() throws AWTException {
		driver.navigate().refresh(); 	
    	
    }	
	public void timeoutManager(int seconds) {
		Duration dur = Duration.ofSeconds(seconds);
		driver.manage().timeouts().implicitlyWait(dur);
		
	}
    
}	