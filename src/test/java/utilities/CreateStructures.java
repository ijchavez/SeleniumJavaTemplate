package utilities;

public class CreateStructures {
	public static void main(String[] args) {
		setFindBy("xpath", "//*[@class='titulo-pagina']", "WebElement", "searchElement", "geTituloPagina()");
		
		createPage("LoginPage", "BasePage");
	
	}
	public static void setFindBy(String selector, String value, String elementType, String elementName, String functionName) {
		String findBy = "";
		String function = "";
		
		findBy = "    @FindBy(" + selector + "=\""+ value + "\")\r\n"
				+ "    " + elementType + " " +  elementName + ";\r\n";
		function =  "    public " + elementType + " " + functionName + "{\r\n"
					+ "     	return " + elementName +";\r\n"
					+ "     	\r\n"
					+ "    }";
			
		System.out.println("@FindBy Created...");
		System.out.println(findBy + function);
		
		
	}
	public static void setFindBy(String selector, String value, String elementType, String elementName, String functionName, String pageObject, String pageName) {
		String findBy = "";
		String function = "";
		
		findBy = "    @FindBy(" + selector + "=\""+ value + "\")\r\n"
				+ "    " + elementType + " " +  elementName + ";\r\n";		
		function =  "    public " + elementType + " " + functionName + "{\r\n"
				+ "    	" + elementName + ".click();\r\n"
				+ "    	\r\n"
				+ "    	" + pageName + " = new " + pageObject + "(driver);\r\n"
				+ "    	return " + pageName + ";"
				+ "     	\r\n"
				+ "    }";
		
		System.out.println("****************************************");
		System.out.println("@FindBy Created...");
		System.out.println(findBy + function);
		System.out.println("Add these statement in your Extends Page: Public " + pageObject + " " + pageName + ";");
		System.out.println("****************************************");
		
	}

	public static void createPage(String pageName, String extendsPage) {
		String page = "package pages;\r\n"
				+ "\r\n"
				+ "import org.openqa.selenium.WebDriver;\r\n"
				+ "import org.openqa.selenium.WebElement;\r\n"
				+ "import org.openqa.selenium.support.FindBy;\r\n"
				+ "import org.openqa.selenium.support.PageFactory;\r\n"
				+ "\r\n"
				+ "public class " + pageName + " extends " + extendsPage + "{\r\n"
				+ "    public LoginPage(WebDriver remoteDriver){\r\n"
				+ "        driver = remoteDriver;\r\n"
				+ "        PageFactory.initElements(driver, this);\r\n"
				+ "\r\n"
				+ "    }\r\n"
				+ "    \r\n"
				+ "}";
		
		System.out.println("****************************************");
		System.out.println("Page Created...");		
		System.out.println(page);
		System.out.println("****************************************");
		
	}
	
}
