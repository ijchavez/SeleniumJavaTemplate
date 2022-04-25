package test.baseTest;

import java.awt.AWTException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;

import utilities.Constants;
import utilities.Utilities;

public class BaseTestBrowser extends BaseTest{

	@BeforeMethod
	public void setUp(ITestContext context) throws AWTException, InterruptedException {   

		chromeOptions = new ChromeOptions();
		
		addOptionsArgumentsForBrowser(chromeOptions);
		setExperimentalOptions(chromeOptions);

        driver = new ChromeDriver(chromeOptions);
	    driver.manage().window().maximize();
	    
		utils = new Utilities(driver);
		utils.loadToList(usersList);
        timeoutManager(10);
        
        PageFactory.initElements(driver, this);
        context.setAttribute(Constants.WEBDRIVER_ATTRIBUTE_VALUE, driver);
        
        driver.get(Constants.URL);
        
    }

	
}
