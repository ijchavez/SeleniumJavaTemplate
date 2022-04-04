package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class BasePage {
	public WebDriver driver;

    public LandingPage landingPage;
    public LoginPage loginPage;
    public AccountCreationPage accountCreationPage;

    @FindBy(tagName="h3")
    List<WebElement> h3List;
    public List<WebElement> getH3List(){
    	return h3List;
    	
    }
    @FindBy(tagName="h1")
    List<WebElement> h1List;
    public List<WebElement> getH1List(){
    	return h1List;

    }
    @FindBy(tagName="h2")
    List<WebElement> h2List;
    public List<WebElement> getH2List(){
    	return h2List;
    	
    }
    @FindBy(tagName="h4")
    List<WebElement> h4List;
    public List<WebElement> getH4List(){
    	return h4List;
    	
    }
    @FindBy(tagName="button")
    List<WebElement> buttonList;
    public List<WebElement> getButtonList(){
    	return buttonList;
    	
    }
    
    public LandingPage startTest() {
    	landingPage = new LandingPage(driver);
    	return landingPage;
    	
    }

    public void selectFromListOfValues(WebElement aWebElement, String aSelection) {
        Select lovValues = new Select(aWebElement);
        lovValues.selectByVisibleText(aSelection);
        
    }
    public void selectByValue(WebElement aWebElement, String aSelection) {
        Select lovValues = new Select(aWebElement);
        lovValues.selectByValue(aSelection);

    }
    public void clickOnElementFromAList(List<WebElement> aListOfChevron, int posicion) {
    	WebElement chevronToClick = aListOfChevron.get(posicion);
    	chevronToClick.click();
    	
    }
    public void clickOnElement(WebElement aWebElement) {
    	aWebElement.click();
    	
    }
    public List<String> getTextFromListOfWebElements(List<WebElement> aListOfWebElements) {
    	List<String> listaElementos = new ArrayList<String>();
    	for(WebElement elementoTexto : aListOfWebElements) {
    		String unTexto = elementoTexto.getText();
    		listaElementos.add(unTexto);
    		
    	}
    	return listaElementos;
    	
    }
    public String getTextFromListOfWebElements(List<WebElement> aListOfWebElements, int posicion) {
		WebElement elementoTexto = aListOfWebElements.get(posicion);
		
		String anElementText = elementoTexto.getText();
   		return anElementText;
    	
    }
    public List<String> getAttributeValue(List<WebElement> aListOfWebElements, String aValue){
    	List<String> aListOfStrings = new ArrayList<String>();

    	for (WebElement anElement: aListOfWebElements) {
    		String valueOfAnElement = anElement.getAttribute(aValue);
    		aListOfStrings.add(valueOfAnElement);
    		
    	}
    	return aListOfStrings;
    	
    }
    public String getAttributeValue(WebElement anElement, String aValue){
   		String valueOfAnElement = anElement.getAttribute(aValue);
   		return valueOfAnElement;
    	
    }
    public void clickOnAButtonFromFilterList(List<WebElement> aListOfWebElements, int aButtonPosition) {
    	WebElement aButton = aListOfWebElements.get(aButtonPosition);
    	aButton.click();
    	
    }
    public void sendKeysToAWebElement(WebElement aWebElement, String aTextToSend) {
    	aWebElement.sendKeys(aTextToSend);
    	
    }
    public void sendKeysToAWebElement(WebElement aWebElement, String aTextToSend, Keys aKey) {
    	aWebElement.sendKeys(aTextToSend + aKey);
    	
    }
    public void waitForAListOfWebElementsToFullyLoad(List<WebElement> aListOfWebElements, long timeToWait, long pollingTime) {
    	Wait<WebDriver> wait = new FluentWait<>(driver)
    		    .withTimeout(Duration.ofSeconds(timeToWait))
    		    .pollingEvery(Duration.ofSeconds(pollingTime))
    		    .ignoring(NoSuchElementException.class);
    	
    	wait.until(ExpectedConditions.visibilityOfAllElements(aListOfWebElements));
        
    }
    public void waitForAWebElementToFullyLoad(WebElement anElement, long timeToWait, long pollingTime) {
    	Wait<WebDriver> wait = new FluentWait<>(driver)
    		    .withTimeout(Duration.ofSeconds(timeToWait))
    		    .pollingEvery(Duration.ofSeconds(pollingTime))
    		    .ignoring(NoSuchElementException.class);
        
    	 wait.until(ExpectedConditions.visibilityOf(anElement));
    	
    }
    public void waitForAWebElementToBeClickable(WebElement anElement, long timeToWait, long pollingTime) {
    	Wait<WebDriver> wait = new FluentWait<>(driver)
    		    .withTimeout(Duration.ofSeconds(timeToWait))
    		    .pollingEvery(Duration.ofSeconds(pollingTime))
    		    .ignoring(NoSuchElementException.class);
    	
        wait.until(ExpectedConditions.elementToBeClickable(anElement));   
        
    }
    public void waitForAWebElementToHaveText(WebElement anElement, int timeToWait, int pollingTime, String aTextToHave) {
        Wait<WebDriver> wait = generalWait(timeToWait, pollingTime);
        wait.until(ExpectedConditions.textToBePresentInElement(anElement, aTextToHave));

    }
    public Wait generalWait(int timeToWait, int pollingTime){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeToWait))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        return wait;
    }
    public String getTextFromAWebElement(WebElement aWebElement) {
    	String aWebElementText = aWebElement.getText();
    	return aWebElementText;
    	
    }
    public void clickOnAnElement(WebElement anElement) {
    	anElement.click();
    	
    }
    public WebElement getValueFromLov(WebElement anElement) {
    	Select selectValue = new Select(anElement);
    	WebElement aWebElement = selectValue.getFirstSelectedOption();
        
    	return aWebElement;
    	
    }
    public boolean isWebElementDisplayed(WebElement aWebElement) {
		boolean WebElementIsDisplayed = aWebElement.isDisplayed();
		
		return WebElementIsDisplayed;
		
    }
    public void getToAnUrl(String anUrl) {
    	driver.get(anUrl);
    	
    }
    
}
