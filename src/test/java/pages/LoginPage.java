package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver remoteDriver){
        driver = remoteDriver;
        PageFactory.initElements(driver, this);

    }
    @FindBy(id = "email_create")
    WebElement emailCreateInput;
    public WebElement getEmailCreateInput(){
        return emailCreateInput;

    }

    @FindBy(id = "SubmitCreate")
    WebElement submitCreateBtn;
    public AccountCreationPage clickOnSubmitCreateBtn(){
        submitCreateBtn.click();

        accountCreationPage = new AccountCreationPage(driver);
        return accountCreationPage;

    }

}
