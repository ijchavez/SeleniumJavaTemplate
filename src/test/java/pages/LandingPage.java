package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage{
    public LandingPage(WebDriver remoteDriver){
        driver = remoteDriver;
        PageFactory.initElements(driver, this);

    }
    @FindBy(className = "login")
    WebElement signInBtn;
    public LoginPage clickOnSignInBtn(){
        signInBtn.click();

        loginPage = new LoginPage(driver);
        return loginPage;

    }

}
