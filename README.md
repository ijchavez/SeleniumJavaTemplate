# SeleniumJavaTemplate
Proyecto template para java, con Selenium y TestNG

# OutPut CreateStructures.java

```
@FindBy Created...
    @FindBy(id="login")
    WebElement loginBtn;
    public WebElement clickOnLoginBtn(){
    loginBtn.click();
   
    landingPage = new LandingPage(driver);
    return landingPage;    
    }
Add these statement in your Extends Page: Public LandingPage landingPage;

**************************************************************************

@FindBy Created...
    @FindBy(xpath="//*[@class='titulo-pagina']")
    WebElement tituloPagina;
    public WebElement geTituloPagina(){
      return tituloPagina;
     
    }

**************************************************************************

Page Created...
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver remoteDriver){
        driver = remoteDriver;
        PageFactory.initElements(driver, this);

    }
   
}


```