package utilities;

import java.util.ArrayList;
import java.util.List;

import constructor.User;

public class Constants{
	public static final String WEBDRIVER_CHROME = "webdriver.chrome.driver";
	public static final String CHROMEDRIVER_FILE = "src/test/resources/chromedriver";
	
	public static final String URL = "http://automationpractice.com/";
	public static final String DATALOADER = "dataloader/";
	public static final String WEBDRIVER_ATTRIBUTE_VALUE = "WebDriver";
	//Esta variable la utilizo para agregar a los usuarios que van a formar parte de los test
	public static List<User> USERS_LIST = new ArrayList<User>();
	public static String JSONPRINT = "J";
	public static String XMLPRINT = "X";
	public static String NORMALPRINT = "N";
	//Constantes para ChromeHeadLess
	public static final String HEADLESS = "--headless";
	public static final String DISABLE_GPU = "--disable-gpu";
	public static final String WINDOW_SIZE = "--window-size=444,761";
	public static final String WINDOW_SIZE_RESPONSIVE = "--window-size=400,850";
	public static final String WINDOW_SIZE_FULLSCREEN = "--window-size=1920,1080";
	public static final String IGNORE_CERTIFICATE_ERROR = "--ignore-certificate-errors";
	public static final String DISABLE_EXTENSIONS = "--disable-extensions";
	public static final String DISABLE_NOTIFICATIONS = "--disable-notifications";
	public static final String NO_SANDBOX = "--no-sandbox";
	public static final String DISABLE_DEV_SHM_USAGE = "--disable-dev-shm-usage";
	public static final String DISABLE_INFOBARS = "disable-infobars";
	//Constantes para Navegador
	public static final String START_MAXIMIZED = "--start-maximized";
	public static final String CREDENTIALS_ENABLE_SERVICE = "credentials_enable_service";
	public static final String PROFILE_PASSWORD_MANAGER_ENABLED ="profile.password_manager_enabled";
	public static final String EXCLUDE_SWITCHES = "excludeSwitches";
	public static final String ENABLE_AUTOMATION = "enable-automation";
	public static final String HASHMAP_PREFERENCES = "prefs";
	public static final String INCOGNITO = "incognito";

	
}