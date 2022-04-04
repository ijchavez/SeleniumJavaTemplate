package test.modules;

import java.io.IOException;
import java.util.List;

import com.github.javafaker.Faker;
import io.thedocs.soyuz.to;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import constructor.User;
import export.CSV_Helper;
import test.baseTest.BaseTestBrowser;
import utilities.Constants;
import utilities.Utilities;

@Listeners (extentReport.FareyeListener.class)
public class test extends BaseTestBrowser{
	Faker fk = new Faker();
	List<String> aListOfDays = to.list("4", "10");
	List<String> aListOfMonths = to.list("4", "5");
	int i = -1;
	@Test(invocationCount = 2, description = "test de creacion de una cuenta")
	public void yourTestHere() throws IOException, InterruptedException {
			landingPage = startTest();
			loginPage = landingPage.clickOnSignInBtn();

			i++;

			WebElement emailInput = loginPage.getEmailCreateInput();
			String fakeMail = fk.internet().emailAddress();

			utils.printAndReport(Constants.NORMALPRINT, fakeMail);
		    loginPage.sendKeysToAWebElement(emailInput, fakeMail);

			accountCreationPage = loginPage.clickOnSubmitCreateBtn();

			WebElement createAnAccountTitle = accountCreationPage.getCreateAnAccountTitle();
			accountCreationPage.waitForAWebElementToHaveText(createAnAccountTitle, 30, 3, "CREATE AN ACCOUNT");

			String createAnAccountTitleText = accountCreationPage.getTextFromAWebElement(createAnAccountTitle);

			utils.printAndReport(Constants.NORMALPRINT, createAnAccountTitleText);
			Assert.assertEquals(createAnAccountTitleText, "CREATE AN ACCOUNT", createAnAccountTitleText + "no es igual a " + "CREATE AN ACCOUNT");

			WebElement days = accountCreationPage.getDayOfBirth();
			WebElement month = accountCreationPage.getMonthOfBirth();
			WebElement year = accountCreationPage.getYearOfBirth();

			accountCreationPage.selectByValue(days, aListOfDays.get(i));
		    accountCreationPage.selectByValue(month, aListOfMonths.get(i));
		    //accountCreationPage.selectByValue(year, "2004");

			// --> usamos otro comento esteutils.scrollToADirection("800");


	}
	
}
