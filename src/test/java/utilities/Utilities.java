package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.common.collect.Ordering;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.*;


import constructor.User;

import extentReport.ExtentTestManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pages.BasePage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utilities extends BasePage {
	
	public static final String WINDOW_OPEN = "window.open()";
	public static final String SCROLL_INTO_VIEW = "arguments[0].scrollIntoView();";
	public static final String SCROLL_TO_DIR_START = "window.scrollBy(0,";
	public static final String SCROLL_TO_DIR_END = ")";
	public static final String YES = "yes";
	public static final String INDENT_NUMBER = "indent-number";
	public static final String DIVPRECODE_START = "<div><pre><code>"; 
	public static final String DIVPRECODE_END = "</div></pre></code>";
	public static final String XML_START = "<div><textarea style=\"border: none; background-color: #243140\" rows=\"30\" cols=\"120\">";
	public static final String XML_END = "</div></textarea>";
	public static final String ES_LANGUAGE = "es";
	public static final String ES_COUNTRY = "ES";
	
    public Utilities(WebDriver remoteDriver){
        driver = remoteDriver;
        initElements(driver, this);

    }
    public Utilities(){


    }
    public String getTitle(){
        String titulo = driver.getTitle();
        return titulo;

    }
    public String getCurrentUrl(){
        String url = driver.getCurrentUrl(); 
        return url;

    }

    public String makeSplit(String aString, String aSplitter, int pos){
        String[] stringToSplit = aString.split(aSplitter);

        String aStringSplitted = stringToSplit[pos];
        return aStringSplitted;

    }
    public String[] makeSplit(String aString, String aSplitter){
        String[] stringToSplit = aString.split(aSplitter);

        return stringToSplit;

    }
    public int parseAString(String aString){
        int number = Integer.parseInt(aString);
        return number;

    }
    public String parseAInt(int anIntToBeParsed) {
    	String anIntParsed = String.valueOf(anIntToBeParsed);
    	return anIntParsed;
    	
    }
	public void elementsInAList(List<WebElement> aListOfElements, String aString) {
		System.out.println(aString);
		for(WebElement listElementFor : aListOfElements) {
			System.out.println( ">>> " + listElementFor.getText());
			
		}
		
	}
    public void loadToList(List<String> aUserList) {
    	Constants.USERS_LIST.clear();
    	
    	for(String data: aUserList) {
			String [] usersArray = makeSplit(data, Constants.REGEX);
			String user = usersArray[0];
			String password = usersArray[1];

			User usuarioCompleto = new User(user, password);
			Constants.USERS_LIST.add(usuarioCompleto);
			
		}
    
    }

    public JavascriptExecutor createJse() {
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	return jse;
    	
    }
    public void scrollToADirection(String aDirection) {
    	JavascriptExecutor jse = createJse();
    	jse.executeScript(SCROLL_TO_DIR_START + aDirection + SCROLL_TO_DIR_END);
    	
    }
    public void scrollIntoView(WebElement anElement) {
    	JavascriptExecutor jse = createJse();
    	jse.executeScript(SCROLL_INTO_VIEW, anElement);
    
    }
    public void openUrlInNewTab(String anUrl) {
    	JavascriptExecutor jse = createJse();
    	jse.executeScript(WINDOW_OPEN);
    	
    	getTabs(1);
    	driver.get(anUrl);  
    	
    }
    public void getTabs(int aTab){
    	ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    	driver.switchTo().window(tabs.get(aTab));
    	
    }
    public Alert getSwitchToAlert() {
    	return driver.switchTo().alert();
    	
    }
    public void acceptOnOkPrompt() throws InterruptedException {
    	getSwitchToAlert().accept();
        Thread.sleep(1000);
        
    }
    public String AlertGetText() {
        String AlertText = getSwitchToAlert().getText();
        return AlertText;
        
    }
    public String replacementInAString(String aString, String objectToBeReplaced, String objectToReplace) {
    	String finalStringReplaced = aString.replace(objectToBeReplaced, objectToReplace);
    	return finalStringReplaced;
    	
    }
    public void printAndReportList(List<String> aListOfStrings) {
		for(String str : aListOfStrings) {
    		printAndReport(Constants.NORMALPRINT, str);
			
		}
		
    }
    public void printAndReport(String whatToPrint, String aString, String aNodeTitle) {
    	System.out.println(aString);
    	if (whatToPrint.equals(Constants.JSONPRINT)) {
        	String htmlDetail = DIVPRECODE_START + aString + DIVPRECODE_END;
    		getReportJsonLog(htmlDetail, aNodeTitle);
	    	
    	}else if(whatToPrint.equals(Constants.XMLPRINT)) {
    		String htmlDetail = XML_START + aString + XML_END;
    		getReportJsonLog(htmlDetail, aNodeTitle);
    		
    	}
    	
    }
    public void printAndReport(String whatToPrint, String aString) {
    	System.out.println(aString);
    	if(whatToPrint.equals(Constants.NORMALPRINT)) {
    		getReportLog(aString);
    	
    	}
    	
    }
    public void getReportLog(String aString) {
		ExtentTestManager.reporterLog(aString);
		
    }
    public void getReportJsonLog(String aString, String aNodeTitle) {
		ExtentTestManager.reporterJsonLog(aString, aNodeTitle);
    
    }
    public void takeScreenShot() {
    	ExtentTestManager.takeScreenShot();
    	
    }
    public void splitForPrintAndReport(String aText, String separator) {
    	String [] separatedText = aText.split(separator);
    	for(int i = 0; i < separatedText.length; i++) {
    		printAndReport(Constants.NORMALPRINT, separatedText[i]);
    	}
    	
    }
    public void navigateToUrl(String anUrl) {
    	driver.navigate().to(anUrl);   
    	
    }
    public String getRandomString(int maximoCaracteres, String unString){ 
        StringBuilder builder;
        builder = new StringBuilder(maximoCaracteres); 
        for (int m = 0; m < maximoCaracteres; m++) { 
            int myindex = (int)(unString.length() * Math.random()); 
            builder.append(unString.charAt(myindex));
            
        } 
        return builder.toString(); 
        
    } 
	public String restAssuredGetMethod(String anUrl) {
		Response response = RestAssured.given()
											.contentType(ContentType.JSON)
									   .when()
									   		.get(anUrl)
									   .then().log().all()
									   		.extract().response();
		
		String resp = response.asString();
		String rbpj = getPerfectParsedResponse(resp);
		
		return rbpj;
		
	}
	public String restAssuredGetMethod(String aBaseUri, String aBasePath, String aBody) {
		RestAssured.baseURI = aBaseUri;
		RestAssured.basePath = aBasePath;
		
		Response response = RestAssured.given()
											.contentType(ContentType.JSON)
									   .when()
										    .body(aBody)
										    .get()
									   .then().log().all()
										 	.extract().response();
		
		String resp = response.asString();
		String rbpj = getPerfectParsedResponse(resp);
		
		return rbpj;
		
	}
	public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	public String getPerfectParsedResponse(String unformattedJSON) {
		String perfectJSON;
		try {
				perfectJSON = GSON.toJson(JsonParser.parseString(unformattedJSON));
				
		}catch(Exception e) {
	    	Document doc = convertStringToDocument(unformattedJSON);
	        perfectJSON = convertDocumentToString(doc);
	        
	    }
	    return perfectJSON;
	    
	}
	private static String convertDocumentToString(Document doc) {
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer;
	        try {
	            transformer = tf.newTransformer();
	            StringWriter writer = new StringWriter();
	            
	            transformer.transform(new DOMSource(doc), new StreamResult(writer));
	            String output = writer.getBuffer().toString();
	            
	            return output;
	            
	        } catch (TransformerException e) {
	            e.printStackTrace();
	            
	        }
	        
	        return null;
	    }
    private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try {  
            builder = factory.newDocumentBuilder();  
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
            
            return doc;
            
        } catch (Exception e) {  
            e.printStackTrace();  
            
        } 
        return null;
        
    }
    public boolean verifyListIsOrderedAlphabetically(List<String> aListOfStrings) {
    	boolean isOrdered = Ordering.natural().isOrdered(aListOfStrings);
    	return isOrdered;
    	
    }
    public boolean verifyListIsOrderedAlphabetically(List<Integer> aListOfIntegers, boolean isOrdered) {
    	isOrdered = Ordering.natural().isOrdered(aListOfIntegers);
    	return isOrdered;
    	
    }       
	public List<Integer> convertDateToReorder(List<String> aListOfDates, String formatFromString, String aDateToBeFormatted) throws ParseException {
		List<String> aNewListOfDates = new ArrayList<String>();
		List<Integer> intList = new ArrayList<Integer>();
		
		for(int i = 0; i < aListOfDates.size(); i++) {
			Locale locale=new Locale("es", "ES");
			
	        String sDate = aListOfDates.get(i);  
			SimpleDateFormat formatter2=new SimpleDateFormat(formatFromString, locale);  
			
			Date date=formatter2.parse(sDate);
			SimpleDateFormat formatter = new SimpleDateFormat(aDateToBeFormatted);  
		    
			String strDate = formatter.format(date);  
		    aNewListOfDates.add(strDate);
		    
		}
		for(String s : aNewListOfDates) intList.add(Integer.valueOf(s));
		return intList;	
	    
	}
	public List<String> convertDateToReorderForString(List<String> aListOfDates, String formatFromString, String aDateToBeFormatted) throws ParseException {
		List<String> aNewListOfDates = new ArrayList<String>();
		for(int i = 0; i < aListOfDates.size(); i++) {
			Locale locale=new Locale("es", "ES");
			
	        String sDate = aListOfDates.get(i);  
			SimpleDateFormat formatter2=new SimpleDateFormat(formatFromString, locale);  
			
			Date date=formatter2.parse(sDate);
			SimpleDateFormat formatter = new SimpleDateFormat(aDateToBeFormatted);  
		    
			String strDate = formatter.format(date);  
		    aNewListOfDates.add(strDate);
		    
		}
		return aNewListOfDates;	
	    
	}
	public String convertDateToReorderForString(String aDate, String formatFromString, String aDateToBeFormatted) throws ParseException {
		Locale locale=new Locale(ES_LANGUAGE, ES_COUNTRY);
		SimpleDateFormat formatter2 = new SimpleDateFormat(formatFromString, locale);  
			
		Date date=formatter2.parse(aDate);
		SimpleDateFormat formatter = new SimpleDateFormat(aDateToBeFormatted);  
	    
		String strDate = formatter.format(date);  
		    

		return strDate;	
	    
	}
	public List<Boolean> convertStringToBoolean(List<String> aListOfStrings){
		List<Boolean> aListOfBoolean = new ArrayList<Boolean>();
		for(int i = 0; i < aListOfStrings.size(); i++) {
			boolean bool = Boolean.parseBoolean(aListOfStrings.get(i));
			aListOfBoolean.add(bool);
			
		}
		return aListOfBoolean;
		
	}
	public String createADate(int aQtyOfDays, String anOperation, String aPattern) {
		LocalDateTime today = LocalDateTime.now();
		
		LocalDateTime aDate = null;
		if(anOperation.equals("A")) {
			aDate = today.plusDays(aQtyOfDays);
			
		}
		if(anOperation.equals("M")) {
			aDate = today.minusDays(aQtyOfDays);
			
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(aPattern);
		String formattedDateTime = aDate.format(formatter);
		
		return formattedDateTime;	
		
	}
	public String createADate(String aPattern) {
		LocalDateTime today = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(aPattern);
		String formattedDateTime = today.format(formatter);
		
		return formattedDateTime;	
		
	}
	public String createASpecificDate(int aYear, Month aMonth, int aDay, String aPattern) {
		LocalDate today = LocalDate.of(aYear, aMonth, aDay);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(aPattern);
		
		String formattedDateTime = today.format(formatter);
		
		return formattedDateTime;	
		
	}
	public int getRandomNumber(int min, int max) {
		int number = (int) ((Math.random() * (max - min) + min));
		return number;
		
	}
	public String reverseDatesForAssertion(String aDayInStringToReverse, String aPatternToSplit) {
		String diaArray[] = aDayInStringToReverse.split(aPatternToSplit);
		String reverseDayFromAfterCompletion = diaArray[2] + "-" + diaArray[1] + "-" + diaArray[0];
		
		return reverseDayFromAfterCompletion;
	
	}
	public JSONArray getAJsonArrayFromJson(String aJsonOutput, String aJsonPath) {
		JSONArray aJsonArray = JsonPath.read(aJsonOutput, aJsonPath);
		
		return aJsonArray;
		
	}
	public String getJsonStringFromArray(JSONArray jsonArr, int pos){
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < jsonArr.length(); i++){
		    list.add((String) jsonArr.get(i));
		    
		}
		return list.get(pos);
		
	}
    public List<String> getXmlParsedData(String aRequestBody, String anXpathExpression) {
        List<String> lS = new ArrayList<String>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document doc = builder.parse(new InputSource(new StringReader(aRequestBody)));
            
            XPathFactory xPathfactory = XPathFactory.newInstance();
            
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile(anXpathExpression + "text()");
            
            Object result = expr.evaluate(doc, XPathConstants.NODESET);
           
            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
            	lS.add(nodes.item(i).getNodeValue().toString());
                           
            }
            
    	} catch (XPathExpressionException e1) {
            e1.printStackTrace();
            
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
            
        } catch (SAXException e1) {
            e1.printStackTrace();
            
        } catch (IOException e1) {
            e1.printStackTrace();
            
        }
    	return lS;
        
	}	
    public String removeChars(String str, int start, int end)    {
        StringBuffer sb = new StringBuffer(str);
        sb.delete(start, end);
  
        return sb.toString();

    }
    public String replaceChars(String str, String stringToBeReplaced, String stringToReplace) {
    	String newString = str.replace(stringToBeReplaced, stringToReplace);
    	
    	return newString;
    	
    }
    public String prettyXMLFormat(String input, int anIndentNumber) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactoryAttributes(transformerFactory, anIndentNumber);
            
            Transformer transformer = transformerFactory.newTransformer(); 
            transformerSetOutProperties(transformer);
            
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
            
        }
        
    }
    public void transformerFactoryAttributes(TransformerFactory aTransformerFactory, int anIndentNumber) {
    	aTransformerFactory.setAttribute(INDENT_NUMBER, anIndentNumber);
    	aTransformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, Constants.EMPTY);
    	aTransformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, Constants.EMPTY);
    	
    }
    public void transformerSetOutProperties(Transformer aTransformer) {
    	aTransformer.setOutputProperty(OutputKeys.INDENT, YES);
    	aTransformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, YES);
        
    }
	public int countOccurrences(String str, String word) {
		 String a[] = str.split(" ");
		 int count = 0;
		 for (int i = 0; i < a.length; i++)	 {
			 if (word.equals(a[i])) {
			     count++;

			 }
	
		 }	
		 return count;
		 
	}
    public String prettyPrintResponse(Response aResponse){
        String responseBody = Constants.EMPTY;
        try{
            responseBody = aResponse.prettyPrint();
            
        }catch(NullPointerException npe){
            responseBody = "The response is null";
        
        }
        return responseBody;

    }
    public String getStringFromJsonArray(JSONArray aJsonArray, String aKey){
        String valueToReturn = Constants.EMPTY;

        for (int i = 0; i < aJsonArray.length(); i++) {
            try {
                JSONObject jsonObject = aJsonArray.getJSONObject(i);
                if (jsonObject.has(aKey)) {
                    valueToReturn = jsonObject.getString(aKey);

                }
            } catch (JSONException e) {
                e.printStackTrace();

            }

        }
        return valueToReturn;

    }
    public Actions getActions() {
       Actions actions = new Actions(driver);
       return actions;
       
    }
    public void moveToAnElement(WebElement anElement) {
    	getActions().moveToElement(anElement)
    					.build()
    			    .perform();
   	 
    }
    public void moveWeightBar(WebElement anElement, int aWidth) {
	    getActions().moveToElement(anElement)
	                	.moveByOffset(aWidth, 0)
	                .click()
	                	.build()
	                .perform();
    	
    }
    public void moveWeightBar(WebElement anElement, int aWidth, int aHeigth) {
	    getActions().moveToElement(anElement)
	    				.moveByOffset(aWidth, aHeigth)
				    .click()
				    	.build()
				    .perform();
    	
    }
    public WebElement switchToAnActiveElement() {
	   WebElement anElement = driver.switchTo().activeElement();
	   return anElement;
   
   }
   public String normalizeForAccent(String aStringToNormalize, Form aNormalizer) {
		String aStringNormalized = Normalizer.normalize(aStringToNormalize, aNormalizer);
		return aStringNormalized;
		
   }
   public void writeIntoAFile(String aFileName, String aLineToWrite) {
		try	{
		    FileWriter fw = new FileWriter(aFileName,true);
		    fw.write(aLineToWrite + Constants.LINE_JUMP);
		    fw.close();
		    
		} catch(IOException ioe){
			System.err.println("IOException: " + ioe.getMessage());
	
		}
		
	}
    public double timeFromMilliSecondsToSeconds(long aMilliSeconds) {
    	double seconds = (double)Math.round(aMilliSeconds * 1d) / 1000d;
    	return seconds;
    	
    }
    public List<Integer> getSequenceOfNumbers(int min, int max) {
    	List<Integer> numbers = new ArrayList<Integer>();
    	for (int i = min ; i < max; i++) 
    		numbers.add(i);
    	
    	return numbers;
    	
    }
    
	public List<String> getRowTable (List<WebElement> rows) {
    	List<String> rowsTable = new ArrayList<String>();
    	for (int i = 0; i < rows.size(); i++) {
    		rowsTable.add(this.getTextFromAWebElement(rows.get(i)));
    	
    	}
    	return rowsTable;
    	
    }
    public List<String> removeEmptyValuesFromList(List<String> aListOfStringsWithEmptyValues) {
    	List<String> aListWithNoEmptyValues = new ArrayList<String>();
    	
		for(int i = 0; i < aListOfStringsWithEmptyValues.size(); i++) {
			if(!aListOfStringsWithEmptyValues.get(i).equals(Constants.EMPTY)) {
				aListWithNoEmptyValues.add(aListOfStringsWithEmptyValues.get(i));
				
			}
			
		}
		return aListWithNoEmptyValues;
		
    }
    public List<String> removeEmptyValuesFromListArray(String[] aListOfStringsWithEmptyValues) {
    	List<String> aListWithNoEmptyValues = new ArrayList<String>();
    	
		for(int i = 0; i < aListOfStringsWithEmptyValues.length; i++) {
			if(!aListOfStringsWithEmptyValues[i].equals(Constants.EMPTY)) {
				aListWithNoEmptyValues.add(aListOfStringsWithEmptyValues[i]);
				
			}
			
		}
		return aListWithNoEmptyValues;
		
    }
    public List<String[]> splitList(List<String> list, String aSplitter){
    	List<String[]> matrixListTextSplitted = new ArrayList<String[]>();
		
		for (String str : list) {
			String[] mLs = str.split(aSplitter);
			matrixListTextSplitted.add(mLs);
	
		}
		
		return matrixListTextSplitted;
    	
    }
    public List<String[]> splitList(List<String> list){
    	List<String[]> matrixListTextSplitted = new ArrayList<String[]>();
		
		for (String str : list) {
			String[] mLs = str.split(Constants.LINE_JUMP);
			matrixListTextSplitted.add(mLs);
	
		}
		
		return matrixListTextSplitted;
    	
    }
    public void IndexOutOfBoundsExceptionCatch(List<String[]> list, int pos, int posArray) {
    	List<String[]> listArray = new ArrayList<String[]>();
    	try {
    		listArray.addAll(list);
    		//System.out.println("--> " + listArray);
    		
    	}
    	catch (IndexOutOfBoundsException e){
    		//System.out.println(">>> " + e.getLocalizedMessage());
    		//ExtentTestManager.reporterLog(AssertionErrorMessages.MATRIX_NOT_FOUND + e.getLocalizedMessage(), Status.FAIL);
    		
    	}
    	
    }
    public void IndexOutOfBoundsExceptionCatchList(List<String> list, int pos) {
    	List<String> listArray = new ArrayList<String>();
    	try {
    		list.add(listArray.get(pos));
    		
    	}
    	catch (IndexOutOfBoundsException e){
    		//System.out.println(e.getLocalizedMessage());
    		//ExtentTestManager.reporterLog(AssertionErrorMessages.MATRIX_NOT_FOUND + e.getLocalizedMessage(), Status.FAIL);
    		
    	}
    	
    }
    public List<String> matchStringOrCharacter(List<String> List, String match) {
    	List<String> listReturn= new ArrayList<String>();
    	for (int i =0 ; i < List.size(); i++) {
    		if (List.get(i).contains(match)) {
    			listReturn.add(List.get(i));
    			
    		}
    		
    	} 
    	return listReturn;
    	
    }
    public void fileUploadWithRobot (String path) throws AWTException {
    	Robot robot = new Robot();
    	
    	robot.setAutoDelay(2000);
 
        StringSelection selection = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,null);
 
        robot.setAutoDelay(1000);
 
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
 
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
 
        robot.setAutoDelay(1000);
 
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
    }
    public List<String> getFilesFromDirectory(String path) throws AWTException {
		File directory =  new File (path);
		List<String> nameFiles = new ArrayList<String>();
		if(directory !=null) {
			File[] files = directory.listFiles();
			for(int i=0; i< files.length; i++) {
				File filesText = files[i];
				nameFiles.add(filesText.getName().toString());
			}
		}
		return nameFiles;
	}
    public String cutAString(String aStringToBeCutted, int from, int to) {
    	String aStringCutted = aStringToBeCutted.substring(from, to);
    	return aStringCutted;
    	
    }
    
}
