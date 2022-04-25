package pages;

import java.awt.AWTException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class BasePage {
	public WebDriver driver;
	
    public LoginPage loginPage;

    public LoginPage startTest() {
    	loginPage = new LoginPage(driver);
    	return loginPage;
    	
    }
    public void initElements(WebDriver remoteDriver, Object aPage) {
    	PageFactory.initElements(remoteDriver, aPage);
    	
    }
    public void refreshPage() throws AWTException {
		driver.navigate().refresh(); 	
    	
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
    @FindBy(tagName="h3")
    List<WebElement> h3List;
    public List<WebElement> getH3List(){
    	return h3List;
    	
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
    @FindBy(tagName="tr")
    List<WebElement> tableList;
    public List<WebElement> getTableList(){
    	return tableList;
    	
    }
    Select aSelection;
    public Select getSelect(WebElement aWebElement) {
    	aSelection = new Select(aWebElement);
    	return aSelection;
    	
    }
    public void selectByVisibleText(WebElement aWebElement, String aValue) {
    	aSelection = getSelect(aWebElement);
    	aSelection.selectByVisibleText(aValue);
    	
    }
	public List<WebElement> valuesFromSelect(WebElement aWebElement){
		aSelection = getSelect(aWebElement);
    	return aSelection.getOptions();
    	
    }
    public WebElement getValueFromLov(WebElement anElement) {
    	aSelection = new Select(anElement);
    	WebElement aWebElement = aSelection.getFirstSelectedOption();
        
    	return aWebElement;
    	
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
    Wait<WebDriver> waitGeneral;
    public Wait<WebDriver> generalFluentWait(long timeToWait, long pollingTime) {
    	waitGeneral = new FluentWait<>(driver)
		      .withTimeout(Duration.ofSeconds(timeToWait))
		      .pollingEvery(Duration.ofSeconds(pollingTime))
		      .ignoring(NoSuchElementException.class);

    	return waitGeneral;
    	
    }
    Wait<WebDriver> waitParticular;
    public void waitForAListOfWebElementsToFullyLoad(List<WebElement> aListOfWebElements, long timeToWait, long pollingTime) {
    	waitParticular = generalFluentWait(timeToWait, pollingTime);
    	waitParticular.until(ExpectedConditions.visibilityOfAllElements(aListOfWebElements));
        
    }
    public void waitForAWebElementToFullyLoad(List<WebElement> aListOfWebElements, int pos, long timeToWait, long pollingTime) {
    	waitParticular = generalFluentWait(timeToWait, pollingTime);
    	waitParticular.until(ExpectedConditions.visibilityOf(aListOfWebElements.get(pos)));
    	
    }
    public void waitForAWebElementToFullyLoad(WebElement anElement, long timeToWait, long pollingTime) {
    	waitParticular = generalFluentWait(timeToWait, pollingTime);
    	waitParticular.until(ExpectedConditions.visibilityOf(anElement));
    	
    }
    public void waitForAWebElementToBeClickable(WebElement anElement, long timeToWait, long pollingTime) {
    	waitParticular = generalFluentWait(timeToWait, pollingTime);
    	waitParticular.until(ExpectedConditions.elementToBeClickable(anElement));   
        
    }
    public void waitForAWebElementToBeInvisible(WebElement anElement, long timeToWait, long pollingTime) {
    	waitParticular = generalFluentWait(timeToWait, pollingTime);
    	waitParticular.until(ExpectedConditions.invisibilityOf(anElement));   
        
    }
    public void waitForAWebElementToBeClickable(List<WebElement> aListOfWebElements, int pos, long timeToWait, long pollingTime) {
    	waitParticular = generalFluentWait(timeToWait, pollingTime);
    	waitParticular.until(ExpectedConditions.elementToBeClickable(aListOfWebElements.get(pos)));   
        
    }
    public void waitForAWebElementHaveAnAttribute(WebElement anElement, long timeToWait, long pollingTime, String anAttribute, String aValue) {
    	waitParticular = generalFluentWait(timeToWait, pollingTime);
    	waitParticular.until(ExpectedConditions.attributeToBe(anElement, anAttribute, aValue)); 
        
    } 
    public void waitForTextToAppear(WebElement anElement, long timeToWait, long pollingTime, String textToAppear) {
    	waitParticular = generalFluentWait(timeToWait, pollingTime);
    	waitParticular.until(ExpectedConditions.textToBePresentInElement(anElement, textToAppear));
    	        
    }
    public void waitForTextToAppear(List<WebElement> aListOfWebElements, int pos, long timeToWait, long pollingTime, String textToAppear) {
    	waitParticular = generalFluentWait(timeToWait, pollingTime);
    	waitParticular.until(ExpectedConditions.textToBePresentInElement(aListOfWebElements.get(pos), textToAppear));
    	
    }    	        
    public String getTextFromAWebElement(WebElement aWebElement) {
    	String aWebElementText = aWebElement.getText();
    	return aWebElementText;
    	
    }
    public void clickOnAnElement(WebElement anElement) {
    	anElement.click();
    	
    }
    public boolean isWebElementDisplayed(WebElement aWebElement) {
		boolean WebElementIsDisplayed = aWebElement.isDisplayed();
		
		return WebElementIsDisplayed;
		
    }
    public void getToAnUrl(String anUrl) {
    	driver.get(anUrl);
    	
    }
    
}
