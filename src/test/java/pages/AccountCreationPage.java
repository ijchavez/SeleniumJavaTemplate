package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreationPage extends BasePage{
    public AccountCreationPage(WebDriver remoteDriver){
        driver = remoteDriver;
        PageFactory.initElements(driver, this);

    }
    @FindBy(className = "page-heading")
    WebElement createAnAccountTitle;
    public WebElement getCreateAnAccountTitle(){
        return createAnAccountTitle;

    }
    @FindBy(name = "days")
    WebElement dayOfBirth;
    public WebElement getDayOfBirth(){
        return dayOfBirth;

    }
    @FindBy(name = "months")
    WebElement monthOfBirth;
    public WebElement getMonthOfBirth(){
        return monthOfBirth;

    }
    @FindBy(name = "months")
    WebElement yearOfBirth;
    public WebElement getYearOfBirth(){
        return yearOfBirth;

    }

}
